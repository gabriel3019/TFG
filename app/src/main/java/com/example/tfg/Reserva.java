package com.example.tfg;

public class Reserva {

    private String provincia;
    private String restaurante;
    private String fecha;
    private String hora;
    private String personas;

    public Reserva(String provincia, String restaurante, String fecha, String hora, String personas) {
        this.provincia = provincia;
        this.restaurante = restaurante;
        this.fecha = fecha;
        this.hora = hora;
        this.personas = personas;
    }

    public String getProvincia() {
        return provincia;
    }

    public void setProvincia(String provincia) {
        this.provincia = provincia;
    }

    public String getRestaurante() {
        return restaurante;
    }

    public void setRestaurante(String restaurante) {
        this.restaurante = restaurante;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public String getPersonas() {
        return personas;
    }

    public void setPersonas(String personas) {
        this.personas = personas;
    }
}
