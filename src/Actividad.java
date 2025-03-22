import com.multiworks.cliente.Empleados;

public class Actividad {
    // Atributos privados
    private int id;
    private String titulo;
    private String descripcion;
    private Cotizacion cotizacion; // Relación de composición con Cotización
    private Empleados empleadoAsignado; // Relación de asignación con Empleado

    // Constructor
    public Actividad(int id, String titulo, String descripcion, Cotizacion cotizacion) {
        this.id = id;
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.cotizacion = cotizacion;
        this.empleadoAsignado = null; // Inicialmente no hay empleado asignado
    }

    //metodos Getters y Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Cotizacion getCotizacion() {
        return cotizacion;
    }

    public void setCotizacion(Cotizacion cotizacion) {
        this.cotizacion = cotizacion;
    }

    public Empleados getEmpleadoAsignado() {
        return empleadoAsignado;
    }

    //metodo para asignar un empleado a la actividad
    public void asignarEmpleado(Empleados empleado) {
        this.empleadoAsignado = empleado;
        System.out.println("Empleado " + empleado.getNombre() + " asignado a la actividad: " + this.titulo);
    }

    //Metodo para desasignar un empleado de la actividad
    public void desasignarEmpleado() {
        if (this.empleadoAsignado != null) {
            System.out.println("Empleado " + this.empleadoAsignado.getNombre() + " desasignado de la actividad: " + this.titulo);
            this.empleadoAsignado = null;
        } else {
            System.out.println("No hay empleado asignado a la actividad: " + this.titulo);
        }
    }

    //Metodo para mostrar la información de la actividad
    public void mostrarInformacion() {
        System.out.println("ID: " + this.id);
        System.out.println("Título: " + this.titulo);
        System.out.println("Descripción: " + this.descripcion);
        System.out.println("Cotización ID: " + this.cotizacion.getId());
        if (this.empleadoAsignado != null) {
            System.out.println("Empleado Asignado: " + this.empleadoAsignado.getNombre());
        } else {
            System.out.println("Empleado Asignado: Ninguno");
        }
    }

    @Override
    public String toString() {
        return "Actividad{" +
                "id=" + id +
                ", titulo='" + titulo + '\'' +
                ", descripcion='" + descripcion + '\'' +
                ", cotizacion=" + cotizacion.getId() +
                ", empleadoAsignado=" + (empleadoAsignado != null ? empleadoAsignado.getNombre() : "Ninguno") +
                '}';
    }
}