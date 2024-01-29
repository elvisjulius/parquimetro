package com.parquimetro.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.parquimetro.entity.Contato;
import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.Parameter;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

import java.util.UUID;

public record ContatoDTO(
        @Hidden
        @JsonInclude(JsonInclude.Include.NON_NULL)
        UUID id,
        @NotNull(message = "codigoPais não pode estar em nulo.")
        @NotBlank(message = "codigoPais não pode estar em Branco.")
        @Pattern(regexp = "\\d+", message = "codigoPais deve conter apenas caracteres não numéricos.")
        @Size(max = 2, message = "codigoPais não pode ter mais de 255 caracteres.")
        String codigoPais,
        @NotNull(message = "codigoArea não pode estar em nulo.")
        @NotBlank(message = "codigoArea não pode estar em Branco.")
        @Pattern(regexp = "\\d+", message = "codigoArea deve conter apenas caracteres não numéricos.")
        @Size(max = 2, message = "codigoArea não pode ter mais de 255 caracteres.")
        String codigoArea,
        @NotNull(message = "numero não pode estar em nulo.")
        @NotBlank(message = "numero não pode estar em Branco.")
        @Pattern(regexp = "\\d+", message = "numero deve conter apenas caracteres não numéricos.")
        @Size(max = 9, message = "numero não pode ter mais de 255 caracteres.")
        String numero) {
    public Contato toEntity() {
        Contato contato = new Contato();
        contato.setId(this.id());
        contato.setCodigoPais(this.codigoPais());
        contato.setCodigoArea(this.codigoArea());
        contato.setNumero(this.numero());
        return contato;
    }
}
