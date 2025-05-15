package com.inventario.servicio;

import com.inventario.modelo.Producto;
import com.inventario.repositorio.ProductoRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductoServicio {

    @Autowired
    private ProductoRepositorio productoRepositorio;

    public List<Producto> obtenerTodos() {
        return productoRepositorio.findAll();
    }

    public Producto obtenerPorId(Long id) {
        return productoRepositorio.findById(id)
                .orElseThrow(() -> new RuntimeException("Producto no encontrado con id: " + id));
    }

    public Producto crearProducto(Producto producto) {
        return productoRepositorio.save(producto);
    }

    public void eliminarProducto(Long id) {
        productoRepositorio.deleteById(id);
    }
}
