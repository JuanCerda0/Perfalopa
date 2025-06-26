package com.web.sistema_de_web.modelo;

public class ClienteWeb {
    private Long id;
    private String nombre;
    private String correo;

    public ClienteWeb() {}

    public ClienteWeb(Long id, String nombre, String correo) {
        this.id = id;
        this.nombre = nombre;
        this.correo = correo;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getCorreo() { return correo; }
    public void setCorreo(String correo) { this.correo = correo; }
}