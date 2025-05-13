package com.example.sistema.de.ventas.feing;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;

import com.perfulandia.inventario_service.model.Producto;

@FeignClient(name = "inventacio-service", url = "http://localhost:8082")
public interface InventarioServiceClient {

	@GetMapping("/producto/{id}")
	Producto getProducto(@PathVariable("id") Long id);
	
	@PutMapping("/producto/{id}/stock")
	void actualizarStock(@PathVariable("id") Long id);
	
	
}



