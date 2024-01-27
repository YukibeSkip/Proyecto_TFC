package com.multimedia.proyecto_tfc;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class Activity_crear_peli extends AppCompatActivity {

    private EditText editText_NumeroPeli, editText_TituloPeli, editText_directorPeli;
    private Spinner spinner_Peli, spinner_ValoracionPeli;
    private Button button_AltaPeliculas, button_Atras9;

    //Declaro los datos que llevara los Spinners
    String[] estado = {"Vista", "Pendiente"};
    String[] valoracion = {"---", "Malo", "Normal", "Bueno", "Favorito"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crear_peli);

        editText_NumeroPeli = (EditText) findViewById(R.id.editText_NumeroPeli);
        editText_TituloPeli = (EditText) findViewById(R.id.editText_TituloPeli);
        editText_directorPeli = (EditText) findViewById(R.id.editText_directorPeli);
        spinner_Peli = (Spinner) findViewById(R.id.spinner_Peli);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, estado);
        spinner_Peli.setAdapter(adapter);
        spinner_ValoracionPeli = (Spinner) findViewById(R.id.spinner_ValoracionPeli);
        ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, valoracion);
        spinner_ValoracionPeli.setAdapter(adapter1);

        button_AltaPeliculas = (Button) findViewById(R.id.button_AltaPeliculas);
        button_Atras9 = (Button) findViewById(R.id.button_Atras9);

        //Metodo para volver a la pnatalla anterior
        Vuelta();
        //Metodo para dar d alta los libros
        AltaPeli();


    }

    public void Vuelta() {
        button_Atras9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(v.getContext(), Activity_OpcionesUsuario.class);
                startActivity(i);
            }
        });
    }

    public void AltaPeli(){

        AdminSQLiteOpenHelper adminSQLiteOpenHelper = new AdminSQLiteOpenHelper(this, "datosApp",null,1);
        SQLiteDatabase db = adminSQLiteOpenHelper.getWritableDatabase();

        button_AltaPeliculas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String numero_peli = editText_NumeroPeli.getText().toString();
                String director = editText_TituloPeli.getText().toString();
                String nombre = editText_directorPeli.getText().toString();
                String estado = spinner_Peli.getSelectedItem().toString();
                String valoracion = spinner_ValoracionPeli.getSelectedItem().toString();

                if(director.length() == 0 || nombre.length() == 0 || numero_peli.length() == 0){
                    Toast.makeText(Activity_crear_peli.this, "Hay datos que no ha sido introducidos", Toast.LENGTH_SHORT).show();
                }else{
                    ContentValues val = new ContentValues();
                    val.put("numero_peli", numero_peli);
                    val.put("director", director);
                    val.put("nombre", nombre);
                    val.put("estado",estado);
                    val.put("valoracion", valoracion);

                    db.insert("Pelis",null,val);
                    db.close();

                    Toast.makeText(Activity_crear_peli.this, "Se han dado de alta los datos", Toast.LENGTH_SHORT).show();

                    Intent i = new Intent(v.getContext(), Activity_VerTablaPelis.class);
                    startActivity(i);

                }
            }
        });
    }

}