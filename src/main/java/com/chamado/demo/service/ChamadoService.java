package com.chamado.demo.service;

import com.chamado.demo.model.*;
import com.chamado.demo.repository.ChamadoRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class ChamadoService {

    private final ChamadoRepository chamadoRepository;
    private final UserService userService;

    public void createChamado(ChamadoModel chamadoModel) {
        User user = this.userService.findById(chamadoModel.getSolicitanteId());

        Chamado chamado = Chamado.builder()
                .status(Status.EM_ESPERA)
                .titulo(chamadoModel.getTitulo())
                .descricao(chamadoModel.getDescricao())
                .departamento(chamadoModel.getDepartamento())
                .usuario(user)
                .build();
        this.chamadoRepository.save(chamado);
    }

    public List<ChamadoResponse> selectAllByUserId(Long id) {
        List<Chamado> chamados = this.chamadoRepository.selectAllFromChamadoByUserId(id);
        List<ChamadoResponse> chamadoResponse = new ArrayList<>();

        chamados.forEach(c -> {
            String atendente = c.getAtendente() == null ? "Sem atendimento" : c.getAtendente().getNome();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            String formatDateTime = c.getCriado().format(formatter);

            String status = null;
            if (c.getStatus() == Status.EM_ANDAMENTO) {
                status = "Em andamento";
            } else if (c.getStatus() == Status.FINALIZADO) {
                status = "Finalizado";
            } else if (c.getStatus() == Status.EM_ESPERA) {
                status = "Em espera";
            }

            chamadoResponse.add(ChamadoResponse.builder()
                            .codigo(c.getId())
                            .nome(c.getUsuario().getNome())
                            .titulo(c.getTitulo())
                            .criado(formatDateTime)
                            .status(status)
                            .atendente(atendente)
                    .build());
        });

        return chamadoResponse;
    }

    public List<ChamadoResponse> selectAllByAtendenteDepartamento(Departamento departamento) {
        List<Chamado> chamados = this.chamadoRepository.selectAllFromChamadoByAtendenteDepartamento(departamento);
        List<ChamadoResponse> chamadoResponse = new ArrayList<>();

        chamados.forEach(c -> {
            String atendente = c.getAtendente() == null ? "Sem atendimento" : c.getAtendente().getNome();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            String formatDateTime = c.getCriado().format(formatter);

            String status = null;
            if (c.getStatus() == Status.EM_ANDAMENTO) {
                status = "Em andamento";
            } else if (c.getStatus() == Status.FINALIZADO) {
                status = "Finalizado";
            } else if (c.getStatus() == Status.EM_ESPERA) {
                status = "Em espera";
            }

            chamadoResponse.add(ChamadoResponse.builder()
                    .codigo(c.getId())
                    .nome(c.getUsuario().getNome())
                    .titulo(c.getTitulo())
                    .criado(formatDateTime)
                    .status(status)
                    .atendente(atendente)
                    .build());
        });

        return chamadoResponse;
    }

    public Chamado getChamado(Long id) {
        Chamado chamado = this.chamadoRepository.findById(id).get();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String formatDateTime = chamado.getCriado().format(formatter);
        chamado.setDataFormatada(formatDateTime);

        return chamado;
    }

    public Chamado updateChamado(Long chamadoId, UpdateChamado updateChamado) {
        Chamado chamado = this.chamadoRepository.findById(chamadoId).get();
        User atendente = this.userService.findById(updateChamado.getAtendenteId());

        chamado.setStatus(updateChamado.getStatus());
        chamado.setAtendente(atendente);

        chamado = this.chamadoRepository.save(chamado);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String formatDateTime = chamado.getCriado().format(formatter);
        chamado.setDataFormatada(formatDateTime);

        return chamado;
    }

    public void deleteChamado(Long chamadoId) {
        this.chamadoRepository.deleteById(chamadoId);
    }
}
