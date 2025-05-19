package com.inventario.controlador;

import com.inventario.modelo.Producto;
import com.inventario.servicio.ProductoServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/productos")
public class ProductoControlador {

    @Autowired
    private ProductoServicio productoServicio;

    @GetMapping
    public List<Producto> obtenerTodos() {
        return productoServicio.obtenerTodos();
    }

    @GetMapping("/{id}")
    public Producto obtenerPorId(@PathVariable Long id) {
        return productoServicio.obtenerPorId(id);
    }

    @PostMapping
    public Producto crear(@RequestBody Producto producto) {
        return productoServicio.crearProducto(producto);
    }

    @PutMapping("/{id}")
    public Producto actualizar(@PathVariable Long id, @RequestBody Producto productoActualizado) {
        Producto productoExistente = productoServicio.obtenerPorId(id);
        productoExistente.setNombre(productoActualizado.getNombre());
        productoExistente.setPrecio(productoActualizado.getPrecio());
        productoExistente.setStock(productoActualizado.getStock());
        return productoServicio.crearProducto(productoExistente);
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Long id) {
        productoServicio.eliminarProducto(id);
    }
}
