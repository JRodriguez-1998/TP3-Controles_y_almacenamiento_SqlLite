package com.example.tp3_controlesyalmacenamientosqllite;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

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

        return builder.create();
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