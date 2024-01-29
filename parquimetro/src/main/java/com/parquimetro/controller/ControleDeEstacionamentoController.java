package com.parquimetro.controller;

import com.parquimetro.controller.exception.StardardError;
import com.parquimetro.dto.*;
import com.parquimetro.service.CondutorService;
import com.parquimetro.service.ControleDeEstacionamentoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(value = "/controle", produces = {"application/json"})
@Tag(name = "Controle de Estacionamento")
public class ControleDeEstacionamentoController {
    private final ControleDeEstacionamentoService controleDeEstacionamentoService;

    @Autowired
    public ControleDeEstacionamentoController(ControleDeEstacionamentoService controleDeEstacionamentoService) {
        this.controleDeEstacionamentoService = controleDeEstacionamentoService;
    }

    @PostMapping
    @Operation(summary = "Registro de Condutores e Veículos", description = "Os condutores podem se registrar no sistema, associando seus dados pessoais, como nome, endereço e informações de contato.", method = "POST")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Cadastro do condutor realizado com sucesso"),
            @ApiResponse(responseCode = "422", description = "Dados de requisição inválida", content = {@Content(schema = @Schema(implementation = StardardError.class))}),
            @ApiResponse(responseCode = "400", description = "Parametros inválidos", content = {@Content(schema = @Schema(implementation = StardardError.class))}),
            @ApiResponse(responseCode = "500", description = "Erro ao realizar a busca pelo condutor", content = {@Content(schema = @Schema(implementation = StardardError.class))})
    })
    public ResponseEntity<ControleDeEstacionamentoDTO> save(@RequestBody @Valid ControleDeEstacionamentoDTO controleDeEstacionamentoDTO) {
        controleDeEstacionamentoDTO = this.controleDeEstacionamentoService.save(controleDeEstacionamentoDTO);
        return new ResponseEntity<>(controleDeEstacionamentoDTO, HttpStatus.CREATED);
    }


    @GetMapping("/{id}")
    @Operation(summary = "Registro de Condutores e Veículos", description = "Os condutores podem se registrar no sistema, associando seus dados pessoais, como nome, endereço e informações de contato.", method = "GET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Cadastro do condutor realizado com sucesso"),
            @ApiResponse(responseCode = "422", description = "Dados de requisição inválida", content = {@Content(schema = @Schema(implementation = StardardError.class))}),
            @ApiResponse(responseCode = "400", description = "Parametros inválidos", content = {@Content(schema = @Schema(implementation = StardardError.class))}),
            @ApiResponse(responseCode = "500", description = "Erro ao realizar a busca pelo condutor", content = {@Content(schema = @Schema(implementation = StardardError.class))})
    })
    public ResponseEntity<ControleDeEstacionamentoDTO> buscaControleEstacionamento(@PathVariable UUID id) {
        return ResponseEntity.ok(this.controleDeEstacionamentoService.buscaControleEstacionamento(id));
    }

    @GetMapping("/cobrar/{placaVeiculo}")
    @Operation(summary = "Registro de Condutores e Veículos", description = "Os condutores podem se registrar no sistema, associando seus dados pessoais, como nome, endereço e informações de contato.", method = "GET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Cadastro do condutor realizado com sucesso"),
            @ApiResponse(responseCode = "422", description = "Dados de requisição inválida", content = {@Content(schema = @Schema(implementation = StardardError.class))}),
            @ApiResponse(responseCode = "400", description = "Parametros inválidos", content = {@Content(schema = @Schema(implementation = StardardError.class))}),
            @ApiResponse(responseCode = "500", description = "Erro ao realizar a busca pelo condutor", content = {@Content(schema = @Schema(implementation = StardardError.class))})
    })
    public ResponseEntity<ReciboDeEstacionamentoDTO> cobrar(@PathVariable String placaVeiculo){
        return ResponseEntity.ok(this.controleDeEstacionamentoService.cobrar(placaVeiculo));
    }
}
