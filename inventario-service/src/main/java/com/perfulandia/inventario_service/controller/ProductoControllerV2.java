package com.perfulandia.inventario_service.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.perfulandia.inventario_service.model.Producto;
import com.perfulandia.inventario_service.service.ProductoServiceV2;

@RestController
@RequestMapping("api/productos")
public class ProductoControllerV2 {

	@Autowired
	private ProductoServiceV2 productoServiceV2;

	@GetMapping
	public List<Producto> getAll() {

		return productoServiceV2.obtenerTodos();
	}
	
	@PostMapping
	public Producto create(@RequestBody Producto producto) {
		
		return productoServiceV2.guardar(producto);
	}

	@GetMapping ("/{id}")
	public ResponseEntity<Producto> getById(@PathVariable Long id) {
		
		return productoServiceV2.buscarPorId(id)
				.map(ResponseEntity::ok)
				.orElse(ResponseEntity.notFound().build());
	}
	
	@DeleteMapping("/{id}")
	public void delete(@PathVariable Long id) {
		
		productoServiceV2.eliminar(id);
	}
	
	@GetMapping("/sucursal/{sucursal}")
	public List<Producto> getBySucursal(@PathVariable String sucursal) {
		
		return productoServiceV2.buscarPorSucursal(sucursal);
	}
	
}
