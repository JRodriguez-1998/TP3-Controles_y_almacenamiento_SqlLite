package com.example.tp3_controlesyalmacenamientosqllite;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class dialogo extends DialogFragment {

    Activity actividad;

    public dialogo() { }

    @Override
    @Nullable
    public AlertDialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        return crearDialogoRegistro();
    }

    private AlertDialog crearDialogoRegistro(){
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View v = inflater.inflate(R.layout.fragment_dialogo,null);
        builder.setView(v);

        EditText txtMatricula = (EditText) v.findViewById(R.id.txtMatricula);
        EditText txtTiempo = (EditText) v.findViewById(R.id.txtTiempo);
        TextView btnRegistrar = (TextView) v.findViewById(R.id.btnRegistrar);
        TextView btnCancelar = (TextView) v.findViewById(R.id.btnCancelar);

        btnRegistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String matricula = txtMatricula.getText().toString();
                Integer minutos = Integer.parseInt(txtTiempo.getText().toString());
                Integer idUsuario = getActivity().getIntent().getIntExtra("idUsuario", 0);

                Parqueo parqueo = new Parqueo(matricula, minutos, idUsuario);
                registrarParqueo(parqueo);
            }
        });
        btnCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getActivity(), "No funca x ahora", Toast.LENGTH_SHORT).show();
//                getActivity().onBackPressed();
            }
        });

        return builder.create();
    }

    private void registrarParqueo(Parqueo parqueo) {
        try {
            AdminSQLiteOpenHelper admSql = new AdminSQLiteOpenHelper(getActivity(), "ParkingControl",null, 1);
            SQLiteDatabase bd = admSql.getWritableDatabase();

            bd.insert("parqueos", null, parqueo.getContentValues());
            Toast.makeText(getActivity(), "Parqueo registrado exitosamente.", Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            Toast.makeText(getActivity(), "Ha ocurrido un error al registrar el parqueo.", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if(context instanceof Activity){
           this.actividad  = (Activity) context;
        }else{
            throw new RuntimeException(context.toString());
        }
    }
}