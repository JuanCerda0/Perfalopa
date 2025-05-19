package com.ejemplo.cliente.controlador;

import com.ejemplo.cliente.modelo.Cliente;
import com.ejemplo.cliente.servicio.ClienteServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/clientes")
public class ClienteControlador {

        @Autowired
    private ClienteServicio clienteServicio;

    @GetMapping
    public List<Cliente> obtenerTodos() {
        return clienteServicio.obtenerTodos();
    }

    @GetMapping("/{id}")
    public Cliente obtenerPorId(@PathVariable Long id) {
        return clienteServicio.obtenerPorId(id);
    }

    @PostMapping
    public Cliente crear(@RequestBody Cliente cliente) {
        return clienteServicio.crearCliente(cliente);
    }

    @PutMapping("/{id}")
    public Cliente actualizar(@PathVariable Long id, @RequestBody Cliente clienteActualizado) {
        Cliente clienteExistente = clienteServicio.obtenerPorId(id);
        clienteExistente.setNombre(clienteActualizado.getNombre());
        clienteExistente.setCorreo(clienteActualizado.getCorreo());
        return clienteServicio.crearCliente(clienteExistente);
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Long id) {
        clienteServicio.eliminarCliente(id);
    }
}