package com.chamado.demo.service;

import com.chamado.demo.model.*;
import com.chamado.demo.repository.ChamadoFluxoRepository;
import jakarta.persistence.Transient;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class ChamadoFluxoService {

    private final ChamadoFluxoRepository chamadoFluxoRepository;
    private final UserService userService;
    private final ChamadoService chamadoService;

    @Transient
    public void addComentario(ChamadoFluxoModel chamadoFluxo) {

        User user = new User();
        User atendente = new User();

        if (chamadoFluxo.getUserId() != null) {
            user = this.userService.findById(chamadoFluxo.getUserId());
        }

        if (chamadoFluxo.getAtendenteId() != null) {
            atendente = this.userService.findById(chamadoFluxo.getAtendenteId());
        }

        Chamado chamado = this.chamadoService.getChamado(chamadoFluxo.getChamadoId());

        ChamadoFluxo chamadoComentario;

        if (chamadoFluxo.getUserId() != null) {
            chamadoComentario = ChamadoFluxo.builder()
                    .comentario(chamadoFluxo.getComentario())
                    .chamado(chamado)
                    .user(user)
                    .build();
        } else {
            chamadoComentario = ChamadoFluxo.builder()
                    .comentario(chamadoFluxo.getComentario())
                    .chamado(chamado)
                    .atendente(atendente)
                    .build();
        }

        this.chamadoFluxoRepository.save(chamadoComentario);
    }

    public List<ChamadoFluxoResponse> getAllComentarios(Long chamadoId) {
        List<ChamadoFluxo> chamadoFluxoList = this.chamadoFluxoRepository.selectAllComentarios(chamadoId);

        List<ChamadoFluxoResponse> chamadoFluxoResponseList = new ArrayList<>();

        chamadoFluxoList.forEach(c -> {
            String nome = c.atendente != null ? c.atendente.getNome() : c.user.getNome();
            chamadoFluxoResponseList.add(new ChamadoFluxoResponse(c.comentario, nome));
        });

        return chamadoFluxoResponseList;
    }
}
