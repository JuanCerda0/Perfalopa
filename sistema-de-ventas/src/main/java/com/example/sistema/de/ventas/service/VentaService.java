package com.example.sistema.de.ventas.service;

import com.example.sistema.de.ventas.dto.DevolucionRequestDTO;
import com.example.sistema.de.ventas.dto.VentaRequestDTO;
import com.example.sistema.de.ventas.model.DetalleVenta;
import com.example.sistema.de.ventas.model.Devolucion;
import com.example.sistema.de.ventas.model.Factura;
import com.example.sistema.de.ventas.model.Venta;
import com.example.sistema.de.ventas.repository.DetalleVentaRepository;
import com.example.sistema.de.ventas.repository.DevolucionRepository;
import com.example.sistema.de.ventas.repository.FacturaRepository;
import com.example.sistema.de.ventas.repository.VentaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class VentaService {

    private final VentaRepository ventaRepository;
    private final DetalleVentaRepository detalleVentaRepository;
    private final FacturaRepository facturaRepository;
    private final DevolucionRepository devolucionRepository;
    private final RestTemplate restTemplate;

    // Inyección por constructor
    public VentaService(VentaRepository ventaRepository,
                        DetalleVentaRepository detalleVentaRepository,
                        FacturaRepository facturaRepository,
                        DevolucionRepository devolucionRepository,
                        RestTemplate restTemplate) {
        this.ventaRepository = ventaRepository;
        this.detalleVentaRepository = detalleVentaRepository;
        this.facturaRepository = facturaRepository;
        this.devolucionRepository = devolucionRepository;
        this.restTemplate = restTemplate;
    }

    @Transactional
    public Venta registrarVenta(VentaRequestDTO ventaRequest) {
        // Crear la venta
        Venta venta = new Venta();
        venta.setClienteId(ventaRequest.getClienteId());
        venta.setFecha(LocalDateTime.now());
        venta.setTipoVenta(ventaRequest.getTipoVenta());

        // Crear los detalles de la venta
        List<DetalleVenta> detalles = ventaRequest.getDetalles().stream().map(dto -> {
            DetalleVenta detalle = new DetalleVenta();
            detalle.setVenta(venta);
            detalle.setProductoId(dto.getProductoId());
            detalle.setCantidad(dto.getCantidad());

            // Simulamos obtener el precio del producto desde el Servicio de Inventario
            Double precioUnitario = obtenerPrecioProducto(dto.getProductoId());
            detalle.setPrecioUnitario(precioUnitario);
            detalle.setSubtotal(precioUnitario * dto.getCantidad());
            return detalle;
        }).collect(Collectors.toList());

        // Sincronizar la relación bidireccional
        venta.setDetalles(detalles);

        // Calcular el total antes del descuento
        Double total = detalles.stream()
                .mapToDouble(DetalleVenta::getSubtotal)
                .sum();

        // Aplicar descuento si hay código
        if (ventaRequest.getCodigoDescuento() != null) {
            total = aplicarDescuento(total, ventaRequest.getCodigoDescuento());
        }

        venta.setTotal(total);

        // Guardar la venta (los detalles se guardan automáticamente por cascade)
        Venta ventaGuardada = ventaRepository.save(venta); // Usamos una nueva variable para no reasignar 'venta'

        // Generar factura
        Factura factura = new Factura();
        factura.setVenta(ventaGuardada);
        factura.setFechaEmision(LocalDateTime.now());
        factura.setTotal(total);
        facturaRepository.save(factura);

        // Actualizar inventario
        for (DetalleVenta detalle : detalles) {
            actualizarInventario(detalle.getProductoId(), detalle.getCantidad());
        }

        // Registrar compra en el historial del cliente
        registrarCompraCliente(ventaGuardada.getClienteId(), ventaGuardada.getId());

        return ventaGuardada;
    }

    @Transactional
    public Devolucion procesarDevolucion(DevolucionRequestDTO devolucionRequest) {
        // Buscar la venta
        Venta venta = ventaRepository.findById(devolucionRequest.getVentaId())
                .orElseThrow(() -> new IllegalArgumentException("Venta no encontrada"));

        // Buscar el detalle de la venta correspondiente al producto
        DetalleVenta detalle = venta.getDetalles().stream()
                .filter(d -> d.getProductoId().equals(devolucionRequest.getProductoId()))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Producto no encontrado en la venta"));

        // Validar la cantidad a devolver
        if (devolucionRequest.getCantidad() > detalle.getCantidad()) {
            throw new IllegalArgumentException("Cantidad a devolver excede la cantidad comprada");
        }

        // Crear la devolución
        Devolucion devolucion = new Devolucion();
        devolucion.setVenta(venta);
        devolucion.setProductoId(devolucionRequest.getProductoId());
        devolucion.setCantidad(devolucionRequest.getCantidad());
        devolucion.setMotivo(devolucionRequest.getMotivo());
        devolucion.setFechaDevolucion(LocalDateTime.now());

        // Calcular el monto a reembolsar (proporcional a la cantidad devuelta)
        Double montoReembolsado = (detalle.getSubtotal() / detalle.getCantidad()) * devolucionRequest.getCantidad();
        devolucion.setMontoReembolsado(montoReembolsado);

        // Guardar la devolución
        Devolucion devolucionGuardada = devolucionRepository.save(devolucion);

        // Actualizar el inventario (aumentar el stock)
        actualizarInventarioDevolucion(devolucionGuardada.getProductoId(), devolucionGuardada.getCantidad());

        // Actualizar el historial del cliente (opcional, según el diseño)
        registrarDevolucionCliente(venta.getClienteId(), devolucionGuardada.getId());

        return devolucionGuardada;
    }

    private Double obtenerPrecioProducto(Long productoId) {
        // Simulamos una llamada al Servicio de Inventario
        // Cuando el servicio esté listo, usarás el endpoint real
        String url = "http://localhost:8082/api/inventario/productos/" + productoId;
        try {
            return restTemplate.getForObject(url, Double.class);
        } catch (Exception e) {
            // Simulamos un precio por ahora
            return 100.0;
        }
    }

    private Double aplicarDescuento(Double total, String codigoDescuento) {
        // Lógica simple de descuento basada en el código
        // Ejemplo: "DESC10" da 10% de descuento
        if (codigoDescuento.equals("DESC10")) {
            return total * 0.9; // 10% de descuento
        }
        return total; // Sin descuento por defecto
    }

    private void actualizarInventario(Long productoId, Integer cantidad) {
        // Simulamos una llamada al Servicio de Inventario para reducir el stock
        String url = "http://localhost:8082/api/inventario/actualizar-stock/" + productoId + "?cantidad=" + (-cantidad);
        try {
            restTemplate.postForObject(url, null, Void.class);
        } catch (Exception e) {
            // Loguear error, pero continuar (simulación por ahora)
        }
    }

    private void registrarCompraCliente(Long clienteId, Long ventaId) {
        // Simulamos una llamada al Servicio de Clientes para registrar la compra
        String url = "http://localhost:8083/api/clientes/" + clienteId + "/compras";
        try {
            restTemplate.postForObject(url, ventaId, Void.class);
        } catch (Exception e) {
            // Loguear error, pero continuar (simulación por ahora)
        }
    }

    private void actualizarInventarioDevolucion(Long productoId, Integer cantidad) {
        // Simulamos una llamada al Servicio de Inventario para aumentar el stock
        String url = "http://localhost:8082/api/inventario/actualizar-stock/" + productoId + "?cantidad=" + cantidad;
        try {
            restTemplate.postForObject(url, null, Void.class);
        } catch (Exception e) {
            // Loguear error, pero continuar (simulación por ahora)
        }
    }

    private void registrarDevolucionCliente(Long clienteId, Long devolucionId) {
        // Simulamos una llamada al Servicio de Clientes para registrar la devolución
        String url = "http://localhost:8083/api/clientes/" + clienteId + "/devoluciones";
        try {
            restTemplate.postForObject(url, devolucionId, Void.class);
        } catch (Exception e) {
            // Loguear error, pero continuar (simulación por ahora)
        }
    }
}