package com.example.sistema.de.ventas.repository;

import com.example.sistema.de.ventas.model.Devolucion;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DevolucionRepository extends JpaRepository<Devolucion, Long> {
}