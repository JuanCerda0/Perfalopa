package com.logistica.sistema_de_logistica;

import com.logistica.sistema_de_logistica.modelo.Pedido;
import com.logistica.sistema_de_logistica.repositorio.PedidoRepositorio;
import com.logistica.sistema_de_logistica.servicio.LogisticaServicio;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class LogisticaServicioTest {

    @Test
    void planificarRuta_debeGuardarPedidoConRutaYEstado() {
        PedidoRepositorio mockRepositorio = Mockito.mock(PedidoRepositorio.class);
        LogisticaServicio servicio = new LogisticaServicio();
        // Inyección manual del mock si no hay constructor
        // Puedes usar reflexión o un setter si existe, aquí se asume acceso directo para el ejemplo
        // Si tienes un constructor, usa: new LogisticaServicio(mockRepositorio);

        Pedido pedido = new Pedido();
        pedido.setId(1L);
        pedido.setDestino("Santiago");
        pedido.setEstado(null);
        pedido.setRutaPlanificada(null);

        Pedido pedidoGuardado = new Pedido();
        pedidoGuardado.setId(1L);
        pedidoGuardado.setDestino("Santiago");
        pedidoGuardado.setEstado("Ruta planificada");
        pedidoGuardado.setRutaPlanificada("Ruta optimizada para Santiago");

        Mockito.when(mockRepositorio.save(Mockito.any(Pedido.class))).thenReturn(pedidoGuardado);

        // Usa reflexión para inyectar el mock si es necesario
        try {
            var field = LogisticaServicio.class.getDeclaredField("pedidoRepositorio");
            field.setAccessible(true);
            field.set(servicio, mockRepositorio);
        } catch (Exception e) {
            fail("No se pudo inyectar el mock del repositorio");
        }

        Pedido resultado = servicio.planificarRuta(pedido);

        assertNotNull(resultado);
        assertEquals("Ruta planificada", resultado.getEstado());
        assertEquals("Ruta optimizada para Santiago", resultado.getRutaPlanificada());
        Mockito.verify(mockRepositorio).save(Mockito.any(Pedido.class));
    }

    @Test
    void actualizarEstado_debeActualizarEstadoDelPedido() {
        PedidoRepositorio mockRepositorio = Mockito.mock(PedidoRepositorio.class);
        LogisticaServicio servicio = new LogisticaServicio();

        Pedido pedido = new Pedido();
        pedido.setId(1L);
        pedido.setDestino("Santiago");
        pedido.setEstado("En camino");

        Pedido pedidoActualizado = new Pedido();
        pedidoActualizado.setId(1L);
        pedidoActualizado.setDestino("Santiago");
        pedidoActualizado.setEstado("Entregado");

        Mockito.when(mockRepositorio.findById(1L)).thenReturn(Optional.of(pedido));
        Mockito.when(mockRepositorio.save(Mockito.any(Pedido.class))).thenReturn(pedidoActualizado);

        // Inyección del mock
        try {
            var field = LogisticaServicio.class.getDeclaredField("pedidoRepositorio");
            field.setAccessible(true);
            field.set(servicio, mockRepositorio);
        } catch (Exception e) {
            fail("No se pudo inyectar el mock del repositorio");
        }

        Pedido resultado = servicio.actualizarEstado(1L, "Entregado");

        assertNotNull(resultado);
        assertEquals("Entregado", resultado.getEstado());
        Mockito.verify(mockRepositorio).findById(1L);
        Mockito.verify(mockRepositorio).save(Mockito.any(Pedido.class));
    }

    @Test
    void obtenerTodos_debeRetornarListaDePedidos() {
        PedidoRepositorio mockRepositorio = Mockito.mock(PedidoRepositorio.class);
        LogisticaServicio servicio = new LogisticaServicio();

        Pedido pedido1 = new Pedido();
        pedido1.setId(1L);
        pedido1.setDestino("Santiago");

        Pedido pedido2 = new Pedido();
        pedido2.setId(2L);
        pedido2.setDestino("Valparaíso");

        List<Pedido> lista = Arrays.asList(pedido1, pedido2);
        Mockito.when(mockRepositorio.findAll()).thenReturn(lista);

        // Inyección del mock
        try {
            var field = LogisticaServicio.class.getDeclaredField("pedidoRepositorio");
            field.setAccessible(true);
            field.set(servicio, mockRepositorio);
        } catch (Exception e) {
            fail("No se pudo inyectar el mock del repositorio");
        }

        List<Pedido> resultado = servicio.obtenerTodos();

        assertEquals(2, resultado.size());
        assertEquals("Santiago", resultado.get(0).getDestino());
        assertEquals("Valparaíso", resultado.get(1).getDestino());
        Mockito.verify(mockRepositorio).findAll();
    }
}