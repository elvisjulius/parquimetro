package com.parquimetro.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record ReciboDeEstacionamentoDTO(
        LocalDateTime horaEntrada,
        LocalDateTime horaSaida,
        @NotNull(message = "O tipo de cobrança deve ser 'FIXA' ou 'VARIAVEL")
        @NotEmpty(message = "O tipo de cobrança deve ser 'FIXA' ou 'VARIAVEL")
        String tipoCobranca,
        @NotNull(message = "Veiculos do  Condutor não ser nulo")
        @NotEmpty(message = "Veiculos do Condutor não ser vazia")
        VeiculoDTO veiculos,

        String metodoPagamento,

        Double valorEstacionamento
){

}
