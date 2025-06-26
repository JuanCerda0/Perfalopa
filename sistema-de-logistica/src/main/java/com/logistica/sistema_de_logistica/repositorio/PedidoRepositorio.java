package com.logistica.sistema_de_logistica.repositorio;

import com.logistica.sistema_de_logistica.modelo.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PedidoRepositorio extends JpaRepository<Pedido, Long> {}