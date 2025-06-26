package com.administracion.sistema_de_administracion.servicio;

import com.administracion.sistema_de_administracion.modelo.Backup;
import com.administracion.sistema_de_administracion.repositorio.BackupRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class BackupServicio {

    private final BackupRepositorio backupRepositorio;

    @Autowired
    public BackupServicio(BackupRepositorio backupRepositorio) {
        this.backupRepositorio = backupRepositorio;
    }

    public Backup iniciarBackup() {
        Backup backup = new Backup();
        backup.setFecha(LocalDateTime.now());
        backup.setEstado("COMPLETADO"); // Simulación de backup exitoso
        return backupRepositorio.save(backup);
    }
}