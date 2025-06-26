package com.administracion.sistema_de_administracion.controlador;

import com.administracion.sistema_de_administracion.modelo.Backup;
import com.administracion.sistema_de_administracion.servicio.BackupServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/backup")
public class BackupControlador {

    private final BackupServicio backupServicio;

    @Autowired
    public BackupControlador(BackupServicio backupServicio) {
        this.backupServicio = backupServicio;
    }

    @PostMapping("/iniciar")
    public Backup iniciarBackup() {
        return backupServicio.iniciarBackup();
    }
}