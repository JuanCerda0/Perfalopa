package com.perfulandia.inventario_service;

public class Perfume {

	Long id_perfume;
	String nombre_perfume;
	int precio_perfume;
	
	// Constructor vacio
	
	public Perfume() {}
	
	public Perfume(Long id_perfume, String nombre_perfume, int precio_perfume) {
		
		this.id_perfume = id_perfume;
		this.nombre_perfume = nombre_perfume;
		this.precio_perfume = precio_perfume;
		
	}
	
	public Long getId_perfume() {
		
		return id_perfume;
	}
	
	public void setId_perfume (Long id_perfume) {
		
		this.id_perfume = id_perfume;
	}
	
	public String getNombre_perfume () {
		
		return nombre_perfume;
	}
	
	public void setNombre_perfume (String nombre_perfume) {
		
		this.nombre_perfume = nombre_perfume;
	}
	
	public int getPrecio_perfume() {
		
		return precio_perfume;
	}
	
	public void setPrecio_perfume(int precio_perfume) {
		
		this.precio_perfume = precio_perfume;
	}
	
}
