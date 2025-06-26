package com.web.sistema_de_web.repositorio;

import com.web.sistema_de_web.modelo.Pedido;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class PedidoRepositorio {
    private final List<Pedido> pedidos = new ArrayList<>();
    private Long nextId = 1L;

    public Pedido guardar(Pedido pedido) {
        pedido.setId(nextId++);
        pedidos.add(pedido);
        return pedido;
    }

    public List<Pedido> buscarPorCliente(Long clienteId) {
        return pedidos.stream()
                .filter(p -> p.getClienteId().equals(clienteId))
                .collect(Collectors.toList());
    }
}