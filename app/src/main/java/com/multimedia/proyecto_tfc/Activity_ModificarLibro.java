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

public class Activity_ModificarLibro extends AppCompatActivity {

    private EditText editText_NumeroM, editText_TituloM, editText_AutorM;
    private Button button_Modificar, button_Borrar, button_Atras4;
    private Spinner spinner_estadoM,spinner_ValoracionM;

    String[] estado = {"Sin empezar", "Leyendo", "Finalizado", "Releyendo"};
    String[] valoracion = {"---","Malo", "Normal", "Bueno", "Favorito"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modificar_libro);

        editText_NumeroM = (EditText) findViewById(R.id.editText_NumeroModP);
        editText_TituloM = (EditText) findViewById(R.id.editText_TituloModP);
        editText_AutorM = (EditText) findViewById(R.id.editText_DirectorModP);

        button_Modificar = (Button) findViewById(R.id.button_ModifP);
        button_Borrar = (Button) findViewById(R.id.button_Borrar);
        button_Atras4 = (Button) findViewById(R.id.button_Atras6);

        spinner_estadoM = (Spinner) findViewById(R.id.spinner_estadoModP);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, estado);
        spinner_estadoM.setAdapter(adapter);
        spinner_ValoracionM = (Spinner) findViewById(R.id.spinner_ValoracionMopP);
        ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, valoracion);
        spinner_ValoracionM.setAdapter(adapter1);

        Bundle datos = this.getIntent().getExtras();
        String numero_libro = datos.getString("Numero");
        String autor = datos.getString("Autor");
        String titulo = datos.getString("Titulo");

        editText_NumeroM.setText(numero_libro);
        editText_AutorM.setText(autor);
        editText_TituloM.setText(titulo);

        //Metod para volver a la pnatlla anterior
        Atras();

        //Metodo para modificar los datos
        Modificar();


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

    public void Modificar(){
       AdminSQLiteOpenHelper adminSQLiteOpenHelper = new AdminSQLiteOpenHelper(this, "datosApp", null,1);
        SQLiteDatabase db = adminSQLiteOpenHelper.getWritableDatabase();
        button_Modificar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String numero_libro = editText_NumeroM.getText().toString();
                String autor = editText_AutorM.getText().toString();
                String titulo = editText_TituloM.getText().toString();
                String estado = spinner_estadoM.getSelectedItem().toString();
                String valoracion = spinner_ValoracionM.getSelectedItem().toString();

                if(autor.length() == 0 || titulo.length() == 0 || numero_libro.length() == 0){
                    Toast.makeText(Activity_ModificarLibro.this, "Hay datos que no ha sido introducidos", Toast.LENGTH_SHORT).show();
                }else{
                    ContentValues valores = new ContentValues();
                    valores.put("numero_libro", numero_libro);
                    valores.put("autor", autor);
                    valores.put("titulo", titulo);
                    valores.put("estado",estado);
                    valores.put("valoracion", valoracion);

                    String seleccion = "numero_libro"+" =? ";
                    String[] condicion = {numero_libro};

                    int filasactulizadas = db.update("Libros",valores, seleccion, condicion);
                    if(filasactulizadas==0){
                        Toast.makeText(Activity_ModificarLibro.this, "No se han podido actulizar los datos", Toast.LENGTH_SHORT).show();
                    }else{
                        Toast.makeText(Activity_ModificarLibro.this, "Los datos han sido actualizados correctamente", Toast.LENGTH_SHORT).show();
                    }

                }
                db.close();
                Intent i = new Intent(v.getContext(), Activity_VerTablaLibros.class);
                startActivity(i);

            }
        });
    }

    public void Borrar(View v) {
        AdminSQLiteOpenHelper adminSQLiteOpenHelper = new AdminSQLiteOpenHelper(this, "datosApp", null, 1);
        SQLiteDatabase db = adminSQLiteOpenHelper.getWritableDatabase();

            Intent intent = getIntent();
            String numero_libro = intent.getStringExtra("Numero");
            String seleccion = "numero_libro" + " =? ";
            String[] condicion = {numero_libro};
            int filasEliminadas = db.delete("Libros", seleccion, condicion);
            if (filasEliminadas != 0) {
                Toast.makeText(Activity_ModificarLibro.this, "Libro borrado", Toast.LENGTH_SHORT).show();
            }
        db.close();
            Intent i = new Intent(v.getContext(), Activity_VerTablaLibros.class);
            startActivity(i);
    }
}