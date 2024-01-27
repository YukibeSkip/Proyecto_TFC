package com.multimedia.proyecto_tfc;

public class Libro {

    //Atributos
    private String idNombreUsuario;
    private String numero_libro;
    private String autor;
    private String titulo;
    private String estado;
    private String valoracion;

    //Contructores
    public Libro(String idNombreUsuario, String numero_libro, String autor, String titulo, String estado, String valoracion){
        this.idNombreUsuario = idNombreUsuario;
        this.numero_libro = numero_libro;
        this.autor = autor;
        this.titulo = titulo;
        this.estado = estado;
        this.valoracion = valoracion;
    }

    public Libro(String numero_libro, String autor, String titulo, String estado, String valoracion){
        this.numero_libro = numero_libro;
        this.autor = autor;
        this.titulo = titulo;
        this.estado = estado;
        this.valoracion = valoracion;
    }

    public Libro(){

    }


    //Getters y  Setters


    public String getIdNombreUsuario() {
        return idNombreUsuario;
    }
    public void setIdNombreUsuario(String idNombreUsuario) {
        this.idNombreUsuario = idNombreUsuario;
    }

    public String getNumero_libro() {
        return numero_libro;
    }
    public void setNumero_libro(String numero_libro) {
        this.numero_libro = numero_libro;
    }

    public String getAutor() {
        return autor;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getEstado() {
        return estado;
    }

    public String getValoracion() {
        return valoracion;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public void setValoracion(String valoracion) {
        this.valoracion = valoracion;
    }
}
