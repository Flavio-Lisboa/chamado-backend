package com.chamado.demo.model;

import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class ChamadoFluxo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;
    public String comentario;

    @ManyToOne(cascade=CascadeType.ALL)
    public Chamado chamado;

    @Nullable
    @ManyToOne(cascade=CascadeType.ALL)
    public User user;

    @Nullable
    @ManyToOne(cascade=CascadeType.ALL)
    public User atendente;
}
