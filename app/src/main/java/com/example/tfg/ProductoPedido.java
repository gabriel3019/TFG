package com.example.tfg;

import com.google.firebase.firestore.PropertyName;

/**
 * Clase que representa un producto incluido en un pedido.
 * Esta clase se utiliza para modelar la información de los productos seleccionados,
 * como nombre, imagen, precio y categoría.
 */
public class ProductoPedido {
    private String nombre;
    private String imagen;
    private String precio;
    private String categoria;
    private String descripcion; // Añadido

    // Constructor vacío para Firebase
    /**
     * Constructor vacío requerido por Firebase Firestore para la deserialización automática.
     */
    public ProductoPedido() {}

    /**
     * Constructor para crear un producto de pedido con sus datos principales.
     *
     * @param nombre    Nombre del producto.
     * @param imagen    URL o nombre del recurso de la imagen.
     * @param precio    Precio del producto.
     * @param categoria Categoría del producto.
     */
    public ProductoPedido(String nombre, String imagen, String precio, String categoria) {
        this.nombre = nombre;
        this.imagen = imagen;
        this.precio = precio;
        this.categoria = categoria;
    }

    // Getters y setters
    /** @return Nombre del producto. */
    public String getNombre() {
        return nombre;
    }

    /** @param nombre Nombre del producto. */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /** @return URL o referencia de la imagen del producto. */
    public String getImagen() {
        return imagen;
    }


    /** @param imagen URL o referencia de la imagen del producto. */
    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    /** @return Precio del producto. */
    public String getPrecio() {
        return precio;
    }

    /** @param precio Precio del producto. */
    public void setPrecio(String precio) {
        this.precio = precio;
    }

    /** @return Categoría del producto. */
    public String getCategoria() {
        return categoria;
    }

    /** @param categoria Categoría del producto. */
    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }
}
