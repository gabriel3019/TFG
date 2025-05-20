package com.example.tfg;

/**
 * La clase {@code Reserva} representa una reserva realizada por un usuario en un restaurante.
 * Contiene información sobre la provincia, restaurante, fecha, hora y número de personas.
 *
 * Esta clase se puede usar para almacenar y recuperar datos de reserva desde una base de datos o para pasar entre actividades.
 *
 */
public class Reserva {

    private String provincia;
    private String restaurante;
    private String fecha;
    private String hora;
    private String personas;

    /**
     * Constructor que inicializa todos los campos de una reserva.
     *
     * @param provincia Provincia donde se encuentra el restaurante
     * @param restaurante Nombre del restaurante reservado
     * @param fecha Fecha de la reserva
     * @param hora Hora de la reserva
     * @param personas Número de personas para la reserva
     */
    public Reserva(String provincia, String restaurante, String fecha, String hora, String personas) {
        this.provincia = provincia;
        this.restaurante = restaurante;
        this.fecha = fecha;
        this.hora = hora;
        this.personas = personas;
    }

    /**
     * Obtiene la provincia de la reserva.
     *
     * @return provincia
     */
    public String getProvincia() {
        return provincia;
    }

    /**
     * Establece la provincia de la reserva.
     *
     * @param provincia Provincia donde se encuentra el restaurante
     */
    public void setProvincia(String provincia) {
        this.provincia = provincia;
    }


    /**
     * Obtiene el nombre del restaurante.
     *
     * @return restaurante
     */
    public String getRestaurante() {
        return restaurante;
    }

    /**
     * Establece el nombre del restaurante.
     *
     * @param restaurante Nombre del restaurante reservado
     */
    public void setRestaurante(String restaurante) {
        this.restaurante = restaurante;
    }

    /**
     * Obtiene la fecha de la reserva.
     *
     * @return fecha
     */
    public String getFecha() {
        return fecha;
    }

    /**
     * Establece la fecha de la reserva.
     *
     * @param fecha Fecha de la reserva
     */
    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    /**
     * Obtiene la hora de la reserva.
     *
     * @return hora
     */
    public String getHora() {
        return hora;
    }

    /**
     * Establece la hora de la reserva.
     *
     * @param hora Hora de la reserva
     */
    public void setHora(String hora) {
        this.hora = hora;
    }

    /**
     * Obtiene el número de personas para la reserva.
     *
     * @return personas
     */
    public String getPersonas() {
        return personas;
    }

    /**
     * Establece el número de personas para la reserva.
     *
     * @param personas Número de personas
     */
    public void setPersonas(String personas) {
        this.personas = personas;
    }
}
