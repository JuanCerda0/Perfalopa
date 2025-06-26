package com.ejemplo.cliente.servicio;

import com.ejemplo.cliente.modelo.Cliente;
import com.ejemplo.cliente.repositorio.ClienteRepositorio;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class ClienteServicioTest {

    @Test
    void crearCliente_debeGuardarCliente() {
        ClienteRepositorio mockRepositorio = Mockito.mock(ClienteRepositorio.class);
        ClienteServicio servicio = new ClienteServicio(mockRepositorio);

        Cliente cliente = new Cliente();
        cliente.setId(1L);
        cliente.setNombre("Juan");
        cliente.setCorreo("juan@email.com");
        cliente.setDireccion("Calle Falsa 123");

        Mockito.when(mockRepositorio.save(cliente)).thenReturn(cliente);

        Cliente resultado = servicio.crearCliente(cliente);

        assertNotNull(resultado);
        assertEquals(1L, resultado.getId());
        assertEquals("Juan", resultado.getNombre());
        assertEquals("juan@email.com", resultado.getCorreo());
        assertEquals("Calle Falsa 123", resultado.getDireccion());
        Mockito.verify(mockRepositorio).save(cliente);
    }

    @Test
    void obtenerTodos_debeRetornarListaDeClientes() {
        ClienteRepositorio mockRepositorio = Mockito.mock(ClienteRepositorio.class);
        ClienteServicio servicio = new ClienteServicio(mockRepositorio);

        Cliente cliente1 = new Cliente();
        cliente1.setId(1L);
        cliente1.setNombre("Juan");
        cliente1.setCorreo("juan@email.com");
        cliente1.setDireccion("Calle Falsa 123");

        Cliente cliente2 = new Cliente();
        cliente2.setId(2L);
        cliente2.setNombre("Ana");
        cliente2.setCorreo("ana@email.com");
        cliente2.setDireccion("Calle Verdadera 456");

        List<Cliente> lista = Arrays.asList(cliente1, cliente2);
        Mockito.when(mockRepositorio.findAll()).thenReturn(lista);

        List<Cliente> resultado = servicio.obtenerTodos();

        assertEquals(2, resultado.size());
        assertEquals("Juan", resultado.get(0).getNombre());
        assertEquals("Ana", resultado.get(1).getNombre());
        Mockito.verify(mockRepositorio).findAll();
    }

    @Test
    void obtenerPorId_debeRetornarClienteCorrecto() {
        ClienteRepositorio mockRepositorio = Mockito.mock(ClienteRepositorio.class);
        ClienteServicio servicio = new ClienteServicio(mockRepositorio);

        Cliente cliente = new Cliente();
        cliente.setId(1L);
        cliente.setNombre("Juan");
        cliente.setCorreo("juan@email.com");
        cliente.setDireccion("Calle Falsa 123");

        Mockito.when(mockRepositorio.findById(1L)).thenReturn(Optional.of(cliente));

        Cliente resultado = servicio.obtenerPorId(1L);

        assertNotNull(resultado);
        assertEquals(1L, resultado.getId());
        assertEquals("Juan", resultado.getNombre());
        Mockito.verify(mockRepositorio).findById(1L);
    }

    @Test
    void eliminarCliente_debeEliminarClientePorId() {
        ClienteRepositorio mockRepositorio = Mockito.mock(ClienteRepositorio.class);
        ClienteServicio servicio = new ClienteServicio(mockRepositorio);

        servicio.eliminarCliente(1L);

        Mockito.verify(mockRepositorio).deleteById(1L);
    }
}