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
    @Operation(summary = "Registro de Entrada e Saída do Estacionamento", description = "Este endpoint registra a entrada do veículo, *PARA TESTAR A GERAÇÃO DO RECIBO, O ATRIBUTO horaSaida PODE SER INFORMADO.*\n" +
            "O sistema permite iniciar o período de estacionamento, oferecendo opções de tempo fixo ou por hora.\n" +
            "Para períodos fixos, o sistema requer que o condutor indique a duração desejada no momento do registro.\n" +
            "Para períodos variáveis, o sistema inicia o tempo de estacionamento automaticamente.\n" +
            "A opção PIX só está disponível para períodos de estacionamento fixos", method = "POST")
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
    @Operation(summary = "Busca Controle Estacionamento", description = "Este metodo retorna os dados do estacionamento e do veículo estacionado.", method = "GET")
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
    @Operation(summary = "Finalizar cobrança e Emitir Recibo", description = "Esse metodo, efetua a cobrança do estacionamento e emite o recibo", method = "GET")
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
