package com.multimedia.proyecto_tfc;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Activity_InicioSesion extends AppCompatActivity {

    private TextView textView_IrCreacionCuenta;
    private EditText editText_Nombre, editText_Contrasenha;

    private Button button_IniciarSesion, button_Volver;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio_sesion);

        textView_IrCreacionCuenta = (TextView) findViewById(R.id.textView_IrCreacionCuenta);
        editText_Nombre = (EditText) findViewById(R.id.editText_Nombre);
        editText_Contrasenha = (EditText) findViewById(R.id.editText_Contrasenha);
        button_IniciarSesion = (Button) findViewById(R.id.button_InicioSesion);
        button_Volver = (Button) findViewById(R.id.button_Volver);

        //Opción para volver a la pantalla anterior
        Volver();
        //Opción para ir a la pantalla de dar de alta un usuario
        Alta();
    }

    //Metodo con el que se puede Volver a la pantalla anterior
    public void Volver(){
        button_Volver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(v.getContext(), MainActivity.class);
                startActivity(i);
            }
        });
    }

    //Metodo con el que podremos ir a la opción de dar de alta un usuario
    public void Alta(){
        textView_IrCreacionCuenta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(v.getContext(), Activity_CrearCuenta.class);
                startActivity(i);
            }
        });
    }

    public void EntrarAplicacion(View v){

        //Instanciamos la conxión a base de datos
        AdminSQLiteOpenHelper adminHelper = new AdminSQLiteOpenHelper(this, "datosApp",null,1);

        //Se abre una conexión de base de datos, con permisos de escritura para realizar las altas
        SQLiteDatabase db = adminHelper.getWritableDatabase();
        String idNombreUsuario = editText_Nombre.getText().toString();
        String contrasenha = editText_Contrasenha.getText().toString();

        //Compruebo que los datos para entrar no esten vacios
        if(idNombreUsuario.length()==0 || contrasenha.length()==0){
            Toast.makeText(this, "Por favor introduce todos los datos", Toast.LENGTH_SHORT).show();

            //Si se han introducido los datos hacemos lo siguiente
        }else{
            //Comrpeubo que el nombre escrito y el de la base de datos coninciden
            String[] datos = {"idNombreUsuario","contrasenha"};
            String seleccion = "idNombreUsuario"+" = ?";
            String[] condicion = {idNombreUsuario};
            Cursor cursor = db.query("Usuario", datos, seleccion,condicion,null,null,null);

            //Si existe un usuario dado de alta con ese nombre
            if(cursor.moveToFirst()){
                //Hago lo mismo con la contraseña
                String[] dato = {"contrasenha"};
                String seleccion2 = "contrasenha"+" = ?";
                String[] condicion2 = {contrasenha};
                Cursor cursor2 = db.query("Usuario", dato, seleccion2, condicion2, null, null, null);

                //Si los datos coninciden le dejo entrar a la aplicación
                if(cursor2.moveToFirst()){
                    db.close();
                    Intent i = new Intent(v.getContext(), Activity_OpcionesUsuario.class);
                    startActivity(i);
                //Si la contraseña es incorrecta se lo muestro al usuario
                }else{
                    Toast.makeText(this, "La contraseña es incorrecta", Toast.LENGTH_SHORT).show();
                }
                //Si el nombre del usuario no existe se lo muestro al usuario
            }else{
                Toast.makeText(this, "El nombre de usuario no existe o esta mal escrito", Toast.LENGTH_SHORT).show();
            }
        }
    }

}