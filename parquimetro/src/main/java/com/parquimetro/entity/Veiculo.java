package com.parquimetro.entity;

import com.parquimetro.dto.VeiculoDTO;
import jakarta.persistence.*;
import lombok.Data;

import java.util.UUID;

@Data
@Entity
@Table(name = "veiculo")
public class Veiculo {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    @Column(nullable = false, unique = true)
    private String placa;
    @ManyToOne
    @JoinColumn(name = "condutor_id")
    private Condutor condutor;

    public VeiculoDTO toDTO() {
        return new VeiculoDTO(
                this.getId(),
                this.getPlaca()
        );
    }
}
