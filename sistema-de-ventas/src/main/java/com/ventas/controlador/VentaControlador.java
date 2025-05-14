package com.ventas.controlador;

import com.ventas.modelo.Venta;
import com.ventas.servicio.VentaServicio;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/ventas")
public class VentaControlador {
    private final VentaServicio ventaServicio;

    public VentaControlador(VentaServicio ventaServicio) {
        this.ventaServicio = ventaServicio;
    }

    @PostMapping
    public Venta registrarVenta(@RequestBody Venta venta) {
        return ventaServicio.registrarVenta(venta);
    }

    @GetMapping
    public List<Venta> obtenerTodas() {
        return ventaServicio.obtenerTodas();
    }

    @GetMapping("/{id}")
    public Venta obtenerPorId(@PathVariable Long id) {
        return ventaServicio.obtenerPorId(id);
    }

    @DeleteMapping("/{id}")
    public void eliminarPorId(@PathVariable Long id) {
        ventaServicio.eliminarPorId(id);
    }
}