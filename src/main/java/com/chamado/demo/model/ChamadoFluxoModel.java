package com.chamado.demo.model;

import jakarta.annotation.Nullable;
import lombok.Data;

@Data
public class ChamadoFluxoModel {

    private String comentario;
    @Nullable
    private Long userId;
    @Nullable
    private Long atendenteId;
    private Long chamadoId;
}
