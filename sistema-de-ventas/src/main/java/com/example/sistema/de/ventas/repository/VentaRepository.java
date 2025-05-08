package com.example.sistema.de.ventas.repository;

import com.example.sistema.de.ventas.model.Venta;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VentaRepository extends JpaRepository<Venta, Long> {
}