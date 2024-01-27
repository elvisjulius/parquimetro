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
import java.util.TimeZone;

@Component
public class Scheduler {

    private final ControleDeEstacionamentoService controleDeEstacionamentoService;
    private final SmsService smsService;
    private final CondutorService condutorService;

    @Autowired
    public Scheduler(ControleDeEstacionamentoService controleDeEstacionamentoService, SmsService smsService, CondutorService condutorService) {
        this.controleDeEstacionamentoService = controleDeEstacionamentoService;
        this.smsService = smsService;
        this.condutorService = condutorService;
    }

    @Scheduled(cron = "0 * * * * ?")
    public void notificador() {

       List<ControleDeEstacionamentoDTO> lista = controleDeEstacionamentoService.findAll();

        for (ControleDeEstacionamentoDTO dto : lista) {
            if (dto.horaSaida() != null && !dto.notificado() && dto.horaSaida().isAfter(LocalDateTime.now().minusMinutes(10))) {
                smsService.sendSms(condutorService.findByVeiculoId(dto.veiculos().id()).getContatos().toArray(new Contato[0]));
            }
        }


    }
}