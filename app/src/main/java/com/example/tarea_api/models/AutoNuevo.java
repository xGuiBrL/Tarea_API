package com.example.tarea_api.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class AutoNuevo extends Vehiculo {
    @SerializedName("garantiaAnios")
    private int garantiaAños;

    @SerializedName("descuentoPromocional")
    private double descuentoPromocional;

    public AutoNuevo() {
        tipo = "AutoNuevo";
    }

    protected AutoNuevo(Parcel in) {
        super(in);
        garantiaAños = in.readInt();
        descuentoPromocional = in.readDouble();
    }

    public static final Parcelable.Creator<AutoNuevo> CREATOR = new Parcelable.Creator<AutoNuevo>() {
        @Override
        public AutoNuevo createFromParcel(Parcel in) {
            return new AutoNuevo(in);
        }

        @Override
        public AutoNuevo[] newArray(int size) {
            return new AutoNuevo[size];
        }
    };

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        super.writeToParcel(dest, flags);
        dest.writeInt(garantiaAños);
        dest.writeDouble(descuentoPromocional);
    }

    // Getters y Setters
    public int getGarantiaAños() { return garantiaAños; }
    public double getDescuentoPromocional() { return descuentoPromocional; }
    public double getPrecioFinal() { return precioBase * (1 - descuentoPromocional / 100); }
}