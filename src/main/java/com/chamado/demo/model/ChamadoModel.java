package com.chamado.demo.model;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Data;

@Data
public class ChamadoModel {

    private Long solicitanteId;
    private String titulo;
    private String descricao;

    @Enumerated(EnumType.STRING)
    private Departamento departamento;
}
