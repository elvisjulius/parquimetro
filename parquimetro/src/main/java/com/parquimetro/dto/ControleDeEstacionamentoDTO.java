package com.parquimetro.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.parquimetro.entity.Condutor;
import com.parquimetro.entity.ControleDeEstacionamento;
import com.parquimetro.util.MetodoPagamento;
import com.parquimetro.util.TipoCobranca;
import io.swagger.v3.oas.annotations.Hidden;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public record ControleDeEstacionamentoDTO(
        @Hidden
        UUID id,
        @Hidden
        LocalDateTime horaEntrada,
        LocalDateTime horaSaida,
        @NotNull(message = "O tipo de cobrança deve ser 'FIXA' ou 'VARIAVEL")
        TipoCobranca tipoCobranca,
        @NotNull(message = "Veiculos do  Condutor não ser nulo")
        VeiculoDTO veiculos,
        @NotNull(message = "O forma de pagamento deve ser informada")
        MetodoPagamento metodoPagamento,
        @Hidden
        @JsonInclude(JsonInclude.Include.NON_NULL)
        Boolean notificado
) {
    public ControleDeEstacionamento toEntity() {
        ControleDeEstacionamento controleDeEstacionamento = new ControleDeEstacionamento();
        controleDeEstacionamento.setId(this.id());
        controleDeEstacionamento.setHoraEntrada(LocalDateTime.now());
        controleDeEstacionamento.setHoraSaida(this.horaSaida);
        controleDeEstacionamento.setTipoCobranca(this.tipoCobranca.getDescricao().toLowerCase());
        controleDeEstacionamento.setVeiculoUtilizado(this.veiculos.toEntity());
        controleDeEstacionamento.setMetodoPagamento(this.metodoPagamento.getDescricao().toLowerCase());
        controleDeEstacionamento.setNotificado(this.notificado);
        return controleDeEstacionamento;
    }

}
