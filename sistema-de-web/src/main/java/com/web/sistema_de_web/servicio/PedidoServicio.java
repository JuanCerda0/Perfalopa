package com.web.sistema_de_web.servicio;

import com.web.sistema_de_web.modelo.Pedido;
import com.web.sistema_de_web.repositorio.PedidoRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class PedidoServicio {

    @Autowired
    private PedidoRepositorio pedidoRepositorio;

    public Pedido realizarPedido(Pedido pedido) {
        pedido.setFecha(LocalDateTime.now());
        pedido.setEstado("PENDIENTE");
        return pedidoRepositorio.guardar(pedido);
    }

    public List<Pedido> obtenerHistorial(Long clienteId) {
        return pedidoRepositorio.buscarPorCliente(clienteId);
    }
}