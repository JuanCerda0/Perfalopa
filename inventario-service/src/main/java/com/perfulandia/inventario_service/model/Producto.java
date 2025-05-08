package com.perfulandia.inventario_service.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Producto {

	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)	
	private Long id_producto;
	
	private String nombre_producto;
	private int cantidad_producto;
	private int precio_producto;
	
	private String proveedor_producto;
	private String sucursal;
	
	// Constructor vacio
	
	public Producto() {}
	
	// Constructores
	
	public Producto(Long id_producto, String nombre_producto, int precio_producto) {
		
		this.id_producto = id_producto;
		this.nombre_producto = nombre_producto;
		this.precio_producto = precio_producto;
		
	}
	
	public Producto(Long id_producto, String nombre_producto, int cantidad_producto, int precio_producto,
			String proveedor_producto, String sucursal) {
		super();
		this.id_producto = id_producto;
		this.nombre_producto = nombre_producto;
		this.cantidad_producto = cantidad_producto;
		this.precio_producto = precio_producto;
		this.proveedor_producto = proveedor_producto;
		this.sucursal = sucursal;
	}

	// Getters y Setters
	
	public Long getId_producto() {
		return id_producto;
	}

	public void setId_producto(Long id_producto) {
		this.id_producto = id_producto;
	}

	public String getNombre_producto() {
		return nombre_producto;
	}

	public void setNombre_producto(String nombre_producto) {
		this.nombre_producto = nombre_producto;
	}

	public int getCantidad_producto() {
		return cantidad_producto;
	}

	public void setCantidad_producto(int cantidad_producto) {
		this.cantidad_producto = cantidad_producto;
	}

	public int getPrecio_producto() {
		return precio_producto;
	}

	public void setPrecio_producto(int precio_producto) {
		this.precio_producto = precio_producto;
	}

	public String getProveedor_producto() {
		return proveedor_producto;
	}

	public void setProveedor_producto(String proveedor_producto) {
		this.proveedor_producto = proveedor_producto;
	}

	public String getsucursal() {
		return sucursal;
	}

	public void setsucursal(String sucursal) {
		this.sucursal = sucursal;
	}
	
	
}
