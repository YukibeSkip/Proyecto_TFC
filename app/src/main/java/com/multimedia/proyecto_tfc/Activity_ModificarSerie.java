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

public class Activity_ModificarSerie extends AppCompatActivity {

    private EditText editText_NumeroModS, editText_TituloModS, editText_DirectorModS;

    private Spinner spinner_estadoModS, spinner_ValoracionModS;

    //Declaro los datos que llevara los Spinners
    String[] estado = {"Vista", "Pendiente", "Viendo"};
    String[] valoracion = {"---", "Malo", "Normal", "Bueno", "Favorito"};

    private Button button_Modificar, button_BorrarSerie, button_Volver3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modificar_serie);

        editText_NumeroModS = (EditText) findViewById(R.id.editText_NumeroModS);
        editText_TituloModS = (EditText) findViewById(R.id.editText_TituloModS);
        editText_DirectorModS = (EditText) findViewById(R.id.editText_DirectorModS);

        spinner_estadoModS = (Spinner) findViewById(R.id.spinner_estadoModS);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, estado);
        spinner_estadoModS.setAdapter(adapter);

        spinner_ValoracionModS = (Spinner) findViewById(R.id.spinner_ValoracionModS);
        ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, valoracion);
        spinner_ValoracionModS.setAdapter(adapter1);

        Bundle datos = this.getIntent().getExtras();
        String numero_serie = datos.getString("Numero");
        String director = datos.getString("Director");
        String nombre = datos.getString("Nombre");

        editText_NumeroModS.setText(numero_serie);
        editText_DirectorModS.setText(director);
        editText_TituloModS.setText(nombre);

        button_Modificar = (Button) findViewById(R.id.button_Modificar);
        button_BorrarSerie = (Button) findViewById(R.id.button_BorrarSerie);
        button_Volver3 = (Button) findViewById(R.id.button_Volver3);

        //Volver a la pantalla anterior
        Atras();

        Modificar();

    }

    public void Atras() {
        button_Volver3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(v.getContext(), Activity_OpcionesUsuario.class);
                startActivity(i);
            }
        });
    }

    public void Modificar(){
        AdminSQLiteOpenHelper adminSQLiteOpenHelper = new AdminSQLiteOpenHelper(this, "datosApp", null, 1);
        SQLiteDatabase db = adminSQLiteOpenHelper.getWritableDatabase();
        button_Modificar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String numero_serie = editText_NumeroModS.getText().toString();
                String director = editText_DirectorModS.getText().toString();
                String nombre = editText_TituloModS.getText().toString();
                String estado = spinner_estadoModS.getSelectedItem().toString();
                String valoracion = spinner_ValoracionModS.getSelectedItem().toString();

                if(director.length() == 0 || nombre.length() == 0 || numero_serie.length() == 0){
                    Toast.makeText(Activity_ModificarSerie.this, "Hay datos que no ha sido introducidos", Toast.LENGTH_SHORT).show();
                }else{
                    ContentValues valores = new ContentValues();
                    valores.put("numero_series", numero_serie);
                    valores.put("director", director);
                    valores.put("nombre", nombre);
                    valores.put("estado", estado);
                    valores.put("valoracion", valoracion);

                    String seleccion = "numero_series"+"=?";
                    String[] condicion = {numero_serie};

                    int filasActulizadas = db.update("Series", valores,seleccion,condicion);
                    if(filasActulizadas == 0){
                        Toast.makeText(Activity_ModificarSerie.this, "No se han podido actulizar los datos", Toast.LENGTH_SHORT).show();
                    }else{
                        Toast.makeText(Activity_ModificarSerie.this, "Los datos han sido actulizados correctamente", Toast.LENGTH_SHORT).show();
                    }
                }
                db.close();
                Intent i = new Intent(v.getContext(), Activity_VerTablaSeries.class);
                startActivity(i);
            }
        });
    }

    public void Borrar(View v){
        AdminSQLiteOpenHelper adminSQLiteOpenHelper = new AdminSQLiteOpenHelper(this, "datosApp", null, 1);
        SQLiteDatabase db = adminSQLiteOpenHelper.getWritableDatabase();

        Intent intent = getIntent();
        String numero_serie = intent.getStringExtra("Numero");
        String seleccion = "numero_series"+"=?";
        String[] condicion = {numero_serie};
        int filasEliminadas = db.delete("Series",seleccion,condicion);
        if(filasEliminadas !=0){
            Toast.makeText(this, "Serie borrada", Toast.LENGTH_SHORT).show();
        }
        db.close();
        Intent i = new Intent(v.getContext(), Activity_VerTablaSeries.class);
        startActivity(i);
    }

}