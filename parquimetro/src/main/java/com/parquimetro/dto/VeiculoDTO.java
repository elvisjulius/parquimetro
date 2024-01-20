package com.parquimetro.dto;

import com.parquimetro.entity.Veiculo;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record VeiculoDTO(
        UUID id,
        @NotNull(message = "placa do carro não pode estar em nulo.")
        @NotBlank(message = "placa do carro não pode estar em Branco.")
        String placa
) {
    public Veiculo toEntity() {
        Veiculo veiculo = new Veiculo();
        veiculo.setId(this.id());
        veiculo.setPlaca(this.placa());
        return veiculo;
    }
}
