package com.parquimetro.model;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "contato")
public class Contato {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(nullable = false)
    private String codigoPais;

    @Column(nullable = false)
    private String codigoArea;

    @Column(nullable = false)
    private String numero;

    @ManyToOne
    @JoinColumn(name = "condutor_id", nullable = false)
    private Condutor condutor;
}