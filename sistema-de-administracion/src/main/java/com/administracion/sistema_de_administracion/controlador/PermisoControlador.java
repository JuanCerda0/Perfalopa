package com.administracion.sistema_de_administracion.controlador;

import com.administracion.sistema_de_administracion.modelo.Permiso;
import com.administracion.sistema_de_administracion.servicio.PermisoServicio;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/permisos")
@Tag(name = "Permisos", description = "Operaciones relacionadas con los permisos de usuario")
public class PermisoControlador {

    private final PermisoServicio permisoServicio;

    @Autowired
    public PermisoControlador(PermisoServicio permisoServicio) {
        this.permisoServicio = permisoServicio;
    }

    @Operation(
        summary = "Asignar un permiso a un usuario",
        description = "Crea y asigna un permiso a un usuario para un módulo y funcionalidad específicos.",
        responses = {
            @ApiResponse(
                responseCode = "200",
                description = "Permiso asignado correctamente",
                content = @Content(schema = @Schema(implementation = Permiso.class))
            ),
            @ApiResponse(
                responseCode = "400",
                description = "Solicitud inválida",
                content = @Content
            )
        }
    )
    @PostMapping("/asignar")
    public Permiso asignarPermiso(@RequestBody Permiso permiso) {
        return permisoServicio.asignarPermiso(permiso);
    }
}