import com.multiworks.clases.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

// Excepción personalizada para cancelación de operaciones
class OperacionCanceladaException extends Exception {
    public OperacionCanceladaException(String mensaje) {
        super(mensaje);
    }
}

public class PrimeraFase {

    private static final SimpleDateFormat FORMATO_FECHA = new SimpleDateFormat("yyyy-MM-dd");
    private static final String PALABRA_CANCELAR = "cancelar";

    // Variables para el auto-incremento de IDs
    private static int nextCotizacionId = 1;
    private static int nextActividadId = 1;

    // Lista global para almacenar empleados creados
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Usuario usuario = null;

        while (true) {
            System.out.println("\n=== MENÚ PRINCIPAL ===");
            System.out.println("1. Iniciar sesión");
            System.out.println("2. Salir");
            System.out.print("Seleccione una opción: ");
            int opcion = obtenerEntero(scanner);

            if (opcion == 1) {
                usuario = iniciarSesion(scanner);
                if (usuario != null) {
                    gestionarSistema(usuario, scanner);
                }
            } else if (opcion == 2) {
                System.out.println("Saliendo del sistema...");
                break;
            } else {
                System.out.println("Opción inválida. Intente de nuevo.");
            }
        }
        scanner.close();
    }

    private static Usuario iniciarSesion(Scanner scanner) {
        System.out.println("\n=== INICIO DE SESIÓN ===");
        try {
            String correo = leerEntrada(scanner, "Ingrese su correo (o 'cancelar' para salir): ");
            String contraseña = leerEntrada(scanner, "Ingrese su contraseña (o 'cancelar' para salir): ");

            // Usuario predefinido para fines de prueba
            Usuario usuario = new Usuario("Admin", "123", "admin@correo.com");

            if (usuario.autenticar(contraseña)) {
                System.out.println("Autenticación exitosa. Bienvenido, " + usuario.getNombre() + "!");
                return usuario;
            } else {
                System.out.println("Autenticación fallida. Intente nuevamente.");
                return null;
            }
        } catch (OperacionCanceladaException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    private static void gestionarSistema(Usuario usuario, Scanner scanner) {
        while (true) {
            System.out.println("\n=== GESTIÓN DEL SISTEMA ===");
            System.out.println("1. Gestionar Clientes");
            System.out.println("2. Gestionar Empleados");
            System.out.println("3. Crear Cotización");
            System.out.println("4. Cerrar sesión");
            System.out.print("Seleccione una opción: ");
            int opcion = obtenerEntero(scanner);

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
                    System.out.println("Cerrando sesión...");
                    return;
                default:
                    System.out.println("Opción inválida. Intente nuevamente.");
            }
        }
    }

    private static void gestionarClientes(Usuario usuario, Scanner scanner) {
        System.out.println("\n=== GESTIÓN DE CLIENTES ===");
        try {
            String documento = leerEntrada(scanner, "Documento (o 'cancelar' para salir): ");
            String nombre = leerEntrada(scanner, "Nombre (o 'cancelar' para salir): ");

            // Selección validada del tipo de cliente
            String tipoPersona = "";
            while (true) {
                System.out.println("Seleccione el tipo de cliente:");
                System.out.println("1. Natural");
                System.out.println("2. Jurídico");
                System.out.println("0. Cancelar creación");
                int opcion = obtenerEntero(scanner);
                if (opcion == 0) {
                    System.out.println("Creación de cliente cancelada.");
                    return;
                } else if (opcion == 1) {
                    tipoPersona = "Natural";
                    break;
                } else if (opcion == 2) {
                    tipoPersona = "Jurídico";
                    break;
                } else {
                    System.out.println("Opción inválida. Intente nuevamente.");
                }
            }

            String direccion = leerEntrada(scanner, "Dirección (o 'cancelar' para salir): ");
            String telefono = leerEntrada(scanner, "Teléfono (o 'cancelar' para salir): ");
            String correo = leerEntrada(scanner, "Correo (o 'cancelar' para salir): ");
            // Se puede asignar la fecha de creación desde el sistema o dejarla vacía
            String fechaCreacion = "";

            Cliente cliente = new Cliente(documento, nombre, tipoPersona, direccion, telefono, correo, usuario.getNombre(), fechaCreacion);
            usuario.crearCliente(cliente);
            System.out.println("Cliente creado: " + cliente);
        } catch (OperacionCanceladaException e) {
            System.out.println("Operación cancelada: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("Error al crear el cliente: " + e.getMessage());
        }
    }

    private static void gestionarEmpleados(Usuario usuario, Scanner scanner) {
        System.out.println("\n=== GESTIÓN DE EMPLEADOS ===");
        try {
            String nombre = leerEntrada(scanner, "Nombre (o 'cancelar' para salir): ");
            String correoContacto = leerEntrada(scanner, "Correo de contacto (o 'cancelar' para salir): ");
            String documento = leerEntrada(scanner, "Documento (o 'cancelar' para salir): ");

            // Selección validada del tipo de empleado (limitado a Natural o Jurídico)
            String tipoEmpleado = "";
            while (true) {
                System.out.println("Seleccione el tipo de empleado:");
                System.out.println("1. Natural");
                System.out.println("2. Jurídico");
                System.out.println("0. Cancelar creación");
                int op = obtenerEntero(scanner);
                if (op == 0) {
                    System.out.println("Creación de empleado cancelada.");
                    return;
                } else if (op == 1) {
                    tipoEmpleado = "Natural";
                    break;
                } else if (op == 2) {
                    tipoEmpleado = "Jurídico";
                    break;
                } else {
                    System.out.println("Opción inválida. Intente nuevamente.");
                }
            }

            String direccion = leerEntrada(scanner, "Dirección (o 'cancelar' para salir): ");
            String numeroContacto = leerEntrada(scanner, "Número de contacto (o 'cancelar' para salir): ");

            Empleado empleado = new Empleado(nombre, correoContacto, documento, tipoEmpleado, direccion, numeroContacto);
            usuario.crearEmpleado(empleado);
            System.out.println("Empleado creado: " + empleado.getNombre());
        } catch (OperacionCanceladaException e) {
            System.out.println("Operación cancelada: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("Error al crear el empleado: " + e.getMessage());
        }
    }

    private static void crearCotizacion(Usuario usuario, Scanner scanner) {
        System.out.println("\n=== CREAR COTIZACIÓN ===");
        try {
            // Verificar que exista al menos un cliente registrado
            if (usuario.getClientes().isEmpty()) {
                System.out.println("No hay clientes registrados. Por favor, registre un cliente primero.");
                return;
            }

            // Mostrar lista de clientes para que el usuario seleccione uno
            System.out.println("Seleccione un cliente de la lista:");
            for (int i = 0; i < usuario.getClientes().size(); i++) {
                Cliente cliente = usuario.getClientes().get(i);
                System.out.println((i + 1) + ". " + cliente.getNombre() + " - " + cliente.getDocumento());
            }
            int seleccion = obtenerEntero(scanner, "Seleccione una opción (número): ");
            if (seleccion < 1 || seleccion > usuario.getClientes().size()) {
                System.out.println("Selección inválida. Operación cancelada.");
                return;
            }
            Cliente clienteSeleccionado = usuario.getClientes().get(seleccion - 1);

            // Se asigna el ID automáticamente
            int id = nextCotizacionId++;
            String fechaInicioStr = leerEntrada(scanner, "Fecha de inicio (yyyy-MM-dd) (o 'cancelar' para salir): ");
            String fechaFinStr = leerEntrada(scanner, "Fecha de fin (yyyy-MM-dd) (o 'cancelar' para salir): ");
            Date fechaInicio = parsearFecha(fechaInicioStr);
            Date fechaFin = parsearFecha(fechaFinStr);
            int cantidadHoras = obtenerEntero(scanner, "Cantidad de horas estimadas: ");
            double costo = obtenerDouble(scanner, "Costo base: ");
            double costoAdicional = obtenerDouble(scanner, "Costo adicional: ");
            double costoAsignacion = obtenerDouble(scanner, "Costo de asignación: ");

            // Crear la cotización asociada al cliente seleccionado
            Cotizacion cotizacion = new Cotizacion(id, fechaInicio, fechaFin, cantidadHoras, costo, costoAdicional, costoAsignacion, clienteSeleccionado);
            System.out.println("Cotización creada: " + cotizacion);

            // Se fuerza que se agregue al menos una actividad a la cotización
            while (cotizacion.getActividades().isEmpty()) {
                System.out.print("¿Desea agregar actividades a la cotización? (si/no): ");
                String respuesta = scanner.next().trim();
                scanner.nextLine(); // limpiar el salto de línea

                if (respuesta.equalsIgnoreCase("si")) {
                    agregarActividades(cotizacion, scanner, usuario);
                } else {
                    System.out.println("Debe agregar al menos una actividad para continuar.");
                }
            }
        } catch (OperacionCanceladaException e) {
            System.out.println("Operación cancelada: " + e.getMessage());
        } catch (ParseException pe) {
            System.err.println("Error en el formato de la fecha: " + pe.getMessage());
        } catch (Exception e) {
            System.err.println("Error al crear la cotización: " + e.getMessage());
        }
    }

    // Se solicita también seleccionar un empleado para la actividad
    private static void agregarActividades(Cotizacion cotizacion, Scanner scanner, Usuario usuario) throws OperacionCanceladaException {
        while (true) {
            System.out.println("\n=== AGREGAR ACTIVIDAD ===");

            // Se asigna el ID automáticamente
            int actividadId = nextActividadId++;
            String titulo = leerEntrada(scanner, "Título (o 'cancelar' para salir): ");
            String descripcion = leerEntrada(scanner, "Descripción (o 'cancelar' para salir): ");

            // Validar que existan empleados registrados
            if (usuario.getEmpleados().isEmpty()) {
                System.out.println("No existen empleados creados para asignar a la actividad.");
                System.out.println("Debe registrar al menos un empleado antes de continuar.");
                return; // No permite continuar sin empleados
            }

            // Seleccionar un empleado obligatorio
            Empleado empleadoSeleccionado = null;
            while (empleadoSeleccionado == null) {
                System.out.println("Seleccione un empleado para asignar a la actividad:");
                for (int i = 0; i < usuario.getEmpleados().size(); i++) {
                    Empleado emp = usuario.getEmpleados().get(i);
                    System.out.println((i + 1) + ". " + emp.getNombre());
                }

                int seleccion = obtenerEntero(scanner, "Seleccione una opción: ");
                if (seleccion >= 1 && seleccion <= usuario.getEmpleados().size()) {
                    empleadoSeleccionado = usuario.getEmpleados().get(seleccion - 1);
                } else {
                    System.out.println("Selección inválida. Debe elegir un empleado obligatorio.");
                }
            }

            System.out.println("Empleado asignado: " + empleadoSeleccionado.getNombre());

            // Crear y asignar la actividad
            Actividad actividad = new Actividad(actividadId, titulo, descripcion, cotizacion, empleadoSeleccionado);
            cotizacion.añadirActividad(actividad);
            System.out.println("Actividad agregada: " + actividad);

            // Preguntar si desea agregar otra actividad
            System.out.print("¿Desea agregar otra actividad? (si/no): ");
            String otra = scanner.next().trim();
            scanner.nextLine(); // limpiar el salto de línea
            if (!otra.equalsIgnoreCase("si")) {
                break;
            }
        }
    }


    // Métodos auxiliares

    private static String leerEntrada(Scanner scanner, String mensaje) throws OperacionCanceladaException {
        String entrada;
        do {
            System.out.print(mensaje);
            entrada = scanner.nextLine().trim();
            if (entrada.equalsIgnoreCase(PALABRA_CANCELAR)) {
                throw new OperacionCanceladaException("Creación cancelada por el usuario.");
            }
            if (entrada.isEmpty()) {
                System.out.println("El campo no puede estar vacío. Inténtelo de nuevo.");
            }
        } while (entrada.isEmpty());
        return entrada;
    }

    private static int obtenerEntero(Scanner scanner) {
        while (!scanner.hasNextInt()) {
            System.out.println("Entrada no válida. Por favor, ingrese un número entero.");
            scanner.next();
        }
        int valor = scanner.nextInt();
        scanner.nextLine(); // limpiar el salto de línea
        return valor;
    }

    private static int obtenerEntero(Scanner scanner, String mensaje) {
        System.out.print(mensaje);
        return obtenerEntero(scanner);
    }

    private static double obtenerDouble(Scanner scanner, String mensaje) {
        System.out.print(mensaje);
        while (!scanner.hasNextDouble()) {
            System.out.println("Entrada no válida. Por favor, ingrese un número decimal.");
            scanner.next();
            System.out.print(mensaje);
        }
        double valor = scanner.nextDouble();
        scanner.nextLine(); // limpiar el salto de línea
        return valor;
    }

    private static Date parsearFecha(String fechaStr) throws ParseException {
        return FORMATO_FECHA.parse(fechaStr);
    }
}
