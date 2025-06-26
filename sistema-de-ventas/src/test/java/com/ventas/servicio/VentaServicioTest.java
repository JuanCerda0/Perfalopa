package com.ventas.servicio;

import com.ventas.modelo.DetalleVenta;
import com.ventas.modelo.Venta;
import com.ventas.repositorio.VentaRepositorio;
import com.ventas.servicio.VentaServicio.ProductoDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

class VentaServicioTest {

    @Mock
    private VentaRepositorio ventaRepositorio;

    @Mock
    private RestTemplate restTemplate;

    @InjectMocks
    private VentaServicio ventaServicio;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void registrarVenta_calculaTotalYGuardaVenta() {
        // Arrange
        DetalleVenta detalle = new DetalleVenta();
        detalle.setProductoId(1L);
        detalle.setCantidad(2);

        Venta venta = new Venta();
        venta.setDetalles(List.of(detalle));

        ProductoDTO productoDTO = new ProductoDTO();
        productoDTO.setPrecio(10.0);

        when(restTemplate.getForObject(anyString(), eq(ProductoDTO.class)))
                .thenReturn(productoDTO);
        when(ventaRepositorio.save(any(Venta.class))).thenAnswer(i -> i.getArgument(0));

        // Act
        Venta result = ventaServicio.registrarVenta(venta);

        // Assert
        assertEquals(20.0, result.getTotal());
        verify(ventaRepositorio).save(venta);
    }

    @Test
    void obtenerTodas_devuelveListaDeVentas() {
        Venta venta = new Venta();
        when(ventaRepositorio.findAll()).thenReturn(List.of(venta));

        List<Venta> ventas = ventaServicio.obtenerTodas();

        assertEquals(1, ventas.size());
    }

    @Test
    void obtenerPorId_existente_devuelveVenta() {
        Venta venta = new Venta();
        venta.setId(1L);
        when(ventaRepositorio.findById(1L)).thenReturn(Optional.of(venta));

        Venta resultado = ventaServicio.obtenerPorId(1L);

        assertNotNull(resultado);
        assertEquals(1L, resultado.getId());
    }

    @Test
    void obtenerPorId_noExistente_devuelveNull() {
        when(ventaRepositorio.findById(2L)).thenReturn(Optional.empty());

        Venta resultado = ventaServicio.obtenerPorId(2L);

        assertNull(resultado);
    }

    @Test
    void eliminarPorId_invocaRepositorio() {
        ventaServicio.eliminarPorId(1L);
        verify(ventaRepositorio).deleteById(1L);
    }
}