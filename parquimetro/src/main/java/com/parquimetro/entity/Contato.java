package com.parquimetro.entity;


import com.parquimetro.dto.ContatoDTO;
import jakarta.persistence.*;
import lombok.Data;

import java.util.UUID;

@Data
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

    public ContatoDTO toDTO() {
        return new ContatoDTO(
                this.getId(),
                this.getCodigoPais(),
                this.getCodigoArea(),
                this.getNumero()
        );
    }
}