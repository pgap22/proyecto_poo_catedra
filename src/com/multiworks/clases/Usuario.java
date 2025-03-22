package com.multiworks.clases;

import java.util.ArrayList;
import java.util.List;

public class Usuario {
    private String nombre;
    private String password;
    private String email;
    private List<Cliente> clientes = new ArrayList<>();
    private List<Empleado> empleados = new ArrayList<>();

    public Usuario(String nombre, String password, String email) {
        this.nombre = nombre;
        this.password = password;
        this.email = email;
    }

    public Cliente crearCliente(Cliente cliente) {
        clientes.add(cliente);
        return cliente;
    }

    public Empleado crearEmpleado(Empleado empleado) {
        empleados.add(empleado);
        return empleado;
    }

    public boolean autenticar(String password) {
        return this.password.equals(password);
    }

    public boolean validar() {
        return nombre != null && !nombre.isEmpty() &&
                email != null && email.contains("@");
    }

    // Getters and Setters

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}