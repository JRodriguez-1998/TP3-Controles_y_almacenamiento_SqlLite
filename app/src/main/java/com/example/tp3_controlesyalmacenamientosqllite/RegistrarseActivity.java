package com.example.tp3_controlesyalmacenamientosqllite;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class RegistrarseActivity extends AppCompatActivity {

    private EditText et_nombre, et_correo, et_pass, et_confimpass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrarse);

        et_nombre = (EditText) findViewById(R.id.txtNombre);
        et_correo = (EditText) findViewById(R.id.txtCorreo);
        et_pass = (EditText) findViewById(R.id.txtPassword);
        et_confimpass = (EditText) findViewById(R.id.txtRepetirContra);

    }

    //Método que registra nuevo Usuario
    public void Registrar(View view){
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this,"ParkingControl",null,1);
        SQLiteDatabase BaseDeDatos = admin.getWritableDatabase();

        String nombre = et_nombre.getText().toString();
        String correo = et_correo.getText().toString();
        String pass = et_pass.getText().toString();
        String repecontra = et_confimpass.getText().toString();

        int existe = 0;

        if(!nombre.isEmpty() && !correo.isEmpty() && !pass.isEmpty() && !repecontra.isEmpty()){
            if(pass == repecontra){
                existe = buscarRegistro(correo);
                if(existe == 0){
                    ContentValues registro = new ContentValues();
                    registro.put("nombre", nombre);
                    registro.put("correo", correo);
                    registro.put("pass", pass);

                    try {
                        BaseDeDatos.insert("usuarios",null,registro);

                        BaseDeDatos.close();

                        Toast.makeText(this, "Usuario Registrado Correctamente", Toast.LENGTH_SHORT).show();

                        Intent intent =  new Intent(this, MenuPrincipal.class);
                        startActivity(intent);
                    }
                    catch (SQLException exception){
                        Toast.makeText(this, "Error al Registrar Usuario", Toast.LENGTH_SHORT).show();
                        BaseDeDatos.close();
                    }
                }
                else{
                    Toast.makeText(this, "El Usuario ya existe", Toast.LENGTH_SHORT).show();
                }
            }
            else{
                Toast.makeText(this, "Las Contraseñas no coinciden", Toast.LENGTH_SHORT).show();
                et_confimpass.setText("");
            }
        }
        else{
            Toast.makeText(this, "Completar todos los campos", Toast.LENGTH_SHORT).show();
        }
        BaseDeDatos.close();
    }

    //Método que verifica si el Usuario existe
    public int buscarRegistro(String correo){
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this,"ParkingControl",null,1);
        SQLiteDatabase BaseDeDatos = admin.getWritableDatabase();

        int existe = 0;

        //Consulto si el correo existe
        Cursor fila = BaseDeDatos.rawQuery
                ("select * from usuarios where correo = " + correo, null);
        if(fila.moveToFirst()) existe++;

        BaseDeDatos.close();
        return existe;
    }
}