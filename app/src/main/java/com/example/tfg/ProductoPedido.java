package com.example.tfg;

import com.google.firebase.firestore.PropertyName;

public class ProductoPedido {
    private String nombre;
    private String imagen;
    private String precio;
    private String categoria;
    private String descripcion; // Añadido

    // Constructor vacío para Firebase
    public ProductoPedido() {}

    public ProductoPedido(String nombre, String imagen, String precio, String categoria) {
        this.nombre = nombre;
        this.imagen = imagen;
        this.precio = precio;
        this.categoria = categoria;
    }

    // Getters y setters
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getImagen() {
        return imagen;
    }


    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public String getPrecio() {
        return precio;
    }

    public void setPrecio(String precio) {
        this.precio = precio;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }
}
