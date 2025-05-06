package com.example.sistema.de.ventas.repository;

import com.example.sistema.de.ventas.model.Factura;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FacturaRepository extends JpaRepository<Factura, Long> {
}