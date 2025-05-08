package com.example.sistema.de.ventas.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "devoluciones")
@Getter
@Setter
public class Devolucion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "venta_id")
    private Venta venta;

    private Long productoId;

    private Integer cantidad;

    private String motivo;

    private LocalDateTime fechaDevolucion;

    private Double montoReembolsado;
}