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

public class Activity_CrearLibro extends AppCompatActivity {

    private EditText editText_Numero, editText_Titulo, editText_Autor;
    private Spinner spinner_estado, spinner_Valoracion;
    private Button button_AltaL, button_Atras4;

    //Declaro los datos que llevara los Spinners
    String[] estado = {"Sin empezar", "Leyendo", "Finalizado", "Releyendo"};
    String[] valoracion = {"---","Malo", "Normal", "Bueno", "Favorito"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crear_libro);

        editText_Numero = (EditText) findViewById(R.id.editText_NumeroModP);
        editText_Titulo = (EditText) findViewById(R.id.editText_TituloModP);
        editText_Autor = (EditText) findViewById(R.id.editText_DirectorModP);

        button_Atras4 = (Button) findViewById(R.id.button_Atras6);
        button_AltaL = (Button) findViewById(R.id.button_ModifP);

        spinner_estado = (Spinner) findViewById(R.id.spinner_estadoModP);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, estado);
        spinner_estado.setAdapter(adapter);
        spinner_Valoracion = (Spinner) findViewById(R.id.spinner_ValoracionMopP);
        ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, valoracion);
        spinner_Valoracion.setAdapter(adapter1);

        //Metodo para volver a la pnatalla anterior
        Atras();
        //Metodo para dar d alta los libros
        AltaLibro();
    }

    public void Atras(){
        button_Atras4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(v.getContext(), Activity_OpcionesUsuario.class);
                startActivity(i);
            }
        });
    }

    public void AltaLibro(){
        AdminSQLiteOpenHelper adminSQLiteOpenHelper = new AdminSQLiteOpenHelper(this, "datosApp",null,1);
        SQLiteDatabase db = adminSQLiteOpenHelper.getWritableDatabase();
        button_AltaL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String numero_libro = editText_Numero.getText().toString();
                String autor = editText_Autor.getText().toString();
                String titulo = editText_Titulo.getText().toString();
                String estado = spinner_estado.getSelectedItem().toString();
                String valoracion = spinner_Valoracion.getSelectedItem().toString();

                if(autor.length() == 0 || titulo.length() == 0 || numero_libro.length() == 0){
                    Toast.makeText(Activity_CrearLibro.this, "Hay datos que no ha sido introducidos", Toast.LENGTH_SHORT).show();
                }else{
                    ContentValues valores = new ContentValues();
                    valores.put("numero_libro", numero_libro);
                    valores.put("autor", autor);
                    valores.put("titulo", titulo);
                    valores.put("estado",estado);
                    valores.put("valoracion", valoracion);
                    db.insert("Libros",null,valores);
                    db.close();
                    Toast.makeText(Activity_CrearLibro.this, "Se han dado de alta los datos", Toast.LENGTH_SHORT).show();

                    Intent i = new Intent(v.getContext(), Activity_VerTablaLibros.class);
                    startActivity(i);
                }
            }
        });
    }

}