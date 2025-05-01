package com.perfulandia.inventario_service.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import com.perfulandia.inventario_service.Perfume;
import com.perfulandia.inventario_service.service.Perfume_Service;


@RestController
@RequestMapping("/inventario")
public class PerfumesController {

	@Autowired
	private Perfume_Service servicio;
	
	@PostMapping
	public Perfume crear(@RequestBody Perfume perfume) {
		
		return servicio.crear(perfume);
	}
	
	@GetMapping
	public List<Perfume> listarTodos() {
		return servicio.obtenerTodos();
	}
	
	@GetMapping("/{id}")
	public Perfume obtenerPorId(@PathVariable Long id) {
		
		return servicio.obtenerPorId(id);
	}
	
	@PutMapping("/{id}")
	public Perfume actualizar(@PathVariable Long id, @RequestBody Perfume perfume) {
		
		return servicio.actualizar(id, perfume);
	}
	
	@DeleteMapping("/{id}")
	public String eliminar(@PathVariable Long id) {
		
		boolean eliminado = servicio.eliminar(id);
		return eliminado ? "Eliminado correctamente" : "No encotrado";
	}
	
	
	
	
	
	
	
	
	
	
	
}
