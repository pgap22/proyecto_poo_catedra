package com.multiworks.clases;

import java.util.Date;
import java.util.Scanner;

public class PrimeraFase {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Usuario usuario = null;

        while (true) {
            System.out.println("\n1. Iniciar sesión\n2. Salir");
            int opcion = scanner.nextInt();

            if (opcion == 1) {
                System.out.println("Ingrese su correo:");
                String correo = scanner.next();
                System.out.println("Ingrese su contraseña:");
                String contraseña = scanner.next();

                usuario = new Usuario("Admin", contraseña, correo);
                if (usuario.autenticar(contraseña)) {
                    gestionarSistema(usuario, scanner);
                } else {
                    System.out.println("Autenticación fallida. Intente nuevamente.");
                }
            } else if (opcion == 2) {
                System.out.println("Saliendo...");
                break;
            } else {
                System.out.println("Opción inválida. Intente de nuevo.");
            }
        }
        scanner.close();
    }

    private static void gestionarSistema(Usuario usuario, Scanner scanner) {
        while (true) {
            System.out.println("\n1. Gestionar Clientes\n2. Gestionar Empleados\n3. Crear Cotización\n4. Salir");
            int opcion = scanner.nextInt();

            switch (opcion) {
                case 1:
                    gestionarClientes(usuario, scanner);
                    break;
                case 2:
                    gestionarEmpleados(usuario, scanner);
                    break;
                case 3:
                    crearCotizacion(usuario, scanner);
                    break;
                case 4:
                    return;
                default:
                    System.out.println("Opción inválida. Intente nuevamente.");
            }
        }
    }

    private static void gestionarClientes(Usuario usuario, Scanner scanner) {
        System.out.println("Ingrese los datos del cliente:");
        System.out.println("Documento:");
        String documento = scanner.next();
        System.out.println("Nombre:");
        String nombre = scanner.next();
        System.out.println("Tipo (Natural/Jurídico):");
        String tipoPersona = scanner.next();
        System.out.println("Dirección:");
        String direccion = scanner.next();
        System.out.println("Teléfono:");
        String telefono = scanner.next();
        System.out.println("Correo:");
        String correo = scanner.next();
        System.out.println("Creado por:");
        String creadoPor = scanner.next();
        System.out.println("Fecha de creación (yyyy-MM-dd):");
        String fechaCreacion = scanner.next();

        Cliente cliente = new Cliente(documento, nombre, tipoPersona, direccion, telefono, correo, creadoPor, fechaCreacion);
        usuario.crearCliente(cliente);
        System.out.println("Cliente creado: " + cliente);
    }

    private static void gestionarEmpleados(Usuario usuario, Scanner scanner) {
        System.out.println("Ingrese los datos del empleado:");
        System.out.println("Nombre:");
        String nombre = scanner.next();
        System.out.println("Contexto:");
        String contexto = scanner.next();
        System.out.println("Correo de contacto:");
        String correoContacto = scanner.next();
        System.out.println("Documento:");
        String documento = scanner.next();
        System.out.println("Tipo de contratación (Permanente/Por horas):");
        String tipoContratacion = scanner.next();
        System.out.println("Dirección:");
        String direccion = scanner.next();
        System.out.println("Número de contacto:");
        String numeroContacto = scanner.next();

        Empleado empleado = new Empleado(nombre, contexto, correoContacto, documento, tipoContratacion, direccion, numeroContacto);
        usuario.crearEmpleado(empleado);
        System.out.println("Empleado creado: " + empleado.getNombre());
    }

    private static void crearCotizacion(Usuario usuario, Scanner scanner) {
        System.out.println("Ingrese los datos de la cotización:");
        System.out.println("ID:");
        int id = scanner.nextInt();
        System.out.println("Fecha de inicio (yyyy-MM-dd):");
        String fechaInicio = scanner.next();
        System.out.println("Fecha de fin (yyyy-MM-dd):");
        String fechaFin = scanner.next();
        System.out.println("Cantidad de horas estimadas:");
        int cantidadHoras = scanner.nextInt();
        System.out.println("Costo base:");
        double costo = scanner.nextDouble();
        System.out.println("Costo adicional:");
        double costoAdicional = scanner.nextDouble();
        System.out.println("Costo de asignación:");
        double costoAsignacion = scanner.nextDouble();

        Cotizacion cotizacion = new Cotizacion(id, new Date(), new Date(), cantidadHoras, costo, costoAdicional, costoAsignacion);
        System.out.println("Cotización creada: " + cotizacion);

        // Asignar actividades a la cotización
        System.out.println("¿Desea agregar actividades a la cotización? (si/no)");
        String agregarActividades = scanner.next();
        if (agregarActividades.equalsIgnoreCase("si")) {
            while (true) {
                System.out.println("Ingrese los detalles de la actividad:");
                System.out.println("ID de la actividad:");
                int actividadId = scanner.nextInt();
                System.out.println("Título:");
                String titulo = scanner.next();
                System.out.println("Descripción:");
                String descripcion = scanner.next();

                Actividad actividad = new Actividad(actividadId, titulo, descripcion, cotizacion);
                cotizacion.añadirActividad(actividad);

                System.out.println("Actividad agregada: " + actividad);

                System.out.println("¿Desea agregar otra actividad? (si/no)");
                String otraActividad = scanner.next();
                if (!otraActividad.equalsIgnoreCase("si")) {
                    break;
                }
            }
        }
    }
}
