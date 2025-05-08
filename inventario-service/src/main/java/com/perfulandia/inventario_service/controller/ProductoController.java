package com.perfulandia.inventario_service.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.perfulandia.inventario_service.model.Producto;
import com.perfulandia.inventario_service.service.Producto_Service;


@RestController
@RequestMapping("/inventario")
public class ProductoController {

	@Autowired
	private Producto_Service servicio;
	
	@PostMapping
	public Producto crear(@RequestBody Producto producto) {
		
		return servicio.crear(producto);
	}
	
	@GetMapping
	public List<Producto> listarTodos() {
		return servicio.obtenerTodos();
	}
	
	@GetMapping("/{id}")
	public Producto obtenerPorId(@PathVariable Long id) {
		
		return servicio.obtenerPorId(id);
	}
	
	@PutMapping("/{id}")
	public Producto actualizar(@PathVariable Long id, @RequestBody Producto producto) {
		
		return servicio.actualizar(id, producto);
	}
	
	@DeleteMapping("/{id}")
	public String eliminar(@PathVariable Long id) {
		
		boolean eliminado = servicio.eliminar(id);
		return eliminado ? "Eliminado correctamente" : "No encotrado";
	}
	
	
	
	
	
	
	
	
	
	
	
}
