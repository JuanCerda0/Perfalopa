package com.web.sistema_de_web.modelo;

import java.util.ArrayList;
import java.util.List;

public class Carrito {
    private List<DetallePedido> items = new ArrayList<>();

    public Carrito() {}

    public List<DetallePedido> getItems() { return items; }
    public void setItems(List<DetallePedido> items) { this.items = items; }

    public void agregarItem(DetallePedido detalle) {
        this.items.add(detalle);
    }

    public void limpiar() {
        this.items.clear();
    }
}