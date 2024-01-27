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

public class Activity_VerTablaPelis extends AppCompatActivity {

    private Button button_AnhadirPeli, button_Atras3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ver_tabla_pelis);

        button_AnhadirPeli = (Button) findViewById(R.id.button_AnhadirPeli);
        button_Atras3 = (Button) findViewById(R.id.button_Atras3);

        RecyclerView recycleView_Pelis = findViewById(R.id.recycleView_Pelis);

        AdminSQLiteOpenHelper adminSQLiteOpenHelper = new AdminSQLiteOpenHelper(this, "datosApp",null, 1);
        SQLiteDatabase db = adminSQLiteOpenHelper.getReadableDatabase();

        String[] columnas = {"numero_peli","director","nombre","estado","valoracion"};
        Cursor cursor = db.query("Pelis", columnas, null,null,null,null,null);
        ArrayList<Peli> Peli = new ArrayList<>();
        while (cursor.moveToNext()){
            String numero_peli = cursor.getString(cursor.getColumnIndexOrThrow("numero_peli"));
            String director = cursor.getString(cursor.getColumnIndexOrThrow("director"));
            String nombre = cursor.getString(cursor.getColumnIndexOrThrow("nombre"));
            String estado = cursor.getString(cursor.getColumnIndexOrThrow("estado"));
            String valoracion = cursor.getString(cursor.getColumnIndexOrThrow("valoracion"));

            Peli peli = new Peli(numero_peli,director,nombre,estado,valoracion);
            Peli.add(peli);
        }
        cursor.close();
        db.close();

        AdaptadorPelis adaptadorPelis = new AdaptadorPelis(Peli);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recycleView_Pelis.setLayoutManager(layoutManager);
        recycleView_Pelis.setItemAnimator(new DefaultItemAnimator());
        recycleView_Pelis.setAdapter(adaptadorPelis);

        adaptadorPelis.setOnclickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Activity_VerTablaPelis.this, "Seleccion: "+Peli.get(recycleView_Pelis.getChildAdapterPosition(v)).getNombre(), Toast.LENGTH_SHORT).show();
                String numero_peli = Peli.get(recycleView_Pelis.getChildAdapterPosition(v)).getNumero_peli();
                String director =Peli.get(recycleView_Pelis.getChildAdapterPosition(v)).getDirector();
                String nombre =Peli.get(recycleView_Pelis.getChildAdapterPosition(v)).getNombre();
                String estado =Peli.get(recycleView_Pelis.getChildAdapterPosition(v)).getEstado();
                String valoracion =Peli.get(recycleView_Pelis.getChildAdapterPosition(v)).getValoracion();

                Intent intent = new Intent(v.getContext(), Activity_ModificarPeli.class);
                intent.putExtra("Numero",numero_peli);
                intent.putExtra("Director",director);
                intent.putExtra("Nombre",nombre);
                intent.putExtra("Estado",estado);
                intent.putExtra("Valoreacion",valoracion);
                startActivity(intent);
            }
        });
        //Metod para volver a la pantalla anterior
        Atras();
        //Metodo dar de alta Libro
        Alta();
    }

    public void Atras(){
        button_Atras3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(v.getContext(), Activity_OpcionesUsuario.class);
                startActivity(i);
            }
        });
    }

    public void Alta(){
        button_AnhadirPeli.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(v.getContext(), Activity_crear_peli.class);
                startActivity(i);
            }
        });
    }

}