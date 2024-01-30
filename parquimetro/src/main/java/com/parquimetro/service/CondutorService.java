package com.parquimetro.service;

import com.parquimetro.controller.exception.RecordNotFoundException;
import com.parquimetro.dto.CondutorDTO;
import com.parquimetro.dto.VeiculoDTO;
import com.parquimetro.entity.Condutor;
import com.parquimetro.entity.Veiculo;
import com.parquimetro.repository.CondutorRepository;
import com.parquimetro.repository.VeiculoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;

@Service
public class CondutorService {
    private final CondutorRepository condutorRepository;
    private final SmsService smsService;
    private final VeiculoRepository veiculoRepository;

    @Autowired
    public CondutorService(CondutorRepository condutorRepository, SmsService smsService, VeiculoRepository veiculoRepository) {
        this.condutorRepository = condutorRepository;
        this.smsService = smsService;
        this.veiculoRepository = veiculoRepository;
    }


    @Transactional
    public CondutorDTO save(CondutorDTO condutorDTO) {
        return this.condutorRepository.save(condutorDTO.toEntity()).toDTO();
    }

    @Transactional
    public CondutorDTO vincularNovoVeiculo(UUID id, VeiculoDTO veiculoDTO) {
        Condutor condutor = this.condutorRepository.findById(id).orElseThrow(() -> new RecordNotFoundException("Não existe condutores cadastrados com esse ID"));
        Veiculo veiculo = veiculoDTO.toEntity();
        veiculo.setCondutor(condutor);
        condutor.getVeiculos().add(veiculo);

        condutor = this.condutorRepository.saveAndFlush(condutor);
        return condutor.toDTO();
    }

    public CondutorDTO findCondutorById(UUID id) {
        Condutor condutor = this.condutorRepository.findById(id).orElseThrow(() -> new RecordNotFoundException("Não existe condutores cadastrados com esse ID"));
        return condutor.toDTO();
    }

    public Condutor findByVeiculoId(UUID veiculoId){
        return this.condutorRepository.findByVeiculoId(veiculoId).orElseThrow(() -> new RecordNotFoundException("Não existe condutores cadastrados com esse ID de veiculo"));
    }

    public Veiculo findVeiculoByPlaca(String veiculoPlaca){
        return this.veiculoRepository.findByPlaca(veiculoPlaca).orElseThrow(() -> new RecordNotFoundException("Não existe condutores cadastrados com essa placa de veiculo"));
    }
}
