package com.example.tp3_controlesyalmacenamientosqllite;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MenuPrincipal extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_principal);
    }

    public void direccionarRegistro(View v){
        Intent i = new Intent(this, RegistrarseActivity.class);
        startActivity(i);
    }

    public void login(View v){
        Intent i = new Intent(this, navigation.class);
        startActivity(i);
    }
}