package com.administracion.sistema_de_administracion.servicio;

import com.administracion.sistema_de_administracion.modelo.Permiso;
import com.administracion.sistema_de_administracion.repositorio.PermisoRepositorio;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PermisoServicio {

    private final PermisoRepositorio permisoRepositorio;

    @Autowired
    public PermisoServicio(PermisoRepositorio permisoRepositorio) {
        this.permisoRepositorio = permisoRepositorio;
    }

    public Permiso asignarPermiso(Permiso permiso) {
        return permisoRepositorio.save(permiso);
    }

    public List<Permiso> obtenerTodos() {
        return permisoRepositorio.findAll();
    }
}