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

public class Activity_Ver_Trabajos extends AppCompatActivity {

    private Button button_TrabajoA, button_Volver8;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ver_trabajos);

        button_TrabajoA = (Button) findViewById(R.id.button_TrabajoA);
        button_Volver8 = (Button) findViewById(R.id.button_Volver8);

        RecyclerView RecycleView_Trabajos = findViewById(R.id.RecycleView_Trabajos);

        AdminSQLiteOpenHelper adminSQLiteOpenHelper = new AdminSQLiteOpenHelper(this, "datosApp",null, 1);
        SQLiteDatabase db = adminSQLiteOpenHelper.getReadableDatabase();

        String[] columnas = {"numero_trab","nombre","estado","fecha"};
        Cursor cursor = db.query("Trabajo",columnas,null,null,null,null,null);
        ArrayList<Trabaj> Trabajos = new ArrayList<>();
        while(cursor.moveToNext()) {
            String numero_trabajo = cursor.getString(cursor.getColumnIndexOrThrow("numero_trab"));
            String nombre = cursor.getString(cursor.getColumnIndexOrThrow("nombre"));
            String estado = cursor.getString(cursor.getColumnIndexOrThrow("estado"));
            String fecha = cursor.getString(cursor.getColumnIndexOrThrow("fecha"));

            Trabaj trabaj = new Trabaj(numero_trabajo,nombre,estado,fecha);
            Trabajos.add(trabaj);
        }

        cursor.close();
        db.close();

        AdaptadorTrabajos adaptadorTrabajos = new AdaptadorTrabajos(Trabajos);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        RecycleView_Trabajos.setLayoutManager(layoutManager);
        RecycleView_Trabajos.setItemAnimator(new DefaultItemAnimator());
        RecycleView_Trabajos.setAdapter(adaptadorTrabajos);

        adaptadorTrabajos.setOnclickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Activity_Ver_Trabajos.this, "Seleccion: "+Trabajos.get(RecycleView_Trabajos.getChildAdapterPosition(v)).getNombre(), Toast.LENGTH_SHORT).show();
                String numero_trabajo = Trabajos.get(RecycleView_Trabajos.getChildAdapterPosition(v)).getNumero_trabajo();
                String nombre = Trabajos.get(RecycleView_Trabajos.getChildAdapterPosition(v)).getNombre();
                String estado = Trabajos.get(RecycleView_Trabajos.getChildAdapterPosition(v)).getEstado();
                String fecha = Trabajos.get(RecycleView_Trabajos.getChildAdapterPosition(v)).getFecha();

                Intent intent = new Intent(v.getContext(), Activity_Mod_Trabajo.class);
                intent.putExtra("Numero",numero_trabajo);
                intent.putExtra("Nombre",nombre);
                intent.putExtra("Estado", estado);
                intent.putExtra("Fecha",fecha);
                startActivity(intent);
            }
        });

    //Volver atras
        Atras();
    //Dar de alta datos
        AltaTrabajo();
    }

    public void Atras(){
        button_Volver8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(v.getContext(), Activity_OpcionesUsuario.class);
                startActivity(i);
            }
        });
    }

    public void AltaTrabajo(){
        button_TrabajoA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(v.getContext(), Activity_crear_trabajo.class);
                startActivity(i);
            }
        });
    }

}