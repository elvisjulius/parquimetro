package com.parquimetro.usecase;

import com.parquimetro.dto.ControleDeEstacionamentoDTO;
import com.parquimetro.entity.Contato;
import com.parquimetro.service.CondutorService;
import com.parquimetro.service.ControleDeEstacionamentoService;
import com.parquimetro.service.SmsService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;
import java.util.List;

@Component
public class SchedulerUseCase {

    private final ControleDeEstacionamentoService controleDeEstacionamentoService;
    private final SmsService smsService;
    private final CondutorService condutorService;

    @Autowired
    public SchedulerUseCase(ControleDeEstacionamentoService controleDeEstacionamentoService, SmsService smsService, CondutorService condutorService) {
        this.controleDeEstacionamentoService = controleDeEstacionamentoService;
        this.smsService = smsService;
        this.condutorService = condutorService;
    }

    @Scheduled(cron = "0 * * * * ?")
    public void notificador() {

       List<ControleDeEstacionamentoDTO> lista = controleDeEstacionamentoService.findAll();


        for (ControleDeEstacionamentoDTO dto : lista) {
            //comunica uma vez quem tem hora de saida agendada 10 minutos antes de vencer o prazo
            if (dto.horaSaida() != null && !dto.notificado() && dto.horaSaida().isAfter(LocalDateTime.now().minusMinutes(10))) {
                smsService.sendSms(condutorService.findByVeiculoId(dto.veiculos().id()).getContatos().toArray(new Contato[0]));
            }

            //comunica uma vez quem não tem hora de saida agendada porém ja passou uma hora no estacionamento
            if (dto.horaSaida() == null && !dto.notificado() && dto.horaEntrada().isAfter(LocalDateTime.now().plusHours(1))) {
                smsService.sendSms(condutorService.findByVeiculoId(dto.veiculos().id()).getContatos().toArray(new Contato[0]));
            }
        }


    }
}