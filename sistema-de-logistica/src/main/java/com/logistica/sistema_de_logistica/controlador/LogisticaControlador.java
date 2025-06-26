package com.logistica.sistema_de_logistica.controlador;

import com.logistica.sistema_de_logistica.modelo.Pedido;
import com.logistica.sistema_de_logistica.servicio.LogisticaServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/logistica")
public class LogisticaControlador {
    @Autowired
    private LogisticaServicio logisticaServicio;

    @PostMapping("/planificar")
    public Pedido planificarRuta(@RequestBody Pedido pedido) {
        return logisticaServicio.planificarRuta(pedido);
    }

    @PutMapping("/estado/{id}")
    public Pedido actualizarEstado(@PathVariable Long id, @RequestParam String estado) {
        return logisticaServicio.actualizarEstado(id, estado);
    }

    @GetMapping("/pedidos")
    public List<Pedido> obtenerTodos() {
        return logisticaServicio.obtenerTodos();
    }
}