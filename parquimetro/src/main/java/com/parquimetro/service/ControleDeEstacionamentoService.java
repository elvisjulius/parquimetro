package com.parquimetro.service;

import com.parquimetro.controller.exception.InvalidBusinessRules;
import com.parquimetro.dto.ControleDeEstacionamentoDTO;
import com.parquimetro.dto.ReciboDeEstacionamentoDTO;
import com.parquimetro.entity.Contato;
import com.parquimetro.entity.ControleDeEstacionamento;
import com.parquimetro.entity.Veiculo;
import com.parquimetro.repository.ControleDeEstacionamentoRepository;
import com.parquimetro.util.MetodoPagamento;
import com.parquimetro.util.TipoCobranca;
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
    private final SmsService smsService;


    @Autowired
    public ControleDeEstacionamentoService(ControleDeEstacionamentoRepository controleDeEstacionamentoRepository, CondutorService condutorService, SmsService smsService) {
        this.controleDeEstacionamentoRepository = controleDeEstacionamentoRepository;
        this.condutorService = condutorService;
        this.smsService = smsService;
    }


    @Transactional
    public ControleDeEstacionamentoDTO save(ControleDeEstacionamentoDTO controleDeEstacionamentoDTO) {
        if(controleDeEstacionamentoDTO.metodoPagamento().equals(MetodoPagamento.PIX) && !controleDeEstacionamentoDTO.tipoCobranca().equals(TipoCobranca.FIXA)){
            throw new InvalidBusinessRules("A opção PIX só está disponível para períodos de estacionamento fixos");
        }
        ControleDeEstacionamento entity = controleDeEstacionamentoDTO.toEntity();
        entity.setVeiculoUtilizado(this.condutorService.findVeiculoByPlaca(entity.getVeiculoUtilizado().getPlaca()));
        return this.controleDeEstacionamentoRepository.save(entity).toDTO();
    }

    public ControleDeEstacionamentoDTO buscaControleEstacionamento(UUID id) {
        Optional<ControleDeEstacionamento> controleDeEstacionamentoOptional = this.controleDeEstacionamentoRepository.findById(id);
        if (!controleDeEstacionamentoOptional.isPresent()) {
            throw new InvalidBusinessRules("Não Existe Controles de Estacionamento cadastrados com esse ID");
        }
        return controleDeEstacionamentoOptional.get().toDTO();
    }

    public List<ControleDeEstacionamentoDTO> findAll() {
        return this.controleDeEstacionamentoRepository.findAll().stream().map(ControleDeEstacionamento::toDTO).collect(Collectors.toList());
    }

    public ReciboDeEstacionamentoDTO cobrar(String placaVeiculoUtilizado) {
        ControleDeEstacionamento controleDeEstacionamento = this.controleDeEstacionamentoRepository.findByVeiculoUtilizadoPlaca(placaVeiculoUtilizado).orElseThrow();
        Double precoEstacionamentoHora = 5.0;

        controleDeEstacionamento.setPago(true);
        this.controleDeEstacionamentoRepository.save(controleDeEstacionamento);

        LocalDateTime horaSaida = controleDeEstacionamento.getHoraSaida() != null ? controleDeEstacionamento.getHoraSaida() : LocalDateTime.now();

        ReciboDeEstacionamentoDTO reciboDeEstacionamentoDTO = new ReciboDeEstacionamentoDTO(
                controleDeEstacionamento.getHoraEntrada(),
                controleDeEstacionamento.getHoraSaida(),
                controleDeEstacionamento.getTipoCobranca(),
                controleDeEstacionamento.getVeiculoUtilizado().toDTO(),
                controleDeEstacionamento.getMetodoPagamento(),
                precoEstacionamentoHora * calculadoraDiferencaEntreLocalDateTime(controleDeEstacionamento.getHoraEntrada(), horaSaida, controleDeEstacionamento.getTipoCobranca())
        );
        Veiculo veiculo = this.condutorService.findVeiculoByPlaca(placaVeiculoUtilizado);
        Contato[] arrayDeContatos = veiculo.getCondutor().getContatos().toArray(new Contato[0]);
        this.smsService.sendSms(reciboDeEstacionamentoDTO.toString(), arrayDeContatos);
        return reciboDeEstacionamentoDTO;
    }

    public static double calculadoraDiferencaEntreLocalDateTime(LocalDateTime start, LocalDateTime end, String tipoCobrança) {
        long diferencaEmMinutos = ChronoUnit.MINUTES.between(start, end);
        double diferencaEmHoras = (double) diferencaEmMinutos / 60;

        if (tipoCobrança == TipoCobranca.FIXA.getDescricao()) {
            return diferencaEmHoras;
        } else {
            return Math.floor(diferencaEmHoras);
        }
    }
}
