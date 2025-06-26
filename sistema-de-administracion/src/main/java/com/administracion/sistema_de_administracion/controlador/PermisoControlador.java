package com.administracion.sistema_de_administracion.controlador;

import com.administracion.sistema_de_administracion.modelo.Permiso;
import com.administracion.sistema_de_administracion.servicio.PermisoServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/permisos")
public class PermisoControlador {

    private final PermisoServicio permisoServicio;

    @Autowired
    public PermisoControlador(PermisoServicio permisoServicio) {
        this.permisoServicio = permisoServicio;
    }

    @PostMapping("/asignar")
    public Permiso asignarPermiso(@RequestBody Permiso permiso) {
        return permisoServicio.asignarPermiso(permiso);
    }
}