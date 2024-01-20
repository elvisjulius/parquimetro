package com.parquimetro.entity;

import com.parquimetro.dto.CondutorDTO;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Data
@Entity
@Table(name = "condutor")
public class Condutor {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(nullable = false)
    private String nome;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "endereco_id", referencedColumnName = "id")
    private Endereco endereco;

    @OneToMany(mappedBy = "condutor", cascade = CascadeType.ALL)
    private List<Contato> contatos;

    @OneToMany(mappedBy = "condutor", cascade = CascadeType.ALL)
    private List<Veiculo> veiculos;

    public CondutorDTO toDTO() {
        return new CondutorDTO(
                this.getId(),
                this.getNome(),
                this.getEndereco().toDTO(),
                this.getContatos().stream().map(Contato::toDTO).collect(Collectors.toList()),
                this.getVeiculos().stream().map(Veiculo::toDTO).collect(Collectors.toList())
        );
    }
}