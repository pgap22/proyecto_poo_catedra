package com.multiworks.cliente;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.util.Date;

public class ClienteForm extends JFrame {
    private JTextField nombreField;
    private JTextField documentoField;
    private JTextField telefonoField;
    private JTextField correoField;
    private JTextField direccionField;
    private JButton guardarButton;

    private ClienteDAO clienteDAO;

    public ClienteForm(ClienteDAO clienteDAO) {
        this.clienteDAO = clienteDAO;
        setTitle("Registrar Cliente");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(7, 2));

        panel.add(new JLabel("Nombre:"));
        nombreField = new JTextField();
        panel.add(nombreField);

        panel.add(new JLabel("DUI (Documento de Identificación):"));
        documentoField = new JTextField();
        panel.add(documentoField);

        panel.add(new JLabel("Teléfono:"));
        telefonoField = new JTextField();
        panel.add(telefonoField);

        panel.add(new JLabel("Correo:"));
        correoField = new JTextField();
        panel.add(correoField);

        panel.add(new JLabel("Dirección:"));
        direccionField = new JTextField();
        panel.add(direccionField);

        guardarButton = new JButton("Guardar");
        panel.add(guardarButton);

        guardarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nombre = nombreField.getText();
                String documento = documentoField.getText();
                String telefono = telefonoField.getText();
                String correo = correoField.getText();
                String direccion = direccionField.getText();

                Cliente cliente = new Cliente(
                        0, nombre, documento, "Natural", telefono, correo, direccion, true,
                        "Admin", new Date(), null, null
                );

                try {
                    clienteDAO.agregarCliente(cliente);
                    JOptionPane.showMessageDialog(null, "Cliente registrado exitosamente.");
                } catch (Exception ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(null, "Error al registrar cliente.");
                }
            }
        });

        add(panel);
    }

    // Método principal para ejecutar el formulario
    public static void main(String[] args) {
        Connection connection = null;
        ClienteDAO clienteDAO = new ClienteDAO(connection);
        ClienteForm form = new ClienteForm(clienteDAO);
        form.setVisible(true);
    }
}

