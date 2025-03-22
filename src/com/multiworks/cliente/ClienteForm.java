package com.multiworks.cliente;

import javax.swing.*;
import java.awt.*;

public class ClienteForm extends JFrame {
    private ClienteDAO clienteDAO;

    public ClienteForm() {
        clienteDAO = new ClienteDAO();
        initUI();
    }

    private void initUI() {
        setTitle("Gestión de Clientes");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(7, 2));

        JTextField txtDocumento = new JTextField();
        JTextField txtNombre = new JTextField();
        JTextField txtTipoPersona = new JTextField();
        JTextField txtDireccion = new JTextField();
        JTextField txtTelefono = new JTextField();
        JTextField txtCorreo = new JTextField();

        JButton btnAgregar = new JButton("Agregar Cliente");
        JButton btnListar = new JButton("Listar Clientes");

        panel.add(new JLabel("Documento:"));
        panel.add(txtDocumento);
        panel.add(new JLabel("Nombre:"));
        panel.add(txtNombre);
        panel.add(new JLabel("Tipo de Persona:"));
        panel.add(txtTipoPersona);
        panel.add(new JLabel("Dirección:"));
        panel.add(txtDireccion);
        panel.add(new JLabel("Teléfono:"));
        panel.add(txtTelefono);
        panel.add(new JLabel("Correo:"));
        panel.add(txtCorreo);
        panel.add(btnAgregar);
        panel.add(btnListar);

        btnAgregar.addActionListener(e -> {
            Cliente cliente = new Cliente(
                    txtDocumento.getText(),
                    txtNombre.getText(),
                    txtTipoPersona.getText(),
                    txtDireccion.getText(),
                    txtTelefono.getText(),
                    txtCorreo.getText(),
                    "admin@example.com",
                    java.time.LocalDate.now().toString()
            );
            clienteDAO.agregarCliente(cliente);
            JOptionPane.showMessageDialog(this, "Cliente agregado: " + cliente.getNombre());
        });

        btnListar.addActionListener(e -> {
            StringBuilder lista = new StringBuilder("Clientes:\n");
            for (Cliente c : clienteDAO.listarClientes()) {
                lista.append(c).append("\n");
            }
            JOptionPane.showMessageDialog(this, lista.toString());
        });

        add(panel);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new ClienteForm().setVisible(true));
    }
}

