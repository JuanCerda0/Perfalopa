package com.ventas.servicio;

import com.ventas.modelo.Venta;
import com.ventas.modelo.DetalleVenta;
import com.ventas.repositorio.VentaRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class VentaServicio {
    private final VentaRepositorio ventaRepositorio;
    private final RestTemplate restTemplate;

    @Autowired
    public VentaServicio(VentaRepositorio ventaRepositorio, RestTemplate restTemplate) {
        this.ventaRepositorio = ventaRepositorio;
        this.restTemplate = restTemplate;
    }

    public Venta registrarVenta(Venta venta) {
        double total = 0.0;
        for (DetalleVenta detalle : venta.getDetalles()) {
            // Llama al microservicio de inventario para obtener el precio
            String url = "http://inventario:8083/api/productos/" + detalle.getProductoId();
            ProductoDTO producto = restTemplate.getForObject(url, ProductoDTO.class);
            if (producto != null) {
                total += producto.getPrecio() * detalle.getCantidad();
            }
        }
        venta.setTotal(total);
        return ventaRepositorio.save(venta);
    }

    public List<Venta> obtenerTodas() {
        return ventaRepositorio.findAll();
    }

    public Venta obtenerPorId(Long id) {
        return ventaRepositorio.findById(id).orElse(null);
    }

    public void eliminarPorId(Long id) {
        ventaRepositorio.deleteById(id);
    }

    // Clase interna para mapear la respuesta del microservicio de inventario
    public static class ProductoDTO {
        private Long id;
        private String nombre;
        private int stock;
        private double precio;

        public Long getId() { return id; }
        public void setId(Long id) { this.id = id; }

        public String getNombre() { return nombre; }
        public void setNombre(String nombre) { this.nombre = nombre; }

        public int getStock() { return stock; }
        public void setStock(int stock) { this.stock = stock; }

        public double getPrecio() { return precio; }
        public void setPrecio(double precio) { this.precio = precio; }
    }
}