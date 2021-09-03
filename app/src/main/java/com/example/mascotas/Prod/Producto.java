package com.example.mascotas.Prod;

import java.io.Serializable;

public class Producto implements Serializable {
    private String nombre;
    private String precio;
    private String descripcion;
    private String foto;

    public Producto(String nombre, String precio, String descripcion,String foto) {
        this.nombre = nombre;
        this.precio = precio;
        this.descripcion = descripcion;
        this.foto=foto;
    }

    public String getNombre() { return nombre; }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPrecio() {
        return precio;
    }

    public void setPrecio(String precio) {
        this.precio = precio;
    }

    public String getDescripcion() { return descripcion; }


    public String getFoto() { return foto; }

    public void setFoto(String foto) { this.foto = foto; }
}
