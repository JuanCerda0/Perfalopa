package com.logistica.sistema_de_logistica.servicio;

import com.logistica.sistema_de_logistica.modelo.Pedido;
import com.logistica.sistema_de_logistica.repositorio.PedidoRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LogisticaServicio {
    @Autowired
    private PedidoRepositorio pedidoRepositorio;

    public Pedido planificarRuta(Pedido pedido) {
        pedido.setRutaPlanificada("Ruta optimizada para " + pedido.getDestino());
        pedido.setEstado("Ruta planificada");
        return pedidoRepositorio.save(pedido);
    }

    public Pedido actualizarEstado(Long id, String estado) {
        Pedido pedido = pedidoRepositorio.findById(id).orElseThrow();
        pedido.setEstado(estado);
        return pedidoRepositorio.save(pedido);
    }

    public List<Pedido> obtenerTodos() {
        return pedidoRepositorio.findAll();
    }
}