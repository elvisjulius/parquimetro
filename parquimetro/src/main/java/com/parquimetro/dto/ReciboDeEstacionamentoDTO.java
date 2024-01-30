package com.parquimetro.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

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
) {

    @Override
    public String toString() {
        return "*RECIBO* \n" +
                "Hora de Entrada: " + formatarLocalDateTime(horaEntrada) +
                "\nHora de Saida: " + formatarLocalDateTime(horaSaida) +
                "\nTipo de Cobrança: " + tipoCobranca + '\'' +
                "\nVeículo Utilizado: " + veiculos +
                "\nForma de Pagamento: " + metodoPagamento + '\'' +
                "\nValor do Estacionamento: R$" + valorEstacionamento ;
    }

    static String formatarLocalDateTime(LocalDateTime localDateTime) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        return localDateTime.format(formatter);
    }
}
