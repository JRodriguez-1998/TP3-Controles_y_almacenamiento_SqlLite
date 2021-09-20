package com.example.tp3_controlesyalmacenamientosqllite;

import android.content.ContentValues;

import java.io.Serializable;

public class Parqueo implements Serializable {
    Integer idParqueo;
    String matricula;
    Integer minutos;
    Integer idUsuario;

    public Parqueo() {
    }

    public Parqueo(String matricula, Integer minutos, Integer idUsuario) {
        this.matricula = matricula;
        this.minutos = minutos;
        this.idUsuario = idUsuario;
    }

    public Integer getIdParqueo() {
        return idParqueo;
    }

    public void setIdParqueo(Integer idParqueo) {
        this.idParqueo = idParqueo;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public Integer getMinutos() {
        return minutos;
    }

    public void setMinutos(Integer minutos) {
        this.minutos = minutos;
    }

    public Integer getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Integer idUsuario) {
        this.idUsuario = idUsuario;
    }

    public ContentValues getContentValues() {
        ContentValues values = new ContentValues();

        values.put("idParqueo", this.idParqueo);
        values.put("matricula", this.matricula);
        values.put("minutos", this.minutos);
        values.put("idUsuario", this.idUsuario);

        return values;
    }

    @Override
    public String toString() {
        return matricula + "\n" + minutos;
    }


}
