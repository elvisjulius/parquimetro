package com.parquimetro.entity;
import jakarta.persistence.*;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "controle_estacionamento")
public class ControleDeEstacionamento {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "hora_entrada", nullable = false)
    private LocalDateTime horaEntrada;

    @Column(name = "hora_saida")
    private LocalDateTime horaSaida;

    @Pattern(regexp = "fixa|variavel", message = "O tipo de cobrança deve ser 'fixa' ou 'variavel'")
    @Column(name = "tipo_cobranca", nullable = false)
    private String tipoCobranca; // Pode ser "fixa" ou "variavel"

    @ManyToOne
    @JoinColumn(name = "veiculo_id", nullable = false)
    private Veiculo veiculoUtilizado;

}
