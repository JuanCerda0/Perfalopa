package com.logistica.sistema_de_logistica.modelo;

import jakarta.persistence.*;

@Entity
public class Pedido {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String destino;
    private String estado;
    private String rutaPlanificada;

    // Getters y setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getDestino() { return destino; }
    public void setDestino(String destino) { this.destino = destino; }

    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }

    public String getRutaPlanificada() { return rutaPlanificada; }
    public void setRutaPlanificada(String rutaPlanificada) { this.rutaPlanificada = rutaPlanificada; }
}