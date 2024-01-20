package com.parquimetro.dto;

import com.parquimetro.entity.Condutor;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public record CondutorDTO(
        UUID id,
        @NotNull(message = "Nome do Condutor não pode estar em nulo.")
        @NotBlank(message = "Nome do Condutor não pode estar em Branco.")
        String nome,
        @NotNull(message = "Endereço do Condutor não ser nulo")
        EnderecoDTO endereco,
        @NotNull(message = "Telefone de contato do Condutor não ser nulo")
        @NotEmpty(message = "Telefone de contato do Condutor não ser vazia")
        List<ContatoDTO> contatos,
        @NotNull(message = "Veiculos do  Condutor não ser nulo")
        @NotEmpty(message = "Veiculos do Condutor não ser vazia")
        List<VeiculoDTO> veiculos) {
    public Condutor toEntity() {
        Condutor condutor = new Condutor();
        condutor.setId(this.id());
        condutor.setNome(this.nome());
        condutor.setEndereco(this.endereco().toEntity());
        condutor.setContatos(this.contatos().stream().map(ContatoDTO::toEntity).collect(Collectors.toList()));
        condutor.setVeiculos(this.veiculos().stream().map(VeiculoDTO::toEntity).collect(Collectors.toList()));

        condutor.getVeiculos().forEach(veiculo -> veiculo.setCondutor(condutor));
        condutor.getContatos().forEach(contato -> contato.setCondutor(condutor));
        return condutor;
    }
}
