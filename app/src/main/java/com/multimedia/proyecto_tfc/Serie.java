package com.multimedia.proyecto_tfc;

public class Serie {

    //Atributos
    private String idNombreUsuario;
    private String numero_series;
    private String director;
    private String nombre;
    private String estado;
    private String valoracion;

    //Contructores
    public Serie(String idNombreUsuario, String numero_series, String director, String nombre, String estado, String valoracion) {
        this.idNombreUsuario = idNombreUsuario;
        this.numero_series = numero_series;
        this.director = director;
        this.nombre = nombre;
        this.estado = estado;
        this.valoracion = valoracion;
    }

    public Serie(String numero_series, String director, String nombre, String estado, String valoracion) {
        this.numero_series = numero_series;
        this.director = director;
        this.nombre = nombre;
        this.estado = estado;
        this.valoracion = valoracion;
    }

    public Serie(){
    }

    //Getters y Setters
    public String getIdNombreUsuario() {
        return idNombreUsuario;
    }

    public void setIdNombreUsuario(String idNombreUsuario) {
        this.idNombreUsuario = idNombreUsuario;
    }

    public String getNumero_series() {
        return numero_series;
    }

    public void setNumero_series(String numero_series) {
        this.numero_series = numero_series;
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
