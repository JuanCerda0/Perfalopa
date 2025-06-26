package com.administracion.sistema_de_administracion.repositorio;

import com.administracion.sistema_de_administracion.modelo.Permiso;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PermisoRepositorio extends JpaRepository<Permiso, Long> {
}