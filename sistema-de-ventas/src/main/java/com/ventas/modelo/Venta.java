package com.ventas.modelo;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.util.List;

@Entity
@Getter @Setter
public class Venta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long clienteId;
    private String tipoVenta;
    private String codigoDescuento;
    private Double total;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "venta_id")
    private List<DetalleVenta> detalles;
}