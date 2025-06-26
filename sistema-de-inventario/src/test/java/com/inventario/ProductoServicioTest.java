package com.inventario;

import com.inventario.modelo.Producto;
import com.inventario.repositorio.ProductoRepositorio;
import com.inventario.servicio.ProductoServicio;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class ProductoServicioTest {

    @Test
    void crearProducto_debeGuardarProducto() {
        ProductoRepositorio mockRepositorio = Mockito.mock(ProductoRepositorio.class);
        ProductoServicio servicio = new ProductoServicio(mockRepositorio);

        Producto producto = new Producto(1L, "Teclado", 10, 19990);
        Mockito.when(mockRepositorio.save(producto)).thenReturn(producto);

        Producto resultado = servicio.crearProducto(producto);

        assertNotNull(resultado);
        assertEquals(1L, resultado.getId());
        assertEquals("Teclado", resultado.getNombre());
        assertEquals(10, resultado.getStock());
        assertEquals(19990, resultado.getPrecio());
        Mockito.verify(mockRepositorio).save(producto);
    }

    @Test
    void obtenerTodos_debeRetornarListaDeProductos() {
        ProductoRepositorio mockRepositorio = Mockito.mock(ProductoRepositorio.class);
        ProductoServicio servicio = new ProductoServicio(mockRepositorio);

        List<Producto> lista = Arrays.asList(
                new Producto(1L, "Teclado", 10, 19990),
                new Producto(2L, "Mouse", 20, 9990)
        );
        Mockito.when(mockRepositorio.findAll()).thenReturn(lista);

        List<Producto> resultado = servicio.obtenerTodos();

        assertEquals(2, resultado.size());
        assertEquals("Teclado", resultado.get(0).getNombre());
        assertEquals("Mouse", resultado.get(1).getNombre());
        Mockito.verify(mockRepositorio).findAll();
    }

    @Test
    void obtenerPorId_debeRetornarProductoCorrecto() {
        ProductoRepositorio mockRepositorio = Mockito.mock(ProductoRepositorio.class);
        ProductoServicio servicio = new ProductoServicio(mockRepositorio);

        Producto producto = new Producto(1L, "Teclado", 10, 19990);
        Mockito.when(mockRepositorio.findById(1L)).thenReturn(Optional.of(producto));

        Producto resultado = servicio.obtenerPorId(1L);

        assertNotNull(resultado);
        assertEquals(1L, resultado.getId());
        assertEquals("Teclado", resultado.getNombre());
        Mockito.verify(mockRepositorio).findById(1L);
    }

    @Test
    void eliminarProducto_debeEliminarProductoPorId() {
        ProductoRepositorio mockRepositorio = Mockito.mock(ProductoRepositorio.class);
        ProductoServicio servicio = new ProductoServicio(mockRepositorio);

        servicio.eliminarProducto(1L);

        Mockito.verify(mockRepositorio).deleteById(1L);
    }
}