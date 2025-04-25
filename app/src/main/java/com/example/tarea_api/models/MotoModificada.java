package com.example.tarea_api.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class MotoModificada extends Moto {
    @SerializedName("tipoModificacion")
    private String tipoModificacion;

    public MotoModificada() {
        tipo = "MotoModificada";
    }

    protected MotoModificada(Parcel in) {
        super(in);
        tipoModificacion = in.readString();
    }

    public static final Parcelable.Creator<MotoModificada> CREATOR = new Parcelable.Creator<MotoModificada>() {
        @Override
        public MotoModificada createFromParcel(Parcel in) {
            return new MotoModificada(in);
        }

        @Override
        public MotoModificada[] newArray(int size) {
            return new MotoModificada[size];
        }
    };

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        super.writeToParcel(dest, flags);
        dest.writeString(tipoModificacion);
    }

    // Getters
    public String getTipoModificacion() { return tipoModificacion; }
}