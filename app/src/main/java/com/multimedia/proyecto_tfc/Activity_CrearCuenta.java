package com.multimedia.proyecto_tfc;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Activity_CrearCuenta extends AppCompatActivity {

    private TextView textView_InicioSesion;
    private EditText editText_NombreUs, editText_ContrasenhaUs;
    private Button button_Alta, button_Volver2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crear_cuenta);

        textView_InicioSesion = (TextView) findViewById(R.id.textView_InicioSesion);
        editText_NombreUs = (EditText) findViewById(R.id.editText_NombreUs);
        editText_ContrasenhaUs = (EditText) findViewById(R.id.editText_ContrasenhaUs);
        button_Alta = (Button) findViewById(R.id.button_Alta);
        button_Volver2 = (Button) findViewById(R.id.button_Volver3);

        //Metodo para volver ala pantlla anterior
        Volver();
        //Metodo para ir a la pantalla de iniciar sesión
        InicioSesion();
    }

    public void Volver(){
        button_Volver2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(v.getContext(), MainActivity.class);
                startActivity(i);
            }
        });
    }

    public void InicioSesion(){
        textView_InicioSesion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i  = new Intent(v.getContext(), Activity_InicioSesion.class);
                startActivity(i);
            }
        });
    }

    public void altaUsuario(View v){
        //Instanciamos la conexion con la base de datos
        AdminSQLiteOpenHelper adminHelper = new AdminSQLiteOpenHelper(this, "datosApp",null,1);

        //Abro la conexión de base de datos, con permisos de escritura para dar de alta los datos
        SQLiteDatabase db = adminHelper.getWritableDatabase();

         //Recogemos los datos introducidos por el usuario
        String idNombreUsuario = editText_NombreUs.getText().toString();
        String contrasenha = editText_ContrasenhaUs.getText().toString();

            if(idNombreUsuario.length()==0 || contrasenha.length()==0){
                Toast.makeText(this, "Por favor introduce todos los datos", Toast.LENGTH_SHORT).show();
            }else{
                //Los valores estan cubiertos pero comprobamos que la contraseña sea la misma en los dos editText
                     //Ahoar preparamos el alta de los datos en la base de datos

                     ContentValues valores = new ContentValues();
                     valores.put("idNombreUsuario", idNombreUsuario);
                     valores.put("contrasenha",contrasenha);

                     db.insert("Usuario", null, valores);
                     db.close();
                     Intent i = new Intent(v.getContext(), Activity_OpcionesUsuario.class);
                     startActivity(i);
            }
    }

}