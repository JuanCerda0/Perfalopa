package com.ventas.controlador;

import com.ventas.modelo.Venta;
import com.ventas.servicio.VentaServicio;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(VentaControlador.class)
class VentaControladorTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private VentaServicio ventaServicio;

    @Test
    void obtenerTodas_debeRetornar200() throws Exception {
        when(ventaServicio.obtenerTodas()).thenReturn(Collections.emptyList());

        mockMvc.perform(get("/api/ventas")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void obtenerPorId_existente_debeRetornar200() throws Exception {
        Venta venta = new Venta();
        venta.setId(1L);
        when(ventaServicio.obtenerPorId(1L)).thenReturn(venta);

        mockMvc.perform(get("/api/ventas/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void obtenerPorId_noExistente_debeRetornar404() throws Exception {
        when(ventaServicio.obtenerPorId(2L)).thenReturn(null);

        mockMvc.perform(get("/api/ventas/2")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }
}