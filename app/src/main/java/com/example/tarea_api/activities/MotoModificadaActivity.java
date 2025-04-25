package com.example.tarea_api.activities;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.tarea_api.R;
import com.example.tarea_api.adapters.VehiculoAdapter;
import com.example.tarea_api.models.MotoModificada;
import com.example.tarea_api.models.Vehiculo;
import com.example.tarea_api.models.VehiculoApiResponse;
import com.google.gson.Gson;
import java.util.ArrayList;
import java.util.List;

public class MotoModificadaActivity extends AppCompatActivity {

    private final String API_URL = "https://raw.githubusercontent.com/adancondori/TareaAPI/refs/heads/main/api/vehiculos.json";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        fetchVehiculosFromApi(recyclerView);
    }

    private void fetchVehiculosFromApi(RecyclerView recyclerView) {
        RequestQueue queue = Volley.newRequestQueue(this);
        JsonObjectRequest request = new JsonObjectRequest(
                Request.Method.GET,
                API_URL,
                null,
                response -> {
                    Gson gson = new Gson();
                    VehiculoApiResponse apiResponse = gson.fromJson(response.toString(), VehiculoApiResponse.class);

                    // Filtrar solo motos modificadas
                    List<Vehiculo> motosMod = new ArrayList<>();
                    for (Vehiculo v : apiResponse.getVehiculos()) {
                        if (v instanceof MotoModificada) {
                            motosMod.add(v);
                        }
                    }

                    setupRecyclerView(recyclerView, motosMod);
                },
                error -> Toast.makeText(this, "Error al cargar motos modificadas", Toast.LENGTH_SHORT).show()
        );

        queue.add(request);
    }

    private void setupRecyclerView(RecyclerView recyclerView, List<Vehiculo> vehiculos) {
        VehiculoAdapter adapter = new VehiculoAdapter(vehiculos, (vehiculo, position) -> {
            Intent intent = new Intent(this, DetalleActivity.class);
            intent.putExtra("vehiculo", vehiculo);
            startActivity(intent);
        });
        recyclerView.setAdapter(adapter);
    }
}