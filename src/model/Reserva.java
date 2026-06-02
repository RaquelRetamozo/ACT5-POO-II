package model;

public class Reserva {
    //Atributos
    private int id;
    private String nombreCliente;
    private String fechaIngreso;
    private String fechaSalida;
    private String habitacion;

    //Constructor
    public Reserva(int id, String nombreCliente, String fechaIngreso, String fechaSalida, String habitacion) {
        this.id = id;
        this.nombreCliente = nombreCliente;
        this.fechaIngreso = fechaIngreso;
        this.fechaSalida = fechaSalida;
        this.habitacion = habitacion;
    }
    public Reserva() {
    }

    //Getters y Setters
    public String getFechaIngreso() {
        return fechaIngreso;
    }

    public void setFechaIngreso(String fechaIngreso) {
        this.fechaIngreso = fechaIngreso;
    }

    public String getFechaSalida() {
        return fechaSalida;
    }

    public void setFechaSalida(String fechaSalida) {
        this.fechaSalida = fechaSalida;
    }

    public String getHabitacion() {
        return habitacion;
    }

    public void setHabitacion(String habitacion) {
        this.habitacion = habitacion;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombreCliente() {
        return nombreCliente;
    }

    public void setNombreCliente(String nombreCliente) {
        this.nombreCliente = nombreCliente;
    }

    //toString
    @Override
    public String toString() {
        return id + "|" + nombreCliente + "|" + fechaIngreso + "|" + fechaSalida + "|" + habitacion;
    }
}