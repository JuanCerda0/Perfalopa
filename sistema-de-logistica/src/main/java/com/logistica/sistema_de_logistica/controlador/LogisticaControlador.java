package com.logistica.sistema_de_logistica.controlador;

import com.logistica.sistema_de_logistica.modelo.Pedido;
import com.logistica.sistema_de_logistica.servicio.LogisticaServicio;
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
@RequestMapping("/api/logistica")
@Tag(name = "Logística", description = "Operaciones relacionadas con la gestión logística de pedidos")
public class LogisticaControlador {
    @Autowired
    private LogisticaServicio logisticaServicio;

    @Operation(
        summary = "Planificar ruta para un pedido",
        description = "Planifica la ruta óptima para un pedido y retorna el pedido actualizado.",
        responses = {
            @ApiResponse(
                responseCode = "200",
                description = "Ruta planificada correctamente",
                content = @Content(schema = @Schema(implementation = Pedido.class))
            )
        }
    )
    @PostMapping("/planificar")
    public Pedido planificarRuta(
        @io.swagger.v3.oas.annotations.parameters.RequestBody(
            description = "Datos del pedido a planificar",
            required = true,
            content = @Content(schema = @Schema(implementation = Pedido.class))
        )
        @RequestBody Pedido pedido
    ) {
        return logisticaServicio.planificarRuta(pedido);
    }

    @Operation(
        summary = "Actualizar estado de un pedido",
        description = "Actualiza el estado de un pedido existente.",
        responses = {
            @ApiResponse(
                responseCode = "200",
                description = "Estado actualizado correctamente",
                content = @Content(schema = @Schema(implementation = Pedido.class))
            ),
            @ApiResponse(
                responseCode = "404",
                description = "Pedido no encontrado"
            )
        }
    )
    @PutMapping("/estado/{id}")
    public Pedido actualizarEstado(
        @Parameter(description = "ID del pedido a actualizar", required = true)
        @PathVariable Long id,
        @Parameter(description = "Nuevo estado del pedido", required = true)
        @RequestParam String estado
    ) {
        return logisticaServicio.actualizarEstado(id, estado);
    }

    @Operation(
        summary = "Obtener todos los pedidos",
        description = "Devuelve una lista de todos los pedidos registrados.",
        responses = {
            @ApiResponse(
                responseCode = "200",
                description = "Lista de pedidos",
                content = @Content(schema = @Schema(implementation = Pedido.class))
            )
        }
    )
    @GetMapping("/pedidos")
    public List<Pedido> obtenerTodos() {
        return logisticaServicio.obtenerTodos();
    }
}