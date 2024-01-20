package com.parquimetro.dto;

import com.parquimetro.entity.Endereco;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record EnderecoDTO(
        UUID id,
        @NotNull(message = "Logradouro do Endereço não pode estar em nulo.")
        @NotBlank(message = "Logradouro do Endereço não pode estar em Branco.")
        String logradouro,
        @NotNull(message = "Numero do Endereço não pode estar em nulo.")
        @NotBlank(message = "Numero do Endereço não pode estar em Branco.")
        String numero,

        String complemento,
        @NotNull(message = "Bairro do Endereço não pode estar em nulo.")
        @NotBlank(message = "Bairro do Endereço não pode estar em Branco.")
        String bairro,
        @NotNull(message = "Cidade do Endereço não pode estar em nulo.")
        @NotBlank(message = "Cidade do Endereço não pode estar em Branco.")
        String cidade,
        @NotNull(message = "Estado do Endereço não pode estar em nulo.")
        @NotBlank(message = "Estado do Endereço não pode estar em Branco.")
        String estado,
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