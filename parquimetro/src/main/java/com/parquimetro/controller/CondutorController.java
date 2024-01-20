package com.parquimetro.controller;

import com.parquimetro.dto.CondutorDTO;
import com.parquimetro.dto.ContatoDTO;
import com.parquimetro.dto.EnderecoDTO;
import com.parquimetro.dto.VeiculoDTO;
import com.parquimetro.service.CondutorService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/condutor")
public class CondutorController {
    private final CondutorService condutorService;

    @Autowired
    public CondutorController(CondutorService condutorService) {
        this.condutorService = condutorService;
    }

    @PostMapping
    public ResponseEntity<CondutorDTO> save(@RequestBody @Valid CondutorDTO condutorDTO) {
        condutorDTO = this.condutorService.save(condutorDTO);
        return new ResponseEntity<>(condutorDTO, HttpStatus.CREATED);
    }

    @PutMapping("vincularNovoVeiculo/{id}")
    public ResponseEntity<CondutorDTO> vincularNovoVeiculo(@PathVariable UUID id, @RequestBody @Valid VeiculoDTO veiculoDTO) {
        CondutorDTO condutorDTO = this.condutorService.vincularNovoVeiculo(id, veiculoDTO);
        return new ResponseEntity<>(condutorDTO, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<CondutorDTO> findAll() {
        EnderecoDTO enderecoDTO = new EnderecoDTO(null, "55642-835", "178", "A", "Nossa Senhora das Graças", "Gravatá", "PE", "55642-835");
        ContatoDTO contatoDTO = new ContatoDTO(null, "55", "81", "99594-1472");
        VeiculoDTO veiculoDTO = new VeiculoDTO(null, "MZS-9397");
        CondutorDTO condutorDTO = new CondutorDTO(null, "Edson Theo Vinicius Lopes", enderecoDTO, List.of(contatoDTO), List.of(veiculoDTO));
        return ResponseEntity.ok(condutorDTO);
    }
}
