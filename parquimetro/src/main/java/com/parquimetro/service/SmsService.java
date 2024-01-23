package com.parquimetro.service;

import com.parquimetro.entity.Contato;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class SmsService {
    @Value("${twilio.sid}")
    private String twilioSid;

    @Value("${twilio.key}")
    private String twilioKey;

    @Value("${twilio.phone.from}")
    private String twilioPhoneFrom;


    public void sendSms(Contato ...contatos) {
        if (twilioSid == null || twilioKey == null || twilioPhoneFrom == null) {
            System.out.println("As variáveis de ambiente não foram fornecidas. O serviço twilio está indisponível.");
            return;
        }
        Twilio.init(twilioSid, twilioKey);
        final PhoneNumber from = new PhoneNumber(twilioPhoneFrom);
        for(Contato telefone : contatos){
            final String telefoneNumber = telefone.getCodigoPais() + telefone.getCodigoArea() + telefone.getNumero();
            PhoneNumber to = new PhoneNumber(telefoneNumber);
            Message message = Message.creator(to, from, "Você se cadastrou no sistema e sempre será notificado havendo alguma cobraça").create();
            System.out.println(message.getSid());
        }
    }

}
