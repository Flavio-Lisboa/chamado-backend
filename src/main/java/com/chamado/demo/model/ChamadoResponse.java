package com.chamado.demo.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ChamadoResponse {
    private Long codigo;
    private String nome;
    private String titulo;
    private String criado;
    private String status;
    private String atendente;
}
