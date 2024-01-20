package com.parquimetro.service;

import com.parquimetro.dto.CondutorDTO;
import com.parquimetro.dto.VeiculoDTO;
import com.parquimetro.entity.Condutor;
import com.parquimetro.entity.Veiculo;
import com.parquimetro.repository.CondutorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;

@Service
public class CondutorService {
    private final CondutorRepository condutorRepository;

    @Autowired
    public CondutorService(CondutorRepository condutorRepository) {
        this.condutorRepository = condutorRepository;
    }


    @Transactional
    public CondutorDTO save(CondutorDTO condutorDTO) {
        return this.condutorRepository.save(condutorDTO.toEntity()).toDTO();
    }

    public CondutorDTO vincularNovoVeiculo(UUID id, VeiculoDTO veiculoDTO) {
        Optional<Condutor> condutorOptional = this.condutorRepository.findById(id);
        if (!condutorOptional.isPresent()) {
            throw new RuntimeException("Não Existe Condutores cadastrados com esse ID");
        }
        Condutor condutor = condutorOptional.get();
        Veiculo veiculo = veiculoDTO.toEntity();
        veiculo.setCondutor(condutor);
        condutor.getVeiculos().add(veiculo);

        condutor = this.condutorRepository.saveAndFlush(condutor);
        return condutor.toDTO();
    }
}
