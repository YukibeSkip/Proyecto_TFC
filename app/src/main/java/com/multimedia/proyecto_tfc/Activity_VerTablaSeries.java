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

public class Activity_VerTablaSeries extends AppCompatActivity {

    private Button button_AnhadirSerie, button_Atras8;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ver_tabla_series);

        button_AnhadirSerie = (Button) findViewById(R.id.button_AnhadirSerie);
        button_Atras8 = (Button) findViewById(R.id.button_Atras8);

        RecyclerView recyclerView_series = findViewById(R.id.recyclerView_series);

        AdminSQLiteOpenHelper adminSQLiteOpenHelper = new AdminSQLiteOpenHelper(this, "datosApp",null, 1);
        SQLiteDatabase db = adminSQLiteOpenHelper.getReadableDatabase();

        String[] columnas = {"numero_series","director","nombre","estado","valoracion"};
        Cursor cursor = db.query("Series", columnas, null,null,null,null,null);
        ArrayList<Serie> Serie = new ArrayList<>();
        while (cursor.moveToNext()){
            String numero_serie = cursor.getString(cursor.getColumnIndexOrThrow("numero_series"));
            String director = cursor.getString(cursor.getColumnIndexOrThrow("director"));
            String nombre = cursor.getString(cursor.getColumnIndexOrThrow("nombre"));
            String estado = cursor.getString(cursor.getColumnIndexOrThrow("estado"));
            String valoracion = cursor.getString(cursor.getColumnIndexOrThrow("valoracion"));

            Serie serie = new Serie(numero_serie, director, nombre, estado, valoracion);
            Serie.add(serie);
        }

        cursor.close();
        db.close();

        AdaptadorSeries adaptadorSeries = new AdaptadorSeries(Serie);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView_series.setLayoutManager(layoutManager);
        recyclerView_series.setItemAnimator(new DefaultItemAnimator());
        recyclerView_series.setAdapter(adaptadorSeries);

      adaptadorSeries.setOnclickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {
              Toast.makeText(Activity_VerTablaSeries.this, "Seleccion: "+Serie.get(recyclerView_series.getChildAdapterPosition(v)).getNombre(), Toast.LENGTH_SHORT).show();
              String numero_serie = Serie.get(recyclerView_series.getChildAdapterPosition(v)).getNumero_series();
              String director = Serie.get(recyclerView_series.getChildAdapterPosition(v)).getDirector();
              String nombre = Serie.get(recyclerView_series.getChildAdapterPosition(v)).getNombre();
              String estado = Serie.get(recyclerView_series.getChildAdapterPosition(v)).getEstado();
              String valoracion = Serie.get(recyclerView_series.getChildAdapterPosition(v)).getValoracion();

              Intent intent = new Intent(v.getContext(), Activity_ModificarSerie.class);
              intent.putExtra("Numero",numero_serie);
              intent.putExtra("Director",director);
              intent.putExtra("Nombre",nombre);
              intent.putExtra("Estado",estado);
              intent.putExtra("Valoreacion",valoracion);
              startActivity(intent);

          }
      });
      //Volver a la pantalla anterior
        Atras();

      Alta();
    }
    public void Atras(){
        button_Atras8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(v.getContext(), Activity_OpcionesUsuario.class);
                startActivity(i);
            }
        });
    }

    public void Alta(){
        button_AnhadirSerie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(v.getContext(), Activity_crear_serie.class);
                startActivity(i);
            }
        });
    }

}