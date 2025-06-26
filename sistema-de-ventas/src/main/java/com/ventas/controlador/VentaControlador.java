package com.ventas.controlador;

import com.ventas.modelo.Venta;
import com.ventas.servicio.VentaServicio;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/ventas")
@Tag(name = "Venta", description = "Operaciones relacionadas con la gestión de ventas")
public class VentaControlador {
    private final VentaServicio ventaServicio;

    public VentaControlador(VentaServicio ventaServicio) {
        this.ventaServicio = ventaServicio;
    }

    @Operation(
        summary = "Registrar una venta",
        description = "Registra una nueva venta y retorna la información de la venta registrada.",
        responses = {
            @ApiResponse(
                responseCode = "200",
                description = "Venta registrada correctamente",
                content = @Content(schema = @Schema(implementation = Venta.class))
            )
        }
    )
    @PostMapping
    public Venta registrarVenta(
        @io.swagger.v3.oas.annotations.parameters.RequestBody(
            description = "Datos de la venta a registrar",
            required = true,
            content = @Content(schema = @Schema(implementation = Venta.class))
        )
        @RequestBody Venta venta
    ) {
        return ventaServicio.registrarVenta(venta);
    }

    @Operation(
        summary = "Obtener todas las ventas",
        description = "Devuelve una lista de todas las ventas registradas.",
        responses = {
            @ApiResponse(
                responseCode = "200",
                description = "Lista de ventas",
                content = @Content(schema = @Schema(implementation = Venta.class))
            )
        }
    )
    @GetMapping
    public List<Venta> obtenerTodas() {
        return ventaServicio.obtenerTodas();
    }

    @Operation(
        summary = "Obtener venta por ID",
        description = "Devuelve una venta según su ID.",
        responses = {
            @ApiResponse(
                responseCode = "200",
                description = "Venta encontrada",
                content = @Content(schema = @Schema(implementation = Venta.class))
            ),
            @ApiResponse(
                responseCode = "404",
                description = "Venta no encontrada"
            )
        }
    )
    @GetMapping("/{id}")
    public Venta obtenerPorId(
        @Parameter(description = "ID de la venta a buscar", required = true)
        @PathVariable Long id
    ) {
        return ventaServicio.obtenerPorId(id);
    }

    @Operation(
        summary = "Eliminar una venta",
        description = "Elimina una venta por su ID.",
        responses = {
            @ApiResponse(
                responseCode = "204",
                description = "Venta eliminada correctamente"
            ),
            @ApiResponse(
                responseCode = "404",
                description = "Venta no encontrada"
            )
        }
    )
    @DeleteMapping("/{id}")
    public void eliminarPorId(
        @Parameter(description = "ID de la venta a eliminar", required = true)
        @PathVariable Long id
    ) {
        ventaServicio.eliminarPorId(id);
    }
}