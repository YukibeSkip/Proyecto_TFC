package com.multimedia.proyecto_tfc;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Activity_OpcionesUsuario extends AppCompatActivity {

    private Button button_VerTrabajos, button_VerLibros,button_VerPeliculas, button_VerSeries, button_Atras;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_opciones_usuario);

        button_VerTrabajos = (Button) findViewById(R.id.button_VerTrabajos);
        button_VerLibros = (Button) findViewById(R.id.button_VerLibros);
        button_VerPeliculas = (Button) findViewById(R.id.button_VerPeliculas);
        button_VerSeries = (Button) findViewById(R.id.button_VerSeries);
        button_Atras = (Button) findViewById(R.id.button_Atras);

        //Metodo para salir de la aplicación
        Salir();

        //Metodo para ir a la pantalla de la lista de trabajo
        VerTrabajo();

        //Metodo para ir a la pantalla de la lista de libros
        VerLibros();

        //Metodo para ir a la pantalla de la lista de peliculas
        VerPelis();

        //Metodo para ir a la pantalla de la lista de Series
        VerSeries();

    }

    public void Salir(){
        button_Atras.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(v.getContext(), MainActivity.class);
                startActivity(i);
            }
        });
    }

    public void VerTrabajo(){
        button_VerTrabajos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(v.getContext(), Activity_Ver_Trabajos.class);
                startActivity(i);
            }
        });
    }

    public void VerLibros(){
        button_VerLibros.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(v.getContext(), Activity_VerTablaLibros.class);
                startActivity(i);
            }
        });
    }

    public void VerPelis(){
        button_VerPeliculas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(v.getContext(), Activity_VerTablaPelis.class);
                startActivity(i);
            }
        });
    }

    public void VerSeries(){
        button_VerSeries.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(v.getContext(), Activity_VerTablaSeries.class);
                startActivity(i);
            }
        });
    }

}