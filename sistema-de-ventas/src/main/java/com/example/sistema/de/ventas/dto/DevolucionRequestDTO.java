package com.example.sistema.de.ventas.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DevolucionRequestDTO {

    private Long ventaId;

    private Long productoId;

    private Integer cantidad;

    private String motivo;
}