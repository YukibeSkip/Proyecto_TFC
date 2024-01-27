package com.multimedia.proyecto_tfc;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class AdminSQLiteOpenHelper  extends SQLiteOpenHelper {

    public AdminSQLiteOpenHelper(Context context, String baseDatos, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, baseDatos, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        final String Crear_Tabla_Usuario =
                "CREATE TABLE Usuario ("+
                        "idNombreUsuario TEXT PRIMARY KEY,"+
                        "contrasenha TEXT)";
        db.execSQL(Crear_Tabla_Usuario);

        final String Crear_Tabla_Usuario_Tabla =
                "CREATE TABLE Usuario_Listas("+
                    "idNombreUsuario TEXT,"+
                    "Nombre_lista TEXT)";
        db.execSQL(Crear_Tabla_Usuario_Tabla);

        final String Crear_Tabla_Libro =
                "CREATE TABLE Libros("+
                        "idNombreUsuario TEXT,"+
                        "numero_libro TEXT  PRIMARY KEY ,"+
                        "autor TEXT,"+
                        "titulo TEXT,"+
                        "estado TEXT,"+
                        "valoracion TEXT)";
        db.execSQL(Crear_Tabla_Libro);

        final String Crear_Tabla_Series =
                "CREATE TABLE Series ("+
                        "idNombreUsuario TEXT,"+
                        "numero_series TEXT PRIMARY KEY,"+
                        "director TEXT,"+
                        "nombre TEXT,"+
                        "estado TEXT,"+
                        "valoracion TEXT)";
        db.execSQL(Crear_Tabla_Series);

        final String Crear_Tabla_Pelis =
                "CREATE TABLE Pelis ("+
                        "idNombreUsuario TEXT,"+
                        "numero_peli TEXT PRIMARY KEY,"+
                        "director TEXT,"+
                        "nombre TEXT,"+
                        "estado TEXT,"+
                        "valoracion TEXT)";
        db.execSQL(Crear_Tabla_Pelis);

        final String Crear_Tabla_Trabajo =
                "CREATE TABLE Trabajo ("+
                        "idNombreUsuario TEXT,"+
                        "numero_trab TEXT PRIMARY KEY,"+
                        "nombre TEXT,"+
                        "estado TEXT,"+
                        "fecha TEXT)";
        db.execSQL(Crear_Tabla_Trabajo);

    }//Fin onCreate

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }//Fin onUpgrade

    @Override
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion){
        onDowngrade(db, oldVersion, newVersion);
    }//Fin onDowngrade

}
