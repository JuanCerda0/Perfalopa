function mostrarRegistro() {
  document.getElementById('form-login').classList.add('d-none');
  document.getElementById('form-registro').classList.remove('d-none');
  document.getElementById('login-mensaje').textContent = '';
}

function mostrarLogin() {
  document.getElementById('form-registro').classList.add('d-none');
  document.getElementById('form-login').classList.remove('d-none');
  document.getElementById('registro-mensaje').textContent = '';
}

async function loginCliente() {
  const correo = document.getElementById('login-correo').value;
  const contrasena = document.getElementById('login-contrasena').value;
  const mensaje = document.getElementById('login-mensaje');
  mensaje.textContent = '';
  try {
    const resp = await fetch('http://localhost:8082/api/clientes/login', {
      method: 'POST',
      headers: { 'Content-Type': 'application/json' },
      body: JSON.stringify({ correo, contrasena })
    });
    if (resp.ok) {
      const cliente = await resp.json();
      if (cliente && cliente.id) {
        mensaje.classList.remove('text-danger');
        mensaje.classList.add('text-success');
        mensaje.textContent = '¡Login exitoso!';
        // Aquí puedes redirigir o mostrar contenido para el usuario logueado
      } else {
        mensaje.classList.remove('text-success');
        mensaje.classList.add('text-danger');
        mensaje.textContent = 'Correo o contraseña incorrectos';
      }
    } else {
      mensaje.classList.remove('text-success');
      mensaje.classList.add('text-danger');
      mensaje.textContent = 'Correo o contraseña incorrectos';
    }
  } catch (e) {
    mensaje.classList.remove('text-success');
    mensaje.classList.add('text-danger');
    mensaje.textContent = 'Error de conexión';
  }
}

async function registrarCliente() {
  const nombre = document.getElementById('registro-nombre').value;
  const correo = document.getElementById('registro-correo').value;
  const direccion = document.getElementById('registro-direccion').value;
  const contrasena = document.getElementById('registro-contrasena').value;
  const mensaje = document.getElementById('registro-mensaje');
  mensaje.textContent = '';
  try {
    const resp = await fetch('http://localhost:8082/api/clientes', {
      method: 'POST',
      headers: { 'Content-Type': 'application/json' },
      body: JSON.stringify({ nombre, correo, direccion, contrasena })
    });
    if (resp.ok) {
      mensaje.classList.remove('text-danger');
      mensaje.classList.add('text-success');
      mensaje.textContent = '¡Registro exitoso! Ahora puedes iniciar sesión.';
      setTimeout(() => {
        mostrarLogin();
      }, 1500);
    } else {
      mensaje.classList.remove('text-success');
      mensaje.classList.add('text-danger');
      mensaje.textContent = 'Error al registrar. ¿El correo ya existe?';
    }
  } catch (e) {
    mensaje.classList.remove('text-success');
    mensaje.classList.add('text-danger');
    mensaje.textContent = 'Error de conexión';
  }
}






