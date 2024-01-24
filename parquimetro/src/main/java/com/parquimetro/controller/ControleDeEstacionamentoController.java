package com.parquimetro.controller;

import com.parquimetro.dto.*;
import com.parquimetro.service.CondutorService;
import com.parquimetro.service.ControleDeEstacionamentoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/controle")
public class ControleDeEstacionamentoController {
    private final ControleDeEstacionamentoService controleDeEstacionamentoService;

    @Autowired
    public ControleDeEstacionamentoController(ControleDeEstacionamentoService controleDeEstacionamentoService) {
        this.controleDeEstacionamentoService = controleDeEstacionamentoService;
    }

    @PostMapping
    public ResponseEntity<ControleDeEstacionamentoDTO> save(@RequestBody @Valid ControleDeEstacionamentoDTO controleDeEstacionamentoDTO) {
        controleDeEstacionamentoDTO = this.controleDeEstacionamentoService.save(controleDeEstacionamentoDTO);
        return new ResponseEntity<>(controleDeEstacionamentoDTO, HttpStatus.CREATED);
    }


    @GetMapping("/{id}")
    public ResponseEntity<ControleDeEstacionamentoDTO> buscaControleEstacionamento(@PathVariable UUID id) {

        return ResponseEntity.ok(this.controleDeEstacionamentoService.buscaControleEstacionamento(id));
    }
}
