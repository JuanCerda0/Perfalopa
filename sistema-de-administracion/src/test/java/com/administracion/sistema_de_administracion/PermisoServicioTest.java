package com.administracion.sistema_de_administracion;

import com.administracion.sistema_de_administracion.modelo.Permiso;
import com.administracion.sistema_de_administracion.repositorio.PermisoRepositorio;
import com.administracion.sistema_de_administracion.servicio.PermisoServicio;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;

class PermisoServicioTest {

    @Test
    void asignarPermiso_debeGuardarPermiso() {
        // Arrange
        PermisoRepositorio mockRepositorio = Mockito.mock(PermisoRepositorio.class);
        PermisoServicio servicio = new PermisoServicio(mockRepositorio);

        Permiso permiso = new Permiso(1L, "modulo1", "funcionalidad1");
        Mockito.when(mockRepositorio.save(permiso)).thenReturn(permiso);

        // Act
        Permiso resultado = servicio.asignarPermiso(permiso);

        // Assert
        assertNotNull(resultado);
        assertEquals(1L, resultado.getUsuarioId());
        assertEquals("modulo1", resultado.getModulo());
        assertEquals("funcionalidad1", resultado.getFuncionalidad());
        Mockito.verify(mockRepositorio).save(permiso);
    }
}