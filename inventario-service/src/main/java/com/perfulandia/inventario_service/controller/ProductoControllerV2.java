package com.perfulandia.inventario_service.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.perfulandia.inventario_service.repository.ProductoRepository;

@Service
public class ProductoControllerV2 {

	@Autowired
	private ProductoRepository productoRepository;
	
	
	
}
