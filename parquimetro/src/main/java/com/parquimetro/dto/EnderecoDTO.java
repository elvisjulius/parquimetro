package com.parquimetro.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.parquimetro.entity.Endereco;
import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record EnderecoDTO(
        @Hidden
        @JsonInclude(JsonInclude.Include.NON_NULL)
        UUID id,
        @Schema(example = "Rua Hego Lubeck")
        @NotNull(message = "Logradouro do Endereço não pode estar em nulo.")
        @NotBlank(message = "Logradouro do Endereço não pode estar em Branco.")
        String logradouro,
        @NotNull(message = "Numero do Endereço não pode estar em nulo.")
        @NotBlank(message = "Numero do Endereço não pode estar em Branco.")
        String numero,
        @Schema(example = "Casa 2")
        String complemento,
        @Schema(example = "Jardim Veneza")
        @NotNull(message = "Bairro do Endereço não pode estar em nulo.")
        @NotBlank(message = "Bairro do Endereço não pode estar em Branco.")
        String bairro,
        @Schema(example = "Sinop")
        @NotNull(message = "Cidade do Endereço não pode estar em nulo.")
        @NotBlank(message = "Cidade do Endereço não pode estar em Branco.")
        String cidade,
        @Schema(example = "Mato Grosso")
        @NotNull(message = "Estado do Endereço não pode estar em nulo.")
        @NotBlank(message = "Estado do Endereço não pode estar em Branco.")
        String estado,
        @Schema(example = "78554-166")
        @NotNull(message = "Cep do Endereço não pode estar em nulo.")
        @NotBlank(message = "Cep do Endereço não pode estar em Branco.")
        String cep
) {

    public Endereco toEntity() {
        Endereco endereco = new Endereco();
        endereco.setId(this.id());
        endereco.setLogradouro(this.logradouro());
        endereco.setNumero(this.numero());
        endereco.setComplemento(this.complemento());
        endereco.setBairro(this.bairro());
        endereco.setCidade(this.cidade());
        endereco.setEstado(this.estado());
        endereco.setCep(this.cep());
        return endereco;
    }
}