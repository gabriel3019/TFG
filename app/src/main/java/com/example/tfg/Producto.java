package com.example.tfg;

import java.util.List;

/**
 * Clase que representa un producto del menú, incluyendo su nombre, descripción y lista de alérgenos.
 */
public class Producto {
    private String nombre;
    private String descripcion;
    private List<String> alergenos;

    /**
     * Constructor para crear un producto con su nombre, descripción y lista de alérgenos.
     *
     * @param nombre     Nombre del producto.
     * @param descripcion Descripción del producto.
     * @param alergenos  Lista de alérgenos que contiene el producto.
     */
    public Producto(String nombre, String descripcion, List<String> alergenos) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.alergenos = alergenos;
    }

    // Getters
    /**
     * Obtiene el nombre del producto.
     *
     * @return Nombre del producto.
     */
    public String getNombre() { return nombre; }
    /**
     * Obtiene la descripción del producto.
     *
     * @return Descripción del producto.
     */
    public String getDescripcion() { return descripcion; }
    /**
     * Obtiene la lista de alérgenos del producto.
     *
     * @return Lista de alérgenos.
     */
    public List<String> getAlergenos() { return alergenos; }

    /**
     * Verifica si el producto contiene alguno de los alérgenos seleccionados.
     *
     * @param alergenosSeleccionados Lista de alérgenos que se quieren evitar.
     * @return {@code true} si el producto contiene al menos uno de los alérgenos seleccionados, {@code false} en caso contrario.
     */
    public boolean contieneAlergenos(List<String> alergenosSeleccionados) {
        for (String alergeno : alergenosSeleccionados) {
            if (alergenos.contains(alergeno)) {
                return true;
            }
        }
        return false;
    }
}
