package com.chamado.demo.model;

import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Chamado {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @CreationTimestamp
    private LocalDateTime criado;
    @Enumerated(EnumType.STRING)
    private Status status;
    private String titulo;
    private String descricao;
    @Enumerated(EnumType.STRING)
    private Departamento departamento;

    @Transient
    private String dataFormatada;

    @ManyToOne
    private User usuario;

    @Nullable
    @ManyToOne
    private User atendente;
}
