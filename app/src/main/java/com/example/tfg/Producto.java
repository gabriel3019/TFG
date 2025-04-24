package com.example.tfg;

import java.util.List;

public class Producto {
    private String nombre;
    private String descripcion;
    private List<String> alergenos;

    public Producto(String nombre, String descripcion, List<String> alergenos) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.alergenos = alergenos;
    }

    // Getters
    public String getNombre() { return nombre; }
    public String getDescripcion() { return descripcion; }
    public List<String> getAlergenos() { return alergenos; }


    public boolean contieneAlergenos(List<String> alergenosSeleccionados) {
        for (String alergeno : alergenosSeleccionados) {
            if (alergenos.contains(alergeno)) {
                return true;
            }
        }
        return false;
    }
}
