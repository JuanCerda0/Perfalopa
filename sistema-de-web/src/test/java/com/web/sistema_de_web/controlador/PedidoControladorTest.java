package com.web.sistema_de_web.controlador;
import static org.mockito.ArgumentMatchers.any;
import com.web.sistema_de_web.modelo.Pedido;
import com.web.sistema_de_web.servicio.PedidoServicio;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(PedidoControlador.class)
class PedidoControladorTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PedidoServicio pedidoServicio;

    @Test
    void historial_debeRetornar200() throws Exception {
        when(pedidoServicio.obtenerHistorial(1L)).thenReturn(Collections.emptyList());

        mockMvc.perform(get("/api/web/pedidos/historial/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void realizarPedido_debeRetornar200() throws Exception {
        Pedido pedido = new Pedido();
        when(pedidoServicio.realizarPedido(any(Pedido.class))).thenReturn(pedido);

        mockMvc.perform(post("/api/web/pedidos")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"clienteId\":1,\"detalles\":[],\"fecha\":null,\"estado\":null}"))
                .andExpect(status().isOk());
    }
}