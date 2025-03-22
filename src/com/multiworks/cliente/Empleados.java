package com.multiworks.cliente;

import java.util.Date;

    // Atributos
    private String nombre;
    private String contexto;
    private String correoContacto;
    private String documento;
    private String tipoContratacion;
    private String direccion;
    private String numeroContacto;
    private Date fechaActualizacion;
    private String estado;
    private Date fechaInactivacion;

    // Constructor
    public Empleados(String nombre, String contexto, String correoContacto, String documento, String tipoContratacion, String direccion, String numeroContacto) {
        this.nombre = nombre;
        this.contexto = contexto;
        this.correoContacto = correoContacto;
        this.documento = documento;
        this.tipoContratacion = tipoContratacion;
        this.direccion = direccion;
        this.numeroContacto = numeroContacto;
        this.estado = "Activo"; // Por defecto, el empleado está activo
        this.fechaActualizacion = new Date(); // Fecha de actualización automática al crear el empleado
    }

    // Métodos

    /**
     * Asigna una actividad al empleado.
     */
    public void asignarActividad() {
        System.out.println("Actividad asignada al empleado: " + this.nombre);
    }

    /**
     * Actualiza la información del empleado.
     */
    public void actualizarInformacion(String nuevoNombre, String nuevaDireccion, String nuevoNumeroContacto) {
        this.nombre = nuevoNombre;
        this.direccion = nuevaDireccion;
        this.numeroContacto = nuevoNumeroContacto;
        this.fechaActualizacion = new Date(); // Actualiza la fecha de actualización
        System.out.println("Información del empleado actualizada.");
    }

    /**
     * Inactiva al empleado.
     */
    public void inactivarEmpleado() {
        this.estado = "Inactivo";
        this.fechaInactivacion = new Date(); // Registra la fecha de inactivación
        System.out.println("Empleado inactivado: " + this.nombre);
    }

    /**
     * Muestra la información del empleado.
     */
    public void mostrarInformacion() {
        System.out.println("Nombre: " + this.nombre);
        System.out.println("Contexto: " + this.contexto);
        System.out.println("Correo de contacto: " + this.correoContacto);
        System.out.println("Documento: " + this.documento);
        System.out.println("Tipo de contratación: " + this.tipoContratacion);
        System.out.println("Dirección: " + this.direccion);
        System.out.println("Número de contacto: " + this.numeroContacto);
        System.out.println("Fecha de actualización: " + this.fechaActualizacion);
        System.out.println("Estado: " + this.estado);
        System.out.println("Fecha de inactivación: " + this.fechaInactivacion);
    }
}