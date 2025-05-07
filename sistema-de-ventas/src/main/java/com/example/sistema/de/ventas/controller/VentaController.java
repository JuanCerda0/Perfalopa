package com.example.sistema.de.ventas.controller;

import com.example.sistema.de.ventas.dto.VentaRequestDTO;
import com.example.sistema.de.ventas.model.Venta;
import com.example.sistema.de.ventas.service.VentaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/ventas")
public class VentaController {

    @Autowired
    private VentaService ventaService;

    @PostMapping
    public ResponseEntity<Venta> registrarVenta(@RequestBody VentaRequestDTO ventaRequest) {
        Venta venta = ventaService.registrarVenta(ventaRequest);
        return ResponseEntity.ok(venta);
    }
}