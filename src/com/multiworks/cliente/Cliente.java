package com.multiworks.cliente;

import java.util.Date;

public class Cliente {
    private String documento;
    private String nombre;
    private String tipoPersona;
    private String direccion;
    private String telefono;
    private String correo;
    private boolean activo;
    private String creadoPor;
    private String fechaCreacion;
    private String fechaActualizacion;
    private String fechaInactivacion;

    public Cliente(String documento, String nombre, String tipoPersona, String direccion, String telefono, String correo, String creadoPor, String fechaCreacion) {
        this.documento = documento;
        this.nombre = nombre;
        this.tipoPersona = tipoPersona;
        this.direccion = direccion;
        this.telefono = telefono;
        this.correo = correo;
        this.creadoPor = creadoPor;
        this.fechaCreacion = fechaCreacion;
        this.activo = true;
    }

    public void actualizarInformacion(String direccion, String telefono, String correo) {
        this.direccion = direccion;
        this.telefono = telefono;
        this.correo = correo;
        this.fechaActualizacion = java.time.LocalDate.now().toString();
    }

    public void inactivar() {
        this.activo = false;
        this.fechaInactivacion = java.time.LocalDate.now().toString();
    }

    public boolean isActivo() {
        return activo;
    }

    public String getDocumento() {
        return documento;
    }

    public String getNombre() {
        return nombre;
    }

    @Override
    public String toString() {
        return "Cliente{" +
                "documento='" + documento + '\'' +
                ", nombre='" + nombre + '\'' +
                ", activo=" + activo +
                '}';
    }
}
