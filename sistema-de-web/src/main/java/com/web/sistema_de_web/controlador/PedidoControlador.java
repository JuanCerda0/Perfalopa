package com.web.sistema_de_web.controlador;

import com.web.sistema_de_web.modelo.Pedido;
import com.web.sistema_de_web.servicio.PedidoServicio;
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
@RequestMapping("/api/web/pedidos")
@Tag(name = "Pedido Web", description = "Operaciones relacionadas con pedidos realizados desde la web")
public class PedidoControlador {

    @Autowired
    private PedidoServicio pedidoServicio;

    @Operation(
        summary = "Realizar pedido en línea",
        description = "Registra un nuevo pedido realizado desde la web.",
        responses = {
            @ApiResponse(
                responseCode = "200",
                description = "Pedido realizado correctamente",
                content = @Content(schema = @Schema(implementation = Pedido.class))
            )
        }
    )
    @PostMapping
    public Pedido realizarPedido(
        @io.swagger.v3.oas.annotations.parameters.RequestBody(
            description = "Datos del pedido a registrar",
            required = true,
            content = @Content(schema = @Schema(implementation = Pedido.class))
        )
        @RequestBody Pedido pedido
    ) {
        return pedidoServicio.realizarPedido(pedido);
    }

    @Operation(
        summary = "Consultar historial de pedidos por cliente",
        description = "Devuelve el historial de pedidos realizados por un cliente específico.",
        responses = {
            @ApiResponse(
                responseCode = "200",
                description = "Historial de pedidos",
                content = @Content(schema = @Schema(implementation = Pedido.class))
            )
        }
    )
    @GetMapping("/historial/{clienteId}")
    public List<Pedido> historial(
        @Parameter(description = "ID del cliente", required = true)
        @PathVariable Long clienteId
    ) {
        return pedidoServicio.obtenerHistorial(clienteId);
    }
}