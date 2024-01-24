package com.parquimetro.controller;

import com.parquimetro.controller.exception.StardardError;
import com.parquimetro.dto.CondutorDTO;
import com.parquimetro.dto.VeiculoDTO;
import com.parquimetro.service.CondutorService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping(value = "/condutor", produces = {"application/json"})
@Tag(name = "Condutor")
public class CondutorController {
    private final CondutorService condutorService;

    @Autowired
    public CondutorController(CondutorService condutorService) {
        this.condutorService = condutorService;
    }

    @PostMapping
    @Operation(summary = "Registro de Condutores e Veículos", description = "Os condutores podem se registrar no sistema, associando seus dados pessoais, como nome, endereço e informações de contato.", method = "POST")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Cadastro do condutor realizado com sucesso"),
            @ApiResponse(responseCode = "422", description = "Dados de requisição inválida", content = {@Content(schema = @Schema(implementation = StardardError.class))}),
            @ApiResponse(responseCode = "400", description = "Parametros inválidos", content = {@Content(schema = @Schema(implementation = StardardError.class))}),
            @ApiResponse(responseCode = "500", description = "Erro ao realizar a busca pelo condutor", content = {@Content(schema = @Schema(implementation = StardardError.class))})
    })
    public ResponseEntity<CondutorDTO> save(@RequestBody @Valid CondutorDTO condutorDTO) {
        condutorDTO = this.condutorService.save(condutorDTO);
        return new ResponseEntity<>(condutorDTO, HttpStatus.CREATED);
    }

    @GetMapping("{id}")
    @Operation(summary = "Endpoint para buscar dados do condutor com base em um ID fornecido", description = "Esté metodo tem como finalidade permitir um condutor concultar suas informações cadastrais.", method = "GET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Consulta do condutor realizado com sucesso"),
            @ApiResponse(responseCode = "422", description = "Dados de requisição inválida", content = {@Content(schema = @Schema(implementation = StardardError.class))}),
            @ApiResponse(responseCode = "400", description = "Parametros inválidos", content = {@Content(schema = @Schema(implementation = StardardError.class))}),
            @ApiResponse(responseCode = "500", description = "Erro ao realizar a busca pelo condutor", content = {@Content(schema = @Schema(implementation = StardardError.class))})
    })
    public ResponseEntity<CondutorDTO> findCondutorById(@PathVariable UUID id) {
        CondutorDTO condutorDTO = this.condutorService.findCondutorById(id);
        return new ResponseEntity<>(condutorDTO, HttpStatus.CREATED);
    }

    @PutMapping("{id}/vincularNovoVeiculo")
    @Operation(summary = "Registro de Veículos", description = "Um condutor pode vincular vários veículos à sua conta, facilitando o gerenciamento de múltiplos veículos", method = "PUT")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Cadastro do condutor realizado com sucesso"),
            @ApiResponse(responseCode = "422", description = "Dados de requisição inválida", content = {@Content(schema = @Schema(implementation = StardardError.class))}),
            @ApiResponse(responseCode = "400", description = "Parametros inválidos", content = {@Content(schema = @Schema(implementation = StardardError.class))}),
            @ApiResponse(responseCode = "500", description = "Erro ao realizar a busca pelo condutor", content = {@Content(schema = @Schema(implementation = StardardError.class))})
    })
    public ResponseEntity<CondutorDTO> vincularNovoVeiculo(@PathVariable UUID id, @RequestBody @Valid VeiculoDTO veiculoDTO) {
        CondutorDTO condutorDTO = this.condutorService.vincularNovoVeiculo(id, veiculoDTO);
        return new ResponseEntity<>(condutorDTO, HttpStatus.CREATED);
    }

}