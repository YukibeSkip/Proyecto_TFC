package com.multimedia.proyecto_tfc;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView textView_CreacionCuenta;
    private Button button_InicioSesion;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView_CreacionCuenta = (TextView) findViewById(R.id.textView_CreacionCuenta);
        button_InicioSesion = (Button) findViewById(R.id.button_InicioSesion);

        Ir_InicioSesion();
        Alta();
    }

    public void Ir_InicioSesion(){
        button_InicioSesion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(v.getContext(), Activity_InicioSesion.class);
                startActivity(i);
            }
        });
    }

    public void Alta(){
        textView_CreacionCuenta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(v.getContext(), Activity_CrearCuenta.class);
                startActivity(i);
            }
        });
    }

}