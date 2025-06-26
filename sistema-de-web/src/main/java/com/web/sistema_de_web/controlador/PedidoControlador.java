package com.web.sistema_de_web.controlador;

import com.web.sistema_de_web.modelo.Pedido;
import com.web.sistema_de_web.servicio.PedidoServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/web/pedidos")
public class PedidoControlador {

    @Autowired
    private PedidoServicio pedidoServicio;

    // Realizar pedido en línea
    @PostMapping
    public Pedido realizarPedido(@RequestBody Pedido pedido) {
        return pedidoServicio.realizarPedido(pedido);
    }

    // Consultar historial de pedidos por cliente
    @GetMapping("/historial/{clienteId}")
    public List<Pedido> historial(@PathVariable Long clienteId) {
        return pedidoServicio.obtenerHistorial(clienteId);
    }
}