package com.chamado.demo.controller;

import com.chamado.demo.model.ChamadoFluxo;
import com.chamado.demo.model.ChamadoFluxoModel;
import com.chamado.demo.model.ChamadoFluxoResponse;
import com.chamado.demo.service.ChamadoFluxoService;
import jakarta.annotation.Nullable;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("fluxo")
public class ChamadoFluxoController {

    private final ChamadoFluxoService chamadoFluxoService;

    @CrossOrigin
    @PostMapping
    public ResponseEntity<Void> addComentario(@RequestBody @Nullable ChamadoFluxoModel chamadoFluxo) {
        this.chamadoFluxoService.addComentario(chamadoFluxo);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @CrossOrigin
    @GetMapping("{chamadoId}")
    public ResponseEntity<List<ChamadoFluxoResponse>> getComentarios(@PathVariable Long chamadoId) {
        List<ChamadoFluxoResponse> response = this.chamadoFluxoService.getAllComentarios(chamadoId);

        return ResponseEntity.ok(response);
    }
}
