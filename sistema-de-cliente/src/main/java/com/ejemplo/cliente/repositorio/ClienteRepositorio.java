package com.ejemplo.cliente.repositorio;

import com.ejemplo.cliente.modelo.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepositorio extends JpaRepository<Cliente, Long> {
    Cliente findByCorreoAndContrasena(String correo, String contrasena);
}
