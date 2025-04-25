package com.example.tarea_api.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class Moto extends Vehiculo {
    @SerializedName("cilindradaCc")
    private int cilindrada;

    @SerializedName("tipoManejo")
    private String tipoManejo;

    public Moto() {
        tipo = "Moto";
    }

    protected Moto(Parcel in) {
        super(in);
        cilindrada = in.readInt();
        tipoManejo = in.readString();
    }


    public static final Parcelable.Creator<Moto> CREATOR = new Parcelable.Creator<Moto>() {
        @Override
        public Moto createFromParcel(Parcel in) {
            return new Moto(in);
        }

        @Override
        public Moto[] newArray(int size) {
            return new Moto[size];
        }
    };

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        super.writeToParcel(dest, flags);
        dest.writeInt(cilindrada);
        dest.writeString(tipoManejo);
    }

    // Getters
    public int getCilindrada() { return cilindrada; }
    public String getTipoManejo() { return tipoManejo; }
}