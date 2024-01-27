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

public class Activity_Mod_Trabajo extends AppCompatActivity {

    private EditText editText_NumTMod, editText_TituloTMod, editText_FechaTMod;
    private Button button_ModTrab, button_BorrarT, button_Volver7;
    private Spinner spinner_estadoTMod;
    String[] estado = {"Sin empezar", "Empezado","Acabado"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modificar_trabajo);

        editText_NumTMod = (EditText) findViewById(R.id.editText_NumTMod);
        editText_TituloTMod = (EditText) findViewById(R.id.editText_TituloTMod);
        editText_FechaTMod = (EditText) findViewById(R.id.editText_FechaTMod);

        button_ModTrab = (Button) findViewById(R.id.button_ModTrab);
        button_BorrarT = (Button) findViewById(R.id.button_BorrarT);
        button_Volver7 = (Button) findViewById(R.id.button_Volver7);

        spinner_estadoTMod = (Spinner) findViewById(R.id.spinner_estadoTMod);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, estado);
        spinner_estadoTMod.setAdapter(adapter);

        Bundle datos = this.getIntent().getExtras();
        String numero_trabajo = datos.getString("Numero");
        String nombre = datos.getString("Nombre");
        String fecha = datos.getString("Fecha");

        editText_NumTMod.setText(numero_trabajo);
        editText_TituloTMod.setText(nombre);
        editText_FechaTMod.setText(fecha);

        //Volver atras
        Atras();

        //Modificar datos
        Modificar();

    }

    public void Atras(){
        button_Volver7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(v.getContext(), Activity_OpcionesUsuario.class);
                startActivity(i);
            }
        });
    }

    public void Modificar(){
        AdminSQLiteOpenHelper adminSQLiteOpenHelper = new AdminSQLiteOpenHelper(this, "datosApp", null,1);
        SQLiteDatabase db = adminSQLiteOpenHelper.getWritableDatabase();

        button_ModTrab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String numero_trab = editText_NumTMod.getText().toString();
                String nombre = editText_TituloTMod.getText().toString();
                String fecha = editText_FechaTMod.getText().toString();
                String estado = spinner_estadoTMod.getSelectedItem().toString();

                if(numero_trab.length()==0 || nombre.length()==0 || fecha.length() ==0){
                    Toast.makeText(Activity_Mod_Trabajo.this, "Hay datos que no han sido introducidos", Toast.LENGTH_SHORT).show();
                }else{
                    ContentValues valores = new ContentValues();
                    valores.put("numero_trab",numero_trab);
                    valores.put("nombre",nombre);
                    valores.put("estado", estado);
                    valores.put("fecha", fecha);

                    String seleccion = "numero_trab"+" =? ";
                    String[] condicion = {numero_trab};

                    int filasActualizadas = db.update("Trabajo", valores,seleccion,condicion);

                    if(filasActualizadas == 0){
                        Toast.makeText(Activity_Mod_Trabajo.this, "No se han podido actulizar los datos", Toast.LENGTH_SHORT).show();
                    }else{
                        Toast.makeText(Activity_Mod_Trabajo.this, "Los datos han sido actualizados correctamente", Toast.LENGTH_SHORT).show();
                    }
                }
                db.close();
                Intent i = new Intent(v.getContext(), Activity_Ver_Trabajos.class);
                startActivity(i);

            }
        });
    }

    public void Borrar(View v){
        AdminSQLiteOpenHelper adminSQLiteOpenHelper = new AdminSQLiteOpenHelper(this, "datosApp", null, 1);
        SQLiteDatabase db = adminSQLiteOpenHelper.getWritableDatabase();

        Intent intent = getIntent();
        String numero_trab = intent.getStringExtra("Numero");
        String seleccion = "numero_trab"+" =? ";
        String[] condicion = {numero_trab};

        int filasEliminadas = db.delete("Trabajo",seleccion,condicion);
        if(filasEliminadas !=0){
            Toast.makeText(this, "Trabajo Borrado", Toast.LENGTH_SHORT).show();
        }
        db.close();
        Intent i = new Intent(v.getContext(), Activity_Ver_Trabajos.class);
        startActivity(i);
    }

}