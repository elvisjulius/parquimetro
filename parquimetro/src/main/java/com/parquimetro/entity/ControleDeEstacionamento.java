package com.parquimetro.entity;
import com.parquimetro.dto.ControleDeEstacionamentoDTO;
import jakarta.persistence.*;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Entity
@Table(name = "controle_estacionamento")
public class ControleDeEstacionamento {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(name = "hora_entrada", nullable = false)
    private LocalDateTime horaEntrada = LocalDateTime.now();

    @Column(name = "hora_saida")
    private LocalDateTime horaSaida;

    @Pattern(regexp = "fixa|variavel", message = "O tipo de cobrança deve ser 'fixa' ou 'variavel'")
    @Column(name = "tipo_cobranca", nullable = false)
    private String tipoCobranca; // Pode ser "fixa" ou "variavel"

    @OneToOne
    private Veiculo veiculoUtilizado;

    private Boolean notificado = false;

    public ControleDeEstacionamentoDTO toDTO(){
        return new ControleDeEstacionamentoDTO(
                this.getId(),
                this.getHoraEntrada(),
                this.getHoraSaida(),
                this.getTipoCobranca(),
                this.getVeiculoUtilizado().toDTO(),
                this.getNotificado()
        );
    }

}


