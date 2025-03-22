package com.multiworks.cliente;

import java.util.ArrayList;
import java.util.List;

public class ClienteDAO {
    private List<Cliente> clientes;

    public ClienteDAO() {
        this.clientes = new ArrayList<>();
    }

    public void agregarCliente(Cliente cliente) {
        clientes.add(cliente);
    }

    public Cliente buscarPorDocumento(String documento) {
        return clientes.stream().filter(c -> c.getDocumento().equals(documento)).findFirst().orElse(null);
    }

    public List<Cliente> listarClientes() {
        return clientes;
    }

    public boolean actualizarCliente(String documento, String direccion, String telefono, String correo) {
        Cliente cliente = buscarPorDocumento(documento);
        if (cliente != null) {
            cliente.actualizarInformacion(direccion, telefono, correo);
            return true;
        }
        return false;
    }

    public boolean inactivarCliente(String documento) {
        Cliente cliente = buscarPorDocumento(documento);
        if (cliente != null) {
            cliente.inactivar();
            return true;
        }
        return false;
    }
}
