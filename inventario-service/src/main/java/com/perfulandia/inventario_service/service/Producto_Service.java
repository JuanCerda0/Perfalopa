package com.perfulandia.inventario_service.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Service;

import com.perfulandia.inventario_service.model.Producto;

@Service
public class Producto_Service {

	private HashMap<Long, Producto> productos = new HashMap<>();
	private long contadorId = 1;
	
	public Producto crear (Producto producto) { 
		
		producto.setId_producto(contadorId++);
		productos.put(producto.getId_producto(), producto);
		return producto;
	}
	
	public List<Producto> obtenerTodos() {
		
		return new ArrayList(productos.values());
	}
	
	public Producto obtenerPorId(Long id_producto) {
		
		return productos.get(id_producto);
	}
	
	public Producto actualizar(Long id_producto, Producto actualizado) {
		
		Producto existente = productos.get(id_producto);
		
		if (existente != null) {
			
			existente.setNombre_producto(actualizado.getNombre_producto());
			existente.setPrecio_producto(actualizado.getPrecio_producto());
		}
		
		return existente;
	}
	
	public boolean eliminar(Long id) {
		
		return productos.remove(id) != null;
	}
	
	
}


