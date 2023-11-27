package com.chamado.demo.controller;

import com.chamado.demo.model.*;
import com.chamado.demo.service.ChamadoService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("chamados")
public class ChamadoController {

    private final ChamadoService chamadoService;

    @CrossOrigin
    @PostMapping
    public ResponseEntity<Void> createChamado(@RequestBody ChamadoModel chamado) {
        this.chamadoService.createChamado(chamado);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @CrossOrigin
    @GetMapping("users/{id}")
    public ResponseEntity<List<ChamadoResponse>> getAllChamadosByUserId(@PathVariable Long id) {
        List<ChamadoResponse> chamados = this.chamadoService.selectAllByUserId(id);

        return ResponseEntity.ok(chamados);
    }

    @CrossOrigin
    @GetMapping("atendentes")
    public ResponseEntity<List<ChamadoResponse>> getAllChamadosByAtendenteDepartamento(@RequestParam Departamento departamento) {
        List<ChamadoResponse> chamados = this.chamadoService.selectAllByAtendenteDepartamento(departamento);

        return ResponseEntity.ok(chamados);
    }

    @CrossOrigin
    @GetMapping("{id}")
    public ResponseEntity<Chamado> getChamado(@PathVariable Long id) {
        Chamado chamado = this.chamadoService.getChamado(id);

        return ResponseEntity.ok(chamado);
    }

    @CrossOrigin
    @PutMapping("{chamadoId}")
    public ResponseEntity<Chamado> updateChamado(@PathVariable Long chamadoId, @RequestBody UpdateChamado updateChamado) {
        Chamado chamado = this.chamadoService.updateChamado(chamadoId, updateChamado);

        return ResponseEntity.ok(chamado);
    }

    @CrossOrigin
    @DeleteMapping("{chamadoId}")
    public void deleteChamado(@PathVariable Long chamadoId) {
        this.chamadoService.deleteChamado(chamadoId);
    }
}
