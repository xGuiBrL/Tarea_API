package com.example.tarea_api.activities;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.example.tarea_api.R;
import com.example.tarea_api.models.*;
import com.squareup.picasso.Picasso;

public class DetalleActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle);

        Vehiculo vehiculo = getIntent().getParcelableExtra("vehiculo");

        TextView tvTitulo = findViewById(R.id.tvTitulo);
        TextView tvDetalles = findViewById(R.id.tvDetalles);
        TextView tvEspecificaciones = findViewById(R.id.tvEspecificaciones);
        ImageView ivVehiculo = findViewById(R.id.ivVehiculoDetalle);

        if (vehiculo != null) {
            // Datos comunes
            tvTitulo.setText(String.format("%s %s", vehiculo.getMarca(), vehiculo.getModelo()));
            tvDetalles.setText(
                    String.format("Año: %d\nPrecio: $%.2f\nKilómetros: %,d km",
                            vehiculo.getAñoFabricacion(),
                            vehiculo.getPrecioBase(),
                            vehiculo.getKilometraje())
            );

            // Datos específicos
            if (vehiculo instanceof AutoNuevo) {
                AutoNuevo auto = (AutoNuevo) vehiculo;
                tvEspecificaciones.setText(
                        String.format("Garantía: %d años\nDescuento: %.1f%%\nPrecio final: $%.2f",
                                auto.getGarantiaAños(),
                                auto.getDescuentoPromocional(),
                                auto.getPrecioBase() * (1 - auto.getDescuentoPromocional()/100))
                );
                ivVehiculo.setVisibility(ImageView.GONE);

            } else if (vehiculo instanceof MotoModificada) {
                MotoModificada motoMod = (MotoModificada) vehiculo;
                tvEspecificaciones.setText(
                        String.format("%d cc | %s\nModificación: %s",
                                motoMod.getCilindrada(),
                                motoMod.getTipoManejo(),
                                motoMod.getTipoModificacion())
                );
                // Cargar imagen de ejemplo (reemplaza con tu lógica real)
                Picasso.get()
                        .load(R.drawable.moto_placeholder)
                        .into(ivVehiculo);
                ivVehiculo.setVisibility(ImageView.VISIBLE);

            } else if (vehiculo instanceof Moto) {
                Moto moto = (Moto) vehiculo;
                tvEspecificaciones.setText(
                        String.format("%d cc | %s",
                                moto.getCilindrada(),
                                moto.getTipoManejo())
                );
                ivVehiculo.setVisibility(ImageView.GONE);
            }
        }
    }
}