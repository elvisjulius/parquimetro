package com.parquimetro.dto;

import com.parquimetro.entity.Contato;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record ContatoDTO(
        UUID id,
        @NotNull(message = "codigoPais não pode estar em nulo.")
        @NotBlank(message = "codigoPais não pode estar em Branco.")
        String codigoPais,
        @NotNull(message = "codigoArea não pode estar em nulo.")
        @NotBlank(message = "codigoArea não pode estar em Branco.")
        String codigoArea,
        @NotNull(message = "numero não pode estar em nulo.")
        @NotBlank(message = "numero não pode estar em Branco.")
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
