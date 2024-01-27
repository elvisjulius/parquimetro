package com.parquimetro.dto;

import com.parquimetro.entity.Condutor;
import com.parquimetro.entity.ControleDeEstacionamento;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public record ControleDeEstacionamentoDTO(
        UUID id,
        LocalDateTime horaEntrada,
        LocalDateTime horaSaida,
        @NotNull(message = "O tipo de cobrança deve ser 'FIXA' ou 'VARIAVEL")
        @NotEmpty(message = "O tipo de cobrança deve ser 'FIXA' ou 'VARIAVEL")
        String tipoCobranca,
        @NotNull(message = "Veiculos do  Condutor não ser nulo")
        @NotEmpty(message = "Veiculos do Condutor não ser vazia")
        VeiculoDTO veiculos,

        Boolean notificado
) {
    public ControleDeEstacionamento toEntity() {
        ControleDeEstacionamento controleDeEstacionamento = new ControleDeEstacionamento();
        controleDeEstacionamento.setId(this.id());
        controleDeEstacionamento.setHoraEntrada(this.horaEntrada);
        controleDeEstacionamento.setHoraSaida(this.horaSaida);
        controleDeEstacionamento.setTipoCobranca(this.tipoCobranca);
        controleDeEstacionamento.setVeiculoUtilizado(this.veiculos.toEntity());
        controleDeEstacionamento.setNotificado(this.notificado);
        return controleDeEstacionamento;
    }

}
