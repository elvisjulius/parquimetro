package com.parquimetro.entity;

import com.parquimetro.dto.EnderecoDTO;
import jakarta.persistence.*;
import lombok.Data;

import java.util.UUID;

@Data
@Entity
@Table(name = "endereco")
public class Endereco {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(nullable = false)
    private String logradouro;

    @Column(nullable = false)
    private String numero;

    @Column(nullable = false)
    private String complemento;

    @Column(nullable = false)
    private String bairro;

    @Column(nullable = false)
    private String cidade;

    @Column(nullable = false)
    private String estado;

    @Column(nullable = false)
    private String cep;

    @OneToOne(mappedBy = "endereco", optional = false)
    private Condutor condutor;

    public EnderecoDTO toDTO() {
        return new EnderecoDTO(
                this.getId(),
                this.getLogradouro(),
                this.getNumero(),
                this.getComplemento(),
                this.getBairro(),
                this.getCidade(),
                this.getEstado(),
                this.getCep()
        );
    }

}