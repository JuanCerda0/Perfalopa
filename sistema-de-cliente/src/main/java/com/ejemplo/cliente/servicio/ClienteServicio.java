package com.ejemplo.cliente.servicio;

import com.ejemplo.cliente.modelo.Cliente;
import com.ejemplo.cliente.repositorio.ClienteRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClienteServicio {

    @Autowired
    private ClienteRepositorio clienteRepositorio;

    public Cliente crearCliente(Cliente cliente) {
        return clienteRepositorio.save(cliente);
    }

    public List<Cliente> obtenerTodos() {
        return clienteRepositorio.findAll();
    }

    public Cliente obtenerPorId(Long id) {
        return clienteRepositorio.findById(id).orElse(null);
    }

    public void eliminarCliente(Long id) {
        clienteRepositorio.deleteById(id);
    }
}
