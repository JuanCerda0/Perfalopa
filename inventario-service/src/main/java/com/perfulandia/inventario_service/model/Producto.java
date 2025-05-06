package com.perfulandia.inventario_service.model;

public class Producto {

	Long id_producto;
	String nombre_producto;
	int precio_producto;
	
	// Constructor vacio
	
	public Producto() {}
	
	public Producto(Long id_producto, String nombre_producto, int precio_producto) {
		
		this.id_producto = id_producto;
		this.nombre_producto = nombre_producto;
		this.precio_producto = precio_producto;
		
	}
	
	public Long getId_producto() {
		
		return id_producto;
	}
	
	public void setId_producto (Long id_producto) {
		
		this.id_producto = id_producto;
	}
	
	public String getNombre_producto () {
		
		return nombre_producto;
	}
	
	public void setNombre_producto (String nombre_producto) {
		
		this.nombre_producto = nombre_producto;
	}
	
	public int getPrecio_producto() {
		
		return precio_producto;
	}
	
	public void setPrecio_producto(int precio_producto) {
		
		this.precio_producto = precio_producto;
	}
	
}
