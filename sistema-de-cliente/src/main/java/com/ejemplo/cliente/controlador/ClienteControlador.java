package com.ejemplo.cliente.controlador;

import com.ejemplo.cliente.modelo.Cliente;
import com.ejemplo.cliente.servicio.ClienteServicio;
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
@RequestMapping("/api/clientes")
@Tag(name = "Cliente", description = "Operaciones relacionadas con la gestión de clientes")
public class ClienteControlador {

    @Autowired
    private ClienteServicio clienteServicio;

    @Operation(
        summary = "Obtener todos los clientes",
        description = "Devuelve una lista de todos los clientes registrados.",
        responses = {
            @ApiResponse(
                responseCode = "200",
                description = "Lista de clientes",
                content = @Content(schema = @Schema(implementation = Cliente.class))
            )
        }
    )
    @GetMapping
    public List<Cliente> obtenerTodos() {
        return clienteServicio.obtenerTodos();
    }

    @Operation(
        summary = "Obtener cliente por ID",
        description = "Devuelve un cliente según su ID.",
        responses = {
            @ApiResponse(
                responseCode = "200",
                description = "Cliente encontrado",
                content = @Content(schema = @Schema(implementation = Cliente.class))
            ),
            @ApiResponse(
                responseCode = "404",
                description = "Cliente no encontrado"
            )
        }
    )
    @GetMapping("/{id}")
    public Cliente obtenerPorId(
        @Parameter(description = "ID del cliente a buscar", required = true)
        @PathVariable Long id
    ) {
        return clienteServicio.obtenerPorId(id);
    }

    @Operation(
        summary = "Crear un nuevo cliente",
        description = "Crea un nuevo cliente en el sistema.",
        responses = {
            @ApiResponse(
                responseCode = "200",
                description = "Cliente creado correctamente",
                content = @Content(schema = @Schema(implementation = Cliente.class))
            )
        }
    )
    @PostMapping
    public Cliente crear(
        @io.swagger.v3.oas.annotations.parameters.RequestBody(
            description = "Datos del cliente a crear",
            required = true,
            content = @Content(schema = @Schema(implementation = Cliente.class))
        )
        @RequestBody Cliente cliente
    ) {
        return clienteServicio.crearCliente(cliente);
    }

    @Operation(
        summary = "Actualizar un cliente existente",
        description = "Actualiza los datos de un cliente existente.",
        responses = {
            @ApiResponse(
                responseCode = "200",
                description = "Cliente actualizado correctamente",
                content = @Content(schema = @Schema(implementation = Cliente.class))
            ),
            @ApiResponse(
                responseCode = "404",
                description = "Cliente no encontrado"
            )
        }
    )
    @PutMapping("/{id}")
    public Cliente actualizar(
        @Parameter(description = "ID del cliente a actualizar", required = true)
        @PathVariable Long id,
        @io.swagger.v3.oas.annotations.parameters.RequestBody(
            description = "Datos actualizados del cliente",
            required = true,
            content = @Content(schema = @Schema(implementation = Cliente.class))
        )
        @RequestBody Cliente clienteActualizado
    ) {
        Cliente clienteExistente = clienteServicio.obtenerPorId(id);
        clienteExistente.setNombre(clienteActualizado.getNombre());
        clienteExistente.setCorreo(clienteActualizado.getCorreo());
        return clienteServicio.crearCliente(clienteExistente);
    }

    @Operation(
        summary = "Eliminar un cliente",
        description = "Elimina un cliente por su ID.",
        responses = {
            @ApiResponse(
                responseCode = "204",
                description = "Cliente eliminado correctamente"
            ),
            @ApiResponse(
                responseCode = "404",
                description = "Cliente no encontrado"
            )
        }
    )
    @DeleteMapping("/{id}")
    public void eliminar(
        @Parameter(description = "ID del cliente a eliminar", required = true)
        @PathVariable Long id
    ) {
        clienteServicio.eliminarCliente(id);
    }
}