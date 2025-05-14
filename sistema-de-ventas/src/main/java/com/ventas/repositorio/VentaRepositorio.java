package com.ventas.repositorio;

import com.ventas.modelo.Venta;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VentaRepositorio extends JpaRepository<Venta, Long> {}