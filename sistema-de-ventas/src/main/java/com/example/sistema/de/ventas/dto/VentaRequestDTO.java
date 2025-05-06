package com.example.sistema.de.ventas.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class VentaRequestDTO {

    private Long clienteId;

    private String tipoVenta;

    private String codigoDescuento;

    private List<DetalleVentaDTO> detalles;

    @Getter
    @Setter
    public static class DetalleVentaDTO {
        private Long productoId;
        private Integer cantidad;
    }
}