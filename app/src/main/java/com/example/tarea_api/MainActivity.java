package com.example.tarea_api;

import android.content.Intent;
import android.os.Bundle;
import android.widget.GridView;
import androidx.appcompat.app.AppCompatActivity;

import com.example.adapter_tarea.activities.AllVehiculosActivity;
import com.example.adapter_tarea.activities.AutoNuevoActivity;
import com.example.adapter_tarea.activities.MotoActivity;
import com.example.adapter_tarea.activities.MotoModificadaActivity;
import com.example.adapter_tarea.adapters.MenuAdapter;
import com.example.adapter_tarea.models.MenuItem;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main); // Asegúrate que use el layout correcto

        GridView gridView = findViewById(R.id.gridViewMenu);
        List<MenuItem> menuItems = new ArrayList<>();

        // Usa los recursos de drawable que sí existen
        menuItems.add(new MenuItem("Autos Nuevos", R.drawable.ic_car));
        menuItems.add(new MenuItem("Motos", R.drawable.ic_motorcycle));
        menuItems.add(new MenuItem("Motos Modificadas", R.drawable.ic_tune));
        menuItems.add(new MenuItem("Todos los Vehículos", R.drawable.ic_list));

        MenuAdapter adapter = new MenuAdapter(this, menuItems);
        gridView.setAdapter(adapter);

        gridView.setOnItemClickListener((parent, view, position, id) -> {
            Intent intent;
            switch (position) {
                case 0:
                    intent = new Intent(this, AutoNuevoActivity.class);
                    break;
                case 1:
                    intent = new Intent(this, MotoActivity.class);
                    break;
                case 2:
                    intent = new Intent(this, MotoModificadaActivity.class);
                    break;
                default:
                    intent = new Intent(this, AllVehiculosActivity.class);
            }
            startActivity(intent);
        });
    }
}