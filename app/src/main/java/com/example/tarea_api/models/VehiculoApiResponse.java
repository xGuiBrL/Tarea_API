package com.example.tarea_api.models;

import com.google.gson.annotations.SerializedName;
import java.util.List;

public class VehiculoApiResponse {
    @SerializedName("page")
    private int page;

    @SerializedName("total")
    private int total;

    @SerializedName("status")
    private String status;

    @SerializedName("vehiculos")
    private List<Vehiculo> vehiculos;

    // Getters
    public List<Vehiculo> getVehiculos() {
        return vehiculos;
    }

    public int getPage() {
        return page;
    }

    public int getTotal() {
        return total;
    }

    public String getStatus() {
        return status;
    }
}