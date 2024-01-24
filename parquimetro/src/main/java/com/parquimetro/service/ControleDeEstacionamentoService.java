package com.parquimetro.service;

import com.parquimetro.dto.CondutorDTO;
import com.parquimetro.dto.ControleDeEstacionamentoDTO;
import com.parquimetro.dto.VeiculoDTO;
import com.parquimetro.entity.Condutor;
import com.parquimetro.entity.ControleDeEstacionamento;
import com.parquimetro.entity.Veiculo;
import com.parquimetro.repository.ControleDeEstacionamentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;

@Service
public class ControleDeEstacionamentoService {
    private final ControleDeEstacionamentoRepository controleDeEstacionamentoRepository;

    @Autowired
    public ControleDeEstacionamentoService(ControleDeEstacionamentoRepository controleDeEstacionamentoRepository) {
        this.controleDeEstacionamentoRepository = controleDeEstacionamentoRepository;
    }


    @Transactional
    public ControleDeEstacionamentoDTO save(ControleDeEstacionamentoDTO controleDeEstacionamentoDTO) {
        return this.controleDeEstacionamentoRepository.save(controleDeEstacionamentoDTO.toEntity()).toDTO();
    }

    public ControleDeEstacionamentoDTO buscaControleEstacionamento(UUID id) {
        Optional<ControleDeEstacionamento> controleDeEstacionamentoOptional = this.controleDeEstacionamentoRepository.findById(id);
        if (!controleDeEstacionamentoOptional.isPresent()) {
            throw new RuntimeException("Não Existe Controles de Estacionamento cadastrados com esse ID");
        }
        return controleDeEstacionamentoOptional.get().toDTO();
    }
}
