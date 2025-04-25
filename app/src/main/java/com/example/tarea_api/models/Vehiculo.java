package com.example.tarea_api.models;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.SerializedName;

public class Vehiculo implements Parcelable {
    @SerializedName("id")
    private int id;

    @SerializedName("marca")
    private String marca;

    @SerializedName("modelo")
    private String modelo;

    @SerializedName("anioFabricacion")
    private int añoFabricacion;

    @SerializedName("precioBase")
    private double precioBase;

    @SerializedName("kilometraje")
    private int kilometraje;

    @SerializedName("tipo")
    private String tipo; // Campo requerido para la diferenciación

    // Constructor vacío para Gson
    public Vehiculo() {}

    protected Vehiculo(Parcel in) {
        id = in.readInt();
        marca = in.readString();
        modelo = in.readString();
        añoFabricacion = in.readInt();
        precioBase = in.readDouble();
        kilometraje = in.readInt();
        tipo = in.readString();
    }

    public static final Creator<Vehiculo> CREATOR = new Creator<Vehiculo>() {
        @Override
        public Vehiculo createFromParcel(Parcel in) {
            return new Vehiculo(in);
        }

        @Override
        public Vehiculo[] newArray(int size) {
            return new Vehiculo[size];
        }
    };

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(marca);
        dest.writeString(modelo);
        dest.writeInt(añoFabricacion);
        dest.writeDouble(precioBase);
        dest.writeInt(kilometraje);
        dest.writeString(tipo);
    }

    // Getters
    public String getTipo() { return tipo; }
    public String getMarca() { return marca; }
    public String getModelo() { return modelo; }
    public int getAñoFabricacion() { return añoFabricacion; }
    public double getPrecioBase() { return precioBase; }
    public int getKilometraje() { return kilometraje; }
}