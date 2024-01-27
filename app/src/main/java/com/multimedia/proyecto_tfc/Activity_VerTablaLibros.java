package com.multimedia.proyecto_tfc;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;

public class Activity_VerTablaLibros extends AppCompatActivity {

    private Button button_AnhadirLibros, button_AtrasLibro;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ver_tabla_libros);

        button_AnhadirLibros = (Button) findViewById(R.id.button_AnhadirLibros);
        button_AtrasLibro = (Button) findViewById(R.id.button_AtrasLibro);

        RecyclerView recyclerView_libros = findViewById(R.id.recyclerView_series);

        AdminSQLiteOpenHelper adminSQLiteOpenHelper = new AdminSQLiteOpenHelper(this, "datosApp",null,1);
        SQLiteDatabase db = adminSQLiteOpenHelper.getReadableDatabase();

        String[] columnas = {"numero_libro","autor", "titulo", "estado", "valoracion"};
        Cursor cursor = db.query("Libros", columnas, null,null,null,null,null);
        ArrayList<Libro> Libro = new ArrayList<>();
        while (cursor.moveToNext()){
            String numero_libro = cursor.getString(cursor.getColumnIndexOrThrow("numero_libro"));
            String autor = cursor.getString(cursor.getColumnIndexOrThrow("autor"));
            String titulo = cursor.getString(cursor.getColumnIndexOrThrow("titulo"));
            String estado = cursor.getString(cursor.getColumnIndexOrThrow("estado"));
            String valoracion = cursor.getString(cursor.getColumnIndexOrThrow("valoracion"));

            Libro libro = new Libro(numero_libro, autor, titulo, estado, valoracion);
            Libro.add(libro);
        }
        cursor.close();
        db.close();

        AdpatadorLibros adpatadorLibros = new AdpatadorLibros(Libro);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView_libros.setLayoutManager(layoutManager);
        recyclerView_libros.setItemAnimator(new DefaultItemAnimator());
        recyclerView_libros.setAdapter(adpatadorLibros);
        
        adpatadorLibros.setOnclickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Activity_VerTablaLibros.this, "Seleccion:"+Libro.get(recyclerView_libros.getChildAdapterPosition(v)).getTitulo() , Toast.LENGTH_SHORT).show();

                String numero_libro = Libro.get(recyclerView_libros.getChildAdapterPosition(v)).getNumero_libro();
                String autor = Libro.get(recyclerView_libros.getChildAdapterPosition(v)).getAutor();
                String titulo = Libro.get(recyclerView_libros.getChildAdapterPosition(v)).getTitulo();
                String estado = Libro.get(recyclerView_libros.getChildAdapterPosition(v)).getValoracion();
                String valoracion = Libro.get(recyclerView_libros.getChildAdapterPosition(v)).getEstado();

                Intent intent = new Intent(v.getContext(), Activity_ModificarLibro.class);
                    intent.putExtra("Numero", numero_libro);
                    intent.putExtra("Autor",autor);
                    intent.putExtra("Titulo", titulo);
                    intent.putExtra("Estado", estado);
                    intent.putExtra("Valoracion", valoracion);
                    startActivity(intent);
            }
        });

        //Metod para volver a la pantalla anterior
        Atras();
        //Metodo dar de alta Libro
        Alta();
    }

    public void Atras(){
        button_AtrasLibro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(v.getContext(), Activity_OpcionesUsuario.class);
                startActivity(i);
            }
        });
    }

    public void Alta(){
        button_AnhadirLibros.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(v.getContext(), Activity_CrearLibro.class);
                startActivity(i);
            }
        });
    }
}