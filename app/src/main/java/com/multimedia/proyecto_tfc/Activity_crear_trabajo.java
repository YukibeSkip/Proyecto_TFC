package com.multimedia.proyecto_tfc;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.ConditionVariable;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class Activity_crear_trabajo extends AppCompatActivity {

    private EditText editText_NumeroTrabajo, editText_TituloTrabajo, editText_FechaTrabajo;
    private Spinner spinner_estadoTrabajo;
    String[] estado = {"Sin empezar", "Empezado","Acabado"};
    private Button button_AltaTrabajo, button_Volver6;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crear_trabajo);

        editText_TituloTrabajo = (EditText) findViewById(R.id.editText_TituloTrabajo);
        editText_NumeroTrabajo  = (EditText) findViewById(R.id.editText_NumeroTrabajo);
        editText_FechaTrabajo = (EditText) findViewById(R.id.editText_FechaTrabajo);

        spinner_estadoTrabajo = (Spinner) findViewById(R.id.spinner_estadoTrabajo);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, estado);
        spinner_estadoTrabajo.setAdapter(adapter);

        button_AltaTrabajo = (Button) findViewById(R.id.button_AltaTrabajo);
        button_Volver6 = (Button) findViewById(R.id.button_Volver6);

        //Volver al menu
        Atras();

        //Dar de alta datos
        AltaTrabajo();
    }

    public void Atras(){
        button_Volver6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(v.getContext(), Activity_OpcionesUsuario.class);
                startActivity(i);
            }
        });
    }

    public void AltaTrabajo(){
        AdminSQLiteOpenHelper adminSQLiteOpenHelper = new AdminSQLiteOpenHelper(this, "datosApp",null,1);
        SQLiteDatabase db = adminSQLiteOpenHelper.getWritableDatabase();

        button_AltaTrabajo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String numero_trab = editText_NumeroTrabajo.getText().toString();
                String nombre = editText_TituloTrabajo.getText().toString();
                String fecha = editText_FechaTrabajo.getText().toString();
                String estado = spinner_estadoTrabajo.getSelectedItem().toString();

                if(numero_trab.length()==0 || nombre.length()==0 || fecha.length() ==0){
                    Toast.makeText(Activity_crear_trabajo.this, "Hay datos que no han sido introducidos", Toast.LENGTH_SHORT).show();
                }else{
                    ContentValues valores = new ContentValues();
                    valores.put("numero_trab",numero_trab);
                    valores.put("nombre",nombre);
                    valores.put("estado", estado);
                    valores.put("fecha", fecha);
                    db.insert("Trabajo",null,valores);
                    db.close();

                    Toast.makeText(Activity_crear_trabajo.this, "Se han dado de alta los datos", Toast.LENGTH_SHORT).show();

                    Intent i = new Intent(v.getContext(), Activity_Ver_Trabajos.class);
                    startActivity(i);
                }

            }
        });
    }

}