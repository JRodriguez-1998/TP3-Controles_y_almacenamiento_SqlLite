package com.example.tp3_controlesyalmacenamientosqllite;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MenuPrincipal extends AppCompatActivity {

    private EditText et_user, et_pass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_principal);

        et_user = (EditText) findViewById(R.id.txtUsuario);
        et_pass = (EditText) findViewById(R.id.txtPassword);
    }

    public void direccionarRegistro(View v){
        Intent i = new Intent(this, RegistrarseActivity.class);
        startActivity(i);
    }

    public void login(View v){
        //Intent i = new Intent(this, navigation.class);
        //startActivity(i);

        String user = et_user.getText().toString();
        String pass = et_pass.getText().toString();

        int existe = 0;

        if(!user.isEmpty() && !pass.isEmpty()){
            existe = buscarRegistro(user, pass);
            if(existe != 0){
                Toast.makeText(this, "El USUARIO " + user + " EXISTE", Toast.LENGTH_SHORT).show();
            }
            else{
                Toast.makeText(this,"EL USUARIO NO EXISTE",Toast.LENGTH_SHORT).show();
            }
        }
        else{
            Toast.makeText(this, "Completar los campos", Toast.LENGTH_SHORT).show();
        }

    }

    //Método que verifica si el Usuario existe
    public int buscarRegistro(String nombre, String pass){
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this,"ParkingControl",null,1);
        SQLiteDatabase BaseDeDatos = admin.getWritableDatabase();

        int existe = 0;

        //Consulto si el usuario y/o pass existen
        Cursor fila = BaseDeDatos.rawQuery
                ("select * from usuarios where nombre = " + nombre + " AND pass = " + pass, null);
        if(fila.moveToFirst()) existe++;

        BaseDeDatos.close();
        return existe;
    }
}