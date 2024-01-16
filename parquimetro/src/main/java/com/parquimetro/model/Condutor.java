package com.parquimetro.model;

import lombok.Data;

import java.util.List;
import java.util.UUID;

@Data
public class Condutor {

    private UUID id;
    private String nome;
    private Endereco endereco;
    private List<Contato> contatos;
}
