package com.parquimetro;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition(info = @Info(title = "Sistema de Parquímetro", version = "1", description = "O novo sistema de parquímetro foi projetado para lidar com a demanda crescente de estacionamento na\n" +
		"cidade. Ele oferece funcionalidades tais, como registro de condutores e veículos, controle de tempo estacionado,\n" +
		"opções flexíveis de pagamento e emissão de recibos."))

public class ParquimetroApplication {

	public static void main(String[] args) {
		SpringApplication.run(ParquimetroApplication.class, args);
	}

}
