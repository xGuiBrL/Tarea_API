package com.example.tarea_api.activities;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
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
import com.example.tarea_api.models.Vehiculo;
import com.example.tarea_api.models.VehiculoApiResponse;
import com.google.gson.Gson;
import org.json.JSONObject;
import java.util.List;

public class AllVehiculosActivity extends AppCompatActivity {

    private final String API_URL = "https://raw.githubusercontent.com/adancondori/TareaAPI/refs/heads/main/api/vehiculos.json";

    JsonObjectRequest request = new JsonObjectRequest(
            Request.Method.GET,
            API_URL,
            null,
            response -> {
                Log.d("API_DEBUG", "Respuesta: " + response.toString()); // ← Verifica el JSON
                // ...
            },
            error -> {
                Log.e("API_FAIL", "Error: " + error.toString()); // ← Ver detalles del error
            }
    );

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        fetchVehiculosFromApi(recyclerView);
    }

    private void fetchVehiculosFromApi() {
        RequestQueue queue = Volley.newRequestQueue(this);
        JsonObjectRequest request = new JsonObjectRequest(
                Request.Method.GET,
                API_URL,
                null,
                response -> {
                    try {
                        Log.d("API_RESPONSE", response.toString());
                        Gson gson = new Gson();
                        VehiculoApiResponse apiResponse = gson.fromJson(response.toString(), VehiculoApiResponse.class);

                        // Verificar si hay datos
                        if (apiResponse == null || apiResponse.getVehiculos() == null) {
                            Toast.makeText(this, "La estructura de la respuesta es incorrecta", Toast.LENGTH_SHORT).show();
                            return;
                        }

                        if (apiResponse.getVehiculos().isEmpty()) {
                            Toast.makeText(this, "No hay vehículos disponibles", Toast.LENGTH_SHORT).show();
                        }

                        setupRecyclerView(apiResponse.getVehiculos());
                    } catch (Exception e) {
                        Log.e("API_ERROR", "Error al parsear JSON: " + e.getMessage());
                        Toast.makeText(this, "Error en los datos recibidos", Toast.LENGTH_SHORT).show();
                    }
                },
                error -> {
                    Log.e("API_FAIL", "Error en la petición: " + error.getMessage());
                    Toast.makeText(this, "Error al conectar con la API", Toast.LENGTH_SHORT).show();
                }
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