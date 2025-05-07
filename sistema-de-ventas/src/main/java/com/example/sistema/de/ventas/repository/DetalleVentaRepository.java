package com.example.sistema.de.ventas.repository;

import com.example.sistema.de.ventas.model.DetalleVenta;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DetalleVentaRepository extends JpaRepository<DetalleVenta, Long> {
}