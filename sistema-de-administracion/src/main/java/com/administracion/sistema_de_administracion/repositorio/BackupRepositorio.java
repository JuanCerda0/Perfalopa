package com.administracion.sistema_de_administracion.repositorio;

import com.administracion.sistema_de_administracion.modelo.Backup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BackupRepositorio extends JpaRepository<Backup, Long> {
}