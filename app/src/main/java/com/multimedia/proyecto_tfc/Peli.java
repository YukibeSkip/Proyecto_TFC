package com.multimedia.proyecto_tfc;

public class Peli {
    //Atributos
    private String idNombreUsuario;
    private String numero_peli;
    private String director;
    private String nombre;
    private String estado;
    private String valoracion;

    //Contructores

    public Peli(String idNombreUsuario, String numero_peli, String director, String nombre, String estado, String valoracion) {
        this.idNombreUsuario = idNombreUsuario;
        this.numero_peli = numero_peli;
        this.director = director;
        this.nombre = nombre;
        this.estado = estado;
        this.valoracion = valoracion;
    }

    public Peli(String numero_peli, String director, String nombre, String estado, String valoracion) {
        this.numero_peli = numero_peli;
        this.director = director;
        this.nombre = nombre;
        this.estado = estado;
        this.valoracion = valoracion;
    }

    public Peli() {
    }

    //Getters y Setters

    public String getIdNombreUsuario() {
        return idNombreUsuario;
    }

    public void setIdNombreUsuario(String idNombreUsuario) {
        this.idNombreUsuario = idNombreUsuario;
    }

    public String getNumero_peli() {
        return numero_peli;
    }

    public void setNumero_peli(String numero_peli) {
        this.numero_peli = numero_peli;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getValoracion() {
        return valoracion;
    }

    public void setValoracion(String valoracion) {
        this.valoracion = valoracion;
    }
}
