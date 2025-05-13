package com.example.sistema.de.ventas.feing;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.perfumelandia.clienteservice.model.Cliente;

@FeignClient(name = "cliente-service", url = "http://localhost:8081")
public interface ClienteServiceClient {
	
	@GetMapping("/clientes/{id}")
	Cliente obtenerCliente(@PathVariable("id") Long id);
}
