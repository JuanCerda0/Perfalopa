package com.administracion.sistema_de_administracion.controlador;

import com.administracion.sistema_de_administracion.modelo.Backup;
import com.administracion.sistema_de_administracion.servicio.BackupServicio;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/backup")
@Tag(name = "Backup", description = "Operaciones relacionadas con la gestión de backups")
public class BackupControlador {

    private final BackupServicio backupServicio;

    @Autowired
    public BackupControlador(BackupServicio backupServicio) {
        this.backupServicio = backupServicio;
    }

    @Operation(
        summary = "Iniciar un backup",
        description = "Inicia el proceso de backup y retorna la información del backup realizado.",
        responses = {
            @ApiResponse(
                responseCode = "200",
                description = "Backup realizado correctamente",
                content = @Content(schema = @Schema(implementation = Backup.class))
            )
        }
    )
    @PostMapping("/iniciar")
    public Backup iniciarBackup() {
        return backupServicio.iniciarBackup();
    }
}