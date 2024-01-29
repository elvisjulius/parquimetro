package com.parquimetro.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.parquimetro.entity.Veiculo;
import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record VeiculoDTO(
        @Hidden
        @JsonInclude(JsonInclude.Include.NON_NULL)
        UUID id,
        @Schema(example = "MHK-1072")
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
