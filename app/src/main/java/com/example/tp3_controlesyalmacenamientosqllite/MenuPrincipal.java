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

    private EditText et_correo, et_pass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_principal);

        et_correo = (EditText) findViewById(R.id.txtCorreo);
        et_pass = (EditText) findViewById(R.id.txtPassword);
    }

    public void direccionarRegistro(View v){
        Intent i = new Intent(this, RegistrarseActivity.class);
        startActivity(i);
    }

    public void login(View v){
        AdminSQLiteOpenHelper admSql = new AdminSQLiteOpenHelper(this, "ParkingControl",null, 1);
        SQLiteDatabase bd = admSql.getWritableDatabase();

        String email = et_correo.getText().toString();
        String pass = et_pass.getText().toString();

            if(!email.isEmpty() && !pass.isEmpty()){
            Cursor fila = bd.rawQuery
                    ("select * from usuarios where correo ='" + email + "'" + " and pass ='" + pass + "'",
                            null);
            if(fila.moveToFirst()){
                    bd.close();
                    Toast.makeText(this,"Bienvenido/a", Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(this, navigation.class);
                    startActivity(i);
                }
                else{
                    Toast.makeText(this,"Usuario inexistente", Toast.LENGTH_SHORT).show();
                }
            }
            else{
            Toast.makeText(this,"Complete ambos campos", Toast.LENGTH_SHORT).show();
        }
    }
}