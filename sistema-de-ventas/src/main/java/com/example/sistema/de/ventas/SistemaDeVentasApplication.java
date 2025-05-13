package com.example.sistema.de.ventas;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients(basePackages = "com.example.sistema.de.ventas.feign")
public class SistemaDeVentasApplication {

	public static void main(String[] args) {
		SpringApplication.run(SistemaDeVentasApplication.class, args);
	}

}
