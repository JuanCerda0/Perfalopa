package com.web.sistema_de_web.servicio;

import com.web.sistema_de_web.modelo.Pedido;
import com.web.sistema_de_web.repositorio.PedidoRepositorio;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class PedidoServicioTest {

    @Mock
    private PedidoRepositorio pedidoRepositorio;

    @InjectMocks
    private PedidoServicio pedidoServicio;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void realizarPedido_debeGuardarPedidoConEstadoPendienteYFecha() {
        Pedido pedido = new Pedido();
        when(pedidoRepositorio.guardar(any(Pedido.class))).thenAnswer(i -> i.getArgument(0));

        Pedido resultado = pedidoServicio.realizarPedido(pedido);

        assertNotNull(resultado.getFecha());
        assertEquals("PENDIENTE", resultado.getEstado());
        verify(pedidoRepositorio).guardar(pedido);
    }

    @Test
    void obtenerHistorial_debeRetornarListaPedidos() {
        Pedido pedido = new Pedido();
        when(pedidoRepositorio.buscarPorCliente(1L)).thenReturn(List.of(pedido));

        List<Pedido> historial = pedidoServicio.obtenerHistorial(1L);

        assertEquals(1, historial.size());
        verify(pedidoRepositorio).buscarPorCliente(1L);
    }
}