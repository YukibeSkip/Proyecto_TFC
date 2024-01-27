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

public class Activity_ModificarPeli extends AppCompatActivity {

    private EditText editText_NumeroModP, editText_TituloModP, editText_DirectorModP;
    private Spinner spinner_estadoModP, spinner_ValoracionMopP;
    private Button button_ModifP, button_BorrarP, button_Atras6;

    String[] estado = {"Vista", "Pendiente"};
    String[] valoracion = {"---", "Malo", "Normal", "Bueno", "Favorito"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modificar_peli);

        editText_NumeroModP = (EditText) findViewById(R.id.editText_NumeroModP);
        editText_TituloModP = (EditText) findViewById(R.id.editText_TituloModP);
        editText_DirectorModP = (EditText) findViewById(R.id.editText_DirectorModP);

        spinner_estadoModP = (Spinner) findViewById(R.id.spinner_estadoModP);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, estado);
        spinner_estadoModP.setAdapter(adapter);

        spinner_ValoracionMopP = (Spinner) findViewById(R.id.spinner_ValoracionMopP);
        ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, valoracion);
        spinner_ValoracionMopP.setAdapter(adapter1);

        button_Atras6 = (Button) findViewById(R.id.button_Atras6);
        button_BorrarP = (Button) findViewById(R.id.button_BorrarP);
        button_ModifP = (Button) findViewById(R.id.button_ModifP);

        Bundle datos = this.getIntent().getExtras();
        String numero_peli = datos.getString("Numero");
        String director = datos.getString("Director");
        String nombre = datos.getString("Nombre");

        editText_NumeroModP.setText(numero_peli);
        editText_DirectorModP.setText(director);
        editText_TituloModP.setText(nombre);

        //Metodo para volver atras
        Atras();

        //Metodo para modificar los datos en la base de datos
        Modificar();
    }

    public void Atras() {
        button_Atras6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(v.getContext(), Activity_OpcionesUsuario.class);
                startActivity(i);
            }
        });
    }

    public void Modificar() {
        AdminSQLiteOpenHelper adminSQLiteOpenHelper = new AdminSQLiteOpenHelper(this, "datosApp", null, 1);
        SQLiteDatabase db = adminSQLiteOpenHelper.getWritableDatabase();
        button_ModifP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String numero_peli = editText_NumeroModP.getText().toString();
                String director = editText_DirectorModP.getText().toString();
                String nombre = editText_TituloModP.getText().toString();
                String estado = spinner_estadoModP.getSelectedItem().toString();
                String valoracion = spinner_ValoracionMopP.getSelectedItem().toString();

                if (director.length() == 0 || nombre.length() == 0 || numero_peli.length() == 0) {
                    Toast.makeText(Activity_ModificarPeli.this, "Hay datos que no ha sido introducidos", Toast.LENGTH_SHORT).show();
                } else {
                    ContentValues valores = new ContentValues();
                    valores.put("numero_peli", numero_peli);
                    valores.put("director", director);
                    valores.put("nombre", nombre);
                    valores.put("estado", estado);
                    valores.put("valoracion", valoracion);

                    String seleccion = "numero_peli" + "=?";
                    String[] condicion = {numero_peli};

                    int filasActualizadas = db.update("Pelis", valores, seleccion, condicion);
                    if (filasActualizadas == 0) {
                        Toast.makeText(Activity_ModificarPeli.this, "No se han podido actualizar los datos", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(Activity_ModificarPeli.this, "Los datos han sido actulizados correctamente", Toast.LENGTH_SHORT).show();
                    }
                }
                db.close();
                Intent i = new Intent(v.getContext(), Activity_VerTablaPelis.class);
                startActivity(i);
            }
        });
    }

    public void Borrar(View v){
        AdminSQLiteOpenHelper adminSQLiteOpenHelper = new AdminSQLiteOpenHelper(this, "datosApp", null, 1);
        SQLiteDatabase db = adminSQLiteOpenHelper.getWritableDatabase();

        Intent intent = getIntent();
        String numero_peli = intent.getStringExtra("Numero");
        String seleccion = "numero_peli" + "=?";
        String[] condicion = {numero_peli};
        int filasEliminadas = db.delete("Pelis",seleccion, condicion);
        if(filasEliminadas !=0){
            Toast.makeText(this, "Peli borrada", Toast.LENGTH_SHORT).show();
        }
        db.close();
        Intent i = new Intent(v.getContext(), Activity_VerTablaPelis.class);
        startActivity(i);
    }

}