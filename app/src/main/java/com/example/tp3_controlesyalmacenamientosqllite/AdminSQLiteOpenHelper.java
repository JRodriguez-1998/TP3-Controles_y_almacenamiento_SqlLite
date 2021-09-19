package com.example.tp3_controlesyalmacenamientosqllite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class AdminSQLiteOpenHelper extends SQLiteOpenHelper {
    public AdminSQLiteOpenHelper(Context context,String name,SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase BaseDeDatos) {
        String queryUsuarios = "create table usuarios(_ID integer primary key autoincrement, nombre text, correo text, pass text)";
        String queryParqueos = "create table parqueos(idParqueo integer primary key autoincrement, matricula text, minutos int, idUsuario int)";

        BaseDeDatos.execSQL(queryUsuarios);
        BaseDeDatos.execSQL(queryParqueos);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("drop table if exists parqueos");
        sqLiteDatabase.execSQL("drop table if exists usuarios");
        onCreate(sqLiteDatabase);
    }
}
