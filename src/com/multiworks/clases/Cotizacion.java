package com.multiworks.clases;

import java.util.Date;
import java.util.ArrayList;
import java.util.List;

public class Cotizacion {
    // Atributos privados
    private int id;
    private Date fechaInicio;
    private Date fechaFin;
    private int cantidadHoras;
    private double costo;
    private double costoAdicional;
    private double costoAsignacion;
    private List<Actividad> actividades;

    // Constructor
    public Cotizacion(int id, Date fechaInicio, Date fechaFin, int cantidadHoras, double costo, double costoAdicional, double costoAsignacion) {
        this.id = id;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.cantidadHoras = cantidadHoras;
        this.costo = costo;
        this.costoAdicional = costoAdicional;
        this.costoAsignacion = costoAsignacion;
        this.actividades = new ArrayList<>();
    }

    // Métodos Getters y Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public Date getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(Date fechaFin) {
        this.fechaFin = fechaFin;
    }

    public int getCantidadHoras() {
        return cantidadHoras;
    }

    public void setCantidadHoras(int cantidadHoras) {
        this.cantidadHoras = cantidadHoras;
    }

    public double getCosto() {
        return costo;
    }

    public void setCosto(double costo) {
        this.costo = costo;
    }

    public double getCostoAdicional() {
        return costoAdicional;
    }

    public void setCostoAdicional(double costoAdicional) {
        this.costoAdicional = costoAdicional;
    }

    public double getCostoAsignacion() {
        return costoAsignacion;
    }

    public void setCostoAsignacion(double costoAsignacion) {
        this.costoAsignacion = costoAsignacion;
    }

    public List<Actividad> getActividades() {
        return actividades;
    }

    // Métodos funcionales
    public void añadirActividad(Actividad actividad) {
        this.actividades.add(actividad);
    }

    public double calcularCostoTotal() {
        return costo + costoAdicional + costoAsignacion;
    }

    public void finalizar() {
        System.out.println("Cotización finalizada.");
    }

    public boolean validar() {
        return fechaInicio != null && fechaFin != null && cantidadHoras > 0 && costo >= 0;
    }

    @Override
    public String toString() {
        return "com.multiworks.clases.Cotizacion{" +
                "id=" + id +
                ", fechaInicio=" + fechaInicio +
                ", fechaFin=" + fechaFin +
                ", cantidadHoras=" + cantidadHoras +
                ", costo=" + costo +
                ", costoAdicional=" + costoAdicional +
                ", costoAsignacion=" + costoAsignacion +
                ", actividades=" + actividades +
                '}';
    }
}