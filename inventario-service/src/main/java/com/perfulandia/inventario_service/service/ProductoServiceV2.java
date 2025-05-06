package com.perfulandia.inventario_service.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.perfulandia.inventario_service.model.Producto;
import com.perfulandia.inventario_service.repository.ProductoRepository;

@Service
public class ProductoServiceV2 {

	@Autowired
	private ProductoRepository productoRepository;
	
	public List<Producto> obtenerTodos() {
		
		return productoRepository.findAll();	
		}
	
	public Producto guardar(Producto producto) {
		
		return productoRepository.save(producto);
	}
	
	public Optional<Producto> buscarPorId(Long id) {
		
		return productoRepository.findById(id);
	}
	
	public void eliminar(Long id) {
		
		productoRepository.deleteById(id);
	}
	
	public List<Producto> buscarPorSucursal(String sucursal) {
		
		return productoRepository.findBySucursal(sucursal);
	}
	
}
