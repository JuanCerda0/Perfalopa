async function cargarClientes() {
  const lista = document.getElementById('lista-clientes');
  lista.innerHTML = 'Cargando...';
  try {
    // Cambia la URL por la de tu API real
    const resp = await fetch('http://localhost:8082/api/clientes');
    const data = await resp.json();
    lista.innerHTML = '';
    data.forEach(cliente => {
      const li = document.createElement('li');
      li.textContent = cliente.nombre;
      lista.appendChild(li);
    });
  } catch (e) {
    lista.innerHTML = 'Error al cargar clientes';
  }
}

async function cargarInventario() {
  const lista = document.getElementById('lista-inventario');
  lista.innerHTML = 'Cargando...';
  try {
    const resp = await fetch('http://localhost:8083/api/productos');
    const data = await resp.json();
    lista.innerHTML = '';
    data.forEach(item => {
      const li = document.createElement('li');
      li.textContent = `${item.nombre} (${item.stock})`;
      lista.appendChild(li);
    });
  } catch (e) {
    lista.innerHTML = 'Error al cargar inventario';
  }
}

async function cargarVentas() {
  const lista = document.getElementById('lista-ventas');
  lista.innerHTML = 'Cargando...';
  try {
    const resp = await fetch('http://localhost:8081/api/ventas');
    const data = await resp.json();
    lista.innerHTML = '';
    data.forEach(venta => {
      const li = document.createElement('li');
      li.textContent = `Venta #${venta.id} - Cliente: ${venta.clienteId} - Total: $${venta.total}`;
      lista.appendChild(li);
    });
  } catch (e) {
    lista.innerHTML = 'Error al cargar ventas';
  }
}

async function cargarLogistica() {
  const lista = document.getElementById('lista-logistica');
  lista.innerHTML = 'Cargando...';
  try {
    const resp = await fetch('http://localhost:8084/api/logistica/pedidos');
    const data = await resp.json();
    lista.innerHTML = '';
    data.forEach(envio => {
      const li = document.createElement('li');
      li.textContent = `Envío #${envio.id} - Estado: ${envio.estado}`;
      lista.appendChild(li);
    });
  } catch (e) {
    lista.innerHTML = 'Error al cargar logística';
  }
}

async function cargarAdministracion() {
  const lista = document.getElementById('lista-administracion');
  lista.innerHTML = 'Cargando...';
  try {
    const resp = await fetch('http://localhost:8085/api/permisos/todos');
    const data = await resp.json();
    lista.innerHTML = '';
    data.forEach(permiso => {
      const li = document.createElement('li');
      li.textContent = `${permiso.nombre} (${permiso.rol})`;
      lista.appendChild(li);
    });
  } catch (e) {
    lista.innerHTML = 'Error al cargar administración';
  }
}

async function agregarInventario() {
  const nombre = document.getElementById('nuevo-nombre').value;
  const cantidad = document.getElementById('nuevo-cantidad').value;
  const lista = document.getElementById('lista-inventario');
  if (!nombre || !cantidad) {
    alert('Completa todos los campos');
    return;
  }
  try {
    const resp = await fetch('http://localhost:8083/api/productos', {
      method: 'POST',
      headers: { 'Content-Type': 'application/json' },
      body: JSON.stringify({ nombre, stock: parseInt(cantidad) })
    });
    if (resp.ok) {
      document.getElementById('nuevo-nombre').value = '';
      document.getElementById('nuevo-cantidad').value = '';
      cargarInventario();
    } else {
      alert('Error al agregar producto');
    }
  } catch (e) {
    alert('Error de conexión');
  }
}

async function agregarVenta() {
  const clienteId = document.getElementById('venta-clienteId').value;
  const tipoVenta = document.getElementById('venta-tipoVenta').value;
  const codigoDescuento = document.getElementById('venta-codigoDescuento').value;
  const total = document.getElementById('venta-total').value;
  const productoId = document.getElementById('detalle-productoId').value;
  const cantidad = document.getElementById('detalle-cantidad').value;
  const precioUnitario = document.getElementById('detalle-precio').value;
  if (!clienteId || !tipoVenta || !total || !productoId || !cantidad || !precioUnitario) {
    alert('Completa todos los campos obligatorios');
    return;
  }
  const venta = {
    clienteId: parseInt(clienteId),
    tipoVenta,
    codigoDescuento,
    total: parseFloat(total),
    detalles: [
      {
        productoId: parseInt(productoId),
        cantidad: parseInt(cantidad),
        precioUnitario: parseFloat(precioUnitario)
      }
    ]
  };
  try {
    const resp = await fetch('http://localhost:8081/api/ventas', {
      method: 'POST',
      headers: { 'Content-Type': 'application/json' },
      body: JSON.stringify(venta)
    });
    if (resp.ok) {
      document.getElementById('venta-clienteId').value = '';
      document.getElementById('venta-tipoVenta').value = '';
      document.getElementById('venta-codigoDescuento').value = '';
      document.getElementById('venta-total').value = '';
      document.getElementById('detalle-productoId').value = '';
      document.getElementById('detalle-cantidad').value = '';
      document.getElementById('detalle-precio').value = '';
      cargarVentas();
    } else {
      alert('Error al registrar venta');
    }
  } catch (e) {
    alert('Error de conexión');
  }
}






