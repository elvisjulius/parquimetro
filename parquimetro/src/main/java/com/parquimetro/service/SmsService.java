package com.parquimetro.service;

import com.parquimetro.entity.Contato;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import org.apache.commons.lang3.StringUtils;
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


    public void sendSms(String msg, Contato... contatos) {
        if (StringUtils.isBlank(twilioSid) && StringUtils.isBlank(twilioKey) && StringUtils.isBlank(twilioPhoneFrom)) {
            System.out.println("As variáveis de ambiente não foram fornecidas. O serviço twilio está indisponível.");
            return;
        }
        try {
            Twilio.init(twilioSid, twilioKey);
            final PhoneNumber from = new PhoneNumber(twilioPhoneFrom);
            for (Contato telefone : contatos) {
                final String telefoneNumber = telefone.getCodigoPais() + telefone.getCodigoArea() + telefone.getNumero();
                if (validarNumeroTelefone(telefoneNumber)) {
                    PhoneNumber to = new PhoneNumber(telefoneNumber);
                    System.out.println(msg);
                    Message message = Message.creator(to, from, msg).create();
                    System.out.println(message.getSid());
                }
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public boolean validarNumeroTelefone(String numero) {
        numero = numero.replaceAll("[\\s()-]", "");

        if ("".equals(numero)) {
            return false;
        }
        try {
            com.twilio.rest.lookups.v1.PhoneNumber.fetcher(new com.twilio.type.PhoneNumber(numero)).fetch();
            return true;
        } catch (Exception e) {
            System.out.println(e);
        }
        return false;
    }
}