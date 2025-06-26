package com.web.sistema_de_web.modelo;

import java.time.LocalDateTime;
import java.util.List;

public class Pedido {
    private Long id;
    private Long clienteId;
    private List<DetallePedido> detalles;
    private LocalDateTime fecha;
    private String estado;

    public Pedido() {}

    public Pedido(Long id, Long clienteId, List<DetallePedido> detalles, LocalDateTime fecha, String estado) {
        this.id = id;
        this.clienteId = clienteId;
        this.detalles = detalles;
        this.fecha = fecha;
        this.estado = estado;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Long getClienteId() { return clienteId; }
    public void setClienteId(Long clienteId) { this.clienteId = clienteId; }

    public List<DetallePedido> getDetalles() { return detalles; }
    public void setDetalles(List<DetallePedido> detalles) { this.detalles = detalles; }

    public LocalDateTime getFecha() { return fecha; }
    public void setFecha(LocalDateTime fecha) { this.fecha = fecha; }

    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }
}