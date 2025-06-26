package com.inventario.controlador;

import com.inventario.modelo.Producto;
import com.inventario.servicio.ProductoServicio;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/productos")
@Tag(name = "Producto", description = "Operaciones relacionadas con la gestión de productos")
public class ProductoControlador {

    @Autowired
    private ProductoServicio productoServicio;

    @Operation(
        summary = "Obtener todos los productos",
        description = "Devuelve una lista de todos los productos en inventario.",
        responses = {
            @ApiResponse(
                responseCode = "200",
                description = "Lista de productos",
                content = @Content(schema = @Schema(implementation = Producto.class))
            )
        }
    )
    @GetMapping
    public List<Producto> obtenerTodos() {
        return productoServicio.obtenerTodos();
    }

    @Operation(
        summary = "Obtener producto por ID",
        description = "Devuelve un producto según su ID.",
        responses = {
            @ApiResponse(
                responseCode = "200",
                description = "Producto encontrado",
                content = @Content(schema = @Schema(implementation = Producto.class))
            ),
            @ApiResponse(
                responseCode = "404",
                description = "Producto no encontrado"
            )
        }
    )
    @GetMapping("/{id}")
    public Producto obtenerPorId(
        @Parameter(description = "ID del producto a buscar", required = true)
        @PathVariable Long id
    ) {
        return productoServicio.obtenerPorId(id);
    }

    @Operation(
        summary = "Crear un nuevo producto",
        description = "Crea un nuevo producto en el inventario.",
        responses = {
            @ApiResponse(
                responseCode = "200",
                description = "Producto creado correctamente",
                content = @Content(schema = @Schema(implementation = Producto.class))
            )
        }
    )
    @PostMapping
    public Producto crear(
        @io.swagger.v3.oas.annotations.parameters.RequestBody(
            description = "Datos del producto a crear",
            required = true,
            content = @Content(schema = @Schema(implementation = Producto.class))
        )
        @RequestBody Producto producto
    ) {
        return productoServicio.crearProducto(producto);
    }

    @Operation(
        summary = "Actualizar un producto existente",
        description = "Actualiza los datos de un producto existente.",
        responses = {
            @ApiResponse(
                responseCode = "200",
                description = "Producto actualizado correctamente",
                content = @Content(schema = @Schema(implementation = Producto.class))
            ),
            @ApiResponse(
                responseCode = "404",
                description = "Producto no encontrado"
            )
        }
    )
    @PutMapping("/{id}")
    public Producto actualizar(
        @Parameter(description = "ID del producto a actualizar", required = true)
        @PathVariable Long id,
        @io.swagger.v3.oas.annotations.parameters.RequestBody(
            description = "Datos actualizados del producto",
            required = true,
            content = @Content(schema = @Schema(implementation = Producto.class))
        )
        @RequestBody Producto productoActualizado
    ) {
        Producto productoExistente = productoServicio.obtenerPorId(id);
        productoExistente.setNombre(productoActualizado.getNombre());
        productoExistente.setPrecio(productoActualizado.getPrecio());
        productoExistente.setStock(productoActualizado.getStock());
        return productoServicio.crearProducto(productoExistente);
    }

    @Operation(
        summary = "Eliminar un producto",
        description = "Elimina un producto por su ID.",
        responses = {
            @ApiResponse(
                responseCode = "204",
                description = "Producto eliminado correctamente"
            ),
            @ApiResponse(
                responseCode = "404",
                description = "Producto no encontrado"
            )
        }
    )
    @DeleteMapping("/{id}")
    public void eliminar(
        @Parameter(description = "ID del producto a eliminar", required = true)
        @PathVariable Long id
    ) {
        productoServicio.eliminarProducto(id);
    }
}