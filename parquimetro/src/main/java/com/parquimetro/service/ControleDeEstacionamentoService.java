package com.parquimetro.service;

import com.parquimetro.dto.ReciboDeEstacionamentoDTO;
import com.parquimetro.dto.ControleDeEstacionamentoDTO;
import com.parquimetro.entity.ControleDeEstacionamento;
import com.parquimetro.repository.ControleDeEstacionamentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class ControleDeEstacionamentoService {
    private final ControleDeEstacionamentoRepository controleDeEstacionamentoRepository;
    private final CondutorService condutorService;

    @Autowired
    public ControleDeEstacionamentoService(ControleDeEstacionamentoRepository controleDeEstacionamentoRepository, CondutorService condutorService) {
        this.controleDeEstacionamentoRepository = controleDeEstacionamentoRepository;
        this.condutorService = condutorService;
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

    public List<ControleDeEstacionamentoDTO> findAll(){
        return this.controleDeEstacionamentoRepository.findAll().stream().map(ControleDeEstacionamento::toDTO).collect(Collectors.toList());
    }

    public ReciboDeEstacionamentoDTO cobrar(String placaVeiculoUtilizado){
        ControleDeEstacionamento controleDeEstacionamento = this.controleDeEstacionamentoRepository.findByVeiculoUtilizadoPlaca(placaVeiculoUtilizado).orElseThrow();
        Double precoEstacionamentoHora = 5.0;

        controleDeEstacionamento.setPago(true);
        this.controleDeEstacionamentoRepository.save(controleDeEstacionamento);

        LocalDateTime horaSaida = controleDeEstacionamento.getHoraSaida() != null ? controleDeEstacionamento.getHoraSaida() :  LocalDateTime.now();



        return new ReciboDeEstacionamentoDTO(
                controleDeEstacionamento.getHoraEntrada(),
                controleDeEstacionamento.getHoraSaida(),
                controleDeEstacionamento.getTipoCobranca(),
                controleDeEstacionamento.getVeiculoUtilizado().toDTO(),
                controleDeEstacionamento.getMetodoPagamento(),
                precoEstacionamentoHora*calculadoraDiferencaEntreLocalDateTime(controleDeEstacionamento.getHoraEntrada(),horaSaida, controleDeEstacionamento.getTipoCobranca())
        );
    }

    public static double calculadoraDiferencaEntreLocalDateTime(LocalDateTime start, LocalDateTime end, String tipoCobrança) {
        long diferencaEmMinutos = ChronoUnit.MINUTES.between(start, end);
        double diferencaEmHoras = (double) diferencaEmMinutos / 60;

        if(tipoCobrança == "fixo") {
            return diferencaEmHoras;
        }
        else{
            return  Math.floor(diferencaEmHoras);
        }
    }
}
