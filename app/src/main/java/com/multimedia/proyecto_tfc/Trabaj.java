package com.multimedia.proyecto_tfc;

public class Trabaj {

    //Atributos
    private String idNombreUsuario;
    private String numero_trab;
    private String nombre;
    private String estado;
    private String fecha;

    public Trabaj(String idNombreUsuario, String numero_trab, String nombre, String estado, String fecha) {
        this.idNombreUsuario = idNombreUsuario;
        this.numero_trab = numero_trab;
        this.nombre = nombre;
        this.estado = estado;
        this.fecha = fecha;
    }

    public Trabaj(String numero_trab, String nombre, String estado, String fecha) {
        this.numero_trab = numero_trab;
        this.nombre = nombre;
        this.estado = estado;
        this.fecha = fecha;
    }

    public Trabaj() {
    }

    //Getters y Setters
    public String getIdNombreUsuario() {
        return idNombreUsuario;
    }

    public void setIdNombreUsuario(String idNombreUsuario) {
        this.idNombreUsuario = idNombreUsuario;
    }

    public String getNumero_trabajo() {
        return numero_trab;
    }

    public void setNumero_trabajo(String numero_trab) {
        this.numero_trab = numero_trab;
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

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }
}
