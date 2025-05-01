package com.perfulandia.inventario_service.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Service;

import com.perfulandia.inventario_service.Perfume;

@Service
public class Perfume_Service {

	private HashMap<Long, Perfume> perfumes = new HashMap<>();
	private long contadorId = 1;
	
	public Perfume crear (Perfume perfume) { 
		
		perfume.setId_perfume(contadorId++);
		perfumes.put(perfume.getId_perfume(), perfume);
		return perfume;
	}
	
	public List<Perfume> obtenerTodos() {
		
		return new ArrayList(perfumes.values());
	}
	
	public Perfume obtenerPorId(Long id_perfume) {
		
		return perfumes.get(id_perfume);
	}
	
	public Perfume actualizar(Long id_perfume, Perfume actualizado) {
		
		Perfume existente = perfumes.get(id_perfume);
		
		if (existente != null) {
			
			existente.setNombre_perfume(actualizado.getNombre_perfume());
			existente.setPrecio_perfume(actualizado.getPrecio_perfume());
		}
		
		return existente;
	}
	
	public boolean eliminar(Long id) {
		
		return perfumes.remove(id) != null;
	}
	
	
}


