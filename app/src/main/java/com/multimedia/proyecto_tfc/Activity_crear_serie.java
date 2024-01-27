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

public class Activity_crear_serie extends AppCompatActivity {

    private EditText editText_NumeroSerie, editText_TituloSerie, editText_DirectorSerie;
    private Button button_AltaSerie, button_Atras7;
    private Spinner spinner_estadoSerie, spinner_ValorSerie;

    //Declaro los datos que llevara los Spinners
    String[] estado = {"Vista", "Pendiente", "Viendo"};
    String[] valoracion = {"---", "Malo", "Normal", "Bueno", "Favorito"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crear_serie);

        editText_NumeroSerie = (EditText) findViewById(R.id.editText_NumeroSerie);
        editText_TituloSerie = (EditText) findViewById(R.id.editText_TituloSerie);
        editText_DirectorSerie = (EditText) findViewById(R.id.editText_DirectorSerie);

        spinner_estadoSerie = (Spinner) findViewById(R.id.spinner_estadoSerie);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, estado);
        spinner_estadoSerie.setAdapter(adapter);
        spinner_ValorSerie = (Spinner) findViewById(R.id.spinner_ValorSerie);
        ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, valoracion);
        spinner_ValorSerie.setAdapter(adapter1);

        button_AltaSerie = (Button) findViewById(R.id.button_AltaSerie);
        button_Atras7 = (Button) findViewById(R.id.button_Atras7);

        //Metodo para volver a la pantalla anterior
        Atras();

        //Metodo para dar de alta los datos
        AltaSerie();

    }

    public void Atras() {
        button_Atras7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(v.getContext(), Activity_OpcionesUsuario.class);
                startActivity(i);
            }
        });
    }

    public void AltaSerie(){
        AdminSQLiteOpenHelper adminSQLiteOpenHelper = new AdminSQLiteOpenHelper(this, "datosApp",null,1);
        SQLiteDatabase db = adminSQLiteOpenHelper.getWritableDatabase();
        button_AltaSerie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String numero_serie = editText_NumeroSerie.getText().toString();
                String director = editText_DirectorSerie.getText().toString();
                String nombre = editText_TituloSerie.getText().toString();
                String estado = spinner_estadoSerie.getSelectedItem().toString();
                String valoracion = spinner_ValorSerie.getSelectedItem().toString();

                if(director.length() == 0 || nombre.length() == 0 || numero_serie.length() == 0){
                    Toast.makeText(Activity_crear_serie.this, "Hay datos que no ha sido introducidos", Toast.LENGTH_SHORT).show();
                }else{
                    ContentValues valores = new ContentValues();
                    valores.put("numero_series", numero_serie);
                    valores.put("director", director);
                    valores.put("nombre", nombre);
                    valores.put("estado", estado);
                    valores.put("valoracion", valoracion);
                    db.insert("Series", null,valores);
                    db.close();
                    Toast.makeText(Activity_crear_serie.this, "Se han dado de alta los datos", Toast.LENGTH_SHORT).show();

                    Intent i = new Intent(v.getContext(), Activity_VerTablaSeries.class);
                    startActivity(i);

                }

            }
        });
    }

}