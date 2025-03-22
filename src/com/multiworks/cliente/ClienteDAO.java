package com.multiworks.cliente;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ClienteDAO {
    private Connection connection;
    
    public ClienteDAO(Connection connection) {
        this.connection = connection;
    }

    public void agregarCliente(Cliente cliente) throws SQLException {
        String query = "INSERT INTO clientes (nombre, documento_identificacion, tipo_persona, telefono, correo, direccion, estado, creado_por, fecha_creacion) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, cliente.getNombre());
            stmt.setString(2, cliente.getDocumentoIdentificacion());
            stmt.setString(3, cliente.getTipoPersona());
            stmt.setString(4, cliente.getTelefono());
            stmt.setString(5, cliente.getCorreo());
            stmt.setString(6, cliente.getDireccion());
            stmt.setBoolean(7, cliente.isEstado());
            stmt.setString(8, cliente.getCreadoPor());
            stmt.setDate(9, new java.sql.Date(cliente.getFechaCreacion().getTime()));

            stmt.executeUpdate();
        }
    }

    public List<Cliente> obtenerClientes() throws SQLException {
        String query = "SELECT * FROM clientes";
        List<Cliente> clientes = new ArrayList<>();
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                clientes.add(new Cliente(
                        rs.getInt("id"),
                        rs.getString("nombre"),
                        rs.getString("documento_identificacion"),
                        rs.getString("tipo_persona"),
                        rs.getString("telefono"),
                        rs.getString("correo"),
                        rs.getString("direccion"),
                        rs.getBoolean("estado"),
                        rs.getString("creado_por"),
                        rs.getDate("fecha_creacion"),
                        rs.getDate("fecha_actualizacion"),
                        rs.getDate("fecha_inactivacion")
                ));
            }
        }
        return clientes;
    }

    public void actualizarCliente(Cliente cliente) throws SQLException {
        String query = "UPDATE clientes SET nombre=?, documento_identificacion=?, tipo_persona=?, telefono=?, correo=?, direccion=?, estado=?, fecha_actualizacion=? WHERE id=?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, cliente.getNombre());
            stmt.setString(2, cliente.getDocumentoIdentificacion());
            stmt.setString(3, cliente.getTipoPersona());
            stmt.setString(4, cliente.getTelefono());
            stmt.setString(5, cliente.getCorreo());
            stmt.setString(6, cliente.getDireccion());
            stmt.setBoolean(7, cliente.isEstado());
            stmt.setDate(8, new java.sql.Date(cliente.getFechaActualizacion().getTime()));
            stmt.setInt(9, cliente.getId());

            stmt.executeUpdate();
        }
    }

    public void eliminarCliente(int id) throws SQLException {
        String query = "DELETE FROM clientes WHERE id=?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }
}
