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
        UUID id,
        @Schema(example = "MHK-1072")

        String placa
) {
    public Veiculo toEntity() {
        Veiculo veiculo = new Veiculo();
        veiculo.setId(this.id());
        veiculo.setPlaca(this.placa());
        return veiculo;
    }

    @Override
    public String toString() {
        return "placa: " + placa;
    }
}
