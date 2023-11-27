package com.chamado.demo.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String email;
    private String password;
    private String departamento;
    private String telefone;

    @Enumerated(EnumType.STRING)
    private Role role;
}
