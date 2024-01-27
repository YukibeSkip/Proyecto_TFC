package com.multimedia.proyecto_tfc;

public class Usuario {

    //Atributos
    private String idNombreUsuario;
    private String contrasenha;

    //Contructores

    public Usuario(){}

    public Usuario(String idNombreUsuario, String contrasenha){
        this.idNombreUsuario = idNombreUsuario;
        this.contrasenha = contrasenha;
    }

    //Getters y Setters
    public String getIdNombreUsuario(){return idNombreUsuario;};

    public void setIdNombreUsuario(String idNombreUsuario){
        this.idNombreUsuario = idNombreUsuario;
    }

    public String getContrasenha(){return contrasenha;}

    public void setContrasenha(String contrasenha){this.contrasenha = contrasenha;}

}
