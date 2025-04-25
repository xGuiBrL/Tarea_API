package com.example.tarea_api.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.tarea_api.R;
import com.example.tarea_api.models.*;
import java.util.List;

public class VehiculoAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private static final int TYPE_AUTO = 1;
    private static final int TYPE_MOTO = 2;
    private static final int TYPE_MOTO_MOD = 3;

    private List<Vehiculo> vehiculos;
    private OnItemClickListener listener;

    public interface OnItemClickListener {
        void onItemClick(Vehiculo vehiculo, int position);
    }

    public VehiculoAdapter(List<Vehiculo> vehiculos, OnItemClickListener listener) {
        this.vehiculos = vehiculos;
        this.listener = listener;
    }

    @Override
    public int getItemViewType(int position) {
        Vehiculo v = vehiculos.get(position);
        if ("AutoNuevo".equals(v.getTipo())) return TYPE_AUTO;
        if ("MotoModificada".equals(v.getTipo())) return TYPE_MOTO_MOD;
        return TYPE_MOTO;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        switch (viewType) {
            case TYPE_AUTO:
                return new AutoViewHolder(inflater.inflate(R.layout.item_auto, parent, false));
            case TYPE_MOTO_MOD:
                return new MotoModViewHolder(inflater.inflate(R.layout.item_moto_mod, parent, false));
            default:
                return new MotoViewHolder(inflater.inflate(R.layout.item_moto, parent, false));
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        Vehiculo vehiculo = vehiculos.get(position);

        if (holder instanceof AutoViewHolder) {
            AutoViewHolder autoHolder = (AutoViewHolder) holder;
            AutoNuevo auto = (AutoNuevo) vehiculo;
            autoHolder.tvTitle.setText(String.format("%s %s", auto.getMarca(), auto.getModelo()));
            autoHolder.tvDetails.setText(
                    String.format("Año: %d | $%.2f\n%d años garantía | %.1f%% desc",
                            auto.getAñoFabricacion(),
                            auto.getPrecioBase(),
                            auto.getGarantiaAños(),
                            auto.getDescuentoPromocional())
            );

        } else if (holder instanceof MotoModViewHolder) {
            MotoModViewHolder modHolder = (MotoModViewHolder) holder;
            MotoModificada motoMod = (MotoModificada) vehiculo;
            modHolder.tvTitle.setText(String.format("%s %s", motoMod.getMarca(), motoMod.getModelo()));
            modHolder.tvMod.setText(String.format("Mod: %s", motoMod.getTipoModificacion()));
            modHolder.tvDetails.setText(
                    String.format("%dcc | %s", motoMod.getCilindrada(), motoMod.getTipoManejo())
            );

        } else if (holder instanceof MotoViewHolder) {
            MotoViewHolder motoHolder = (MotoViewHolder) holder;
            Moto moto = (Moto) vehiculo;
            motoHolder.tvTitle.setText(String.format("%s %s", moto.getMarca(), moto.getModelo()));
            motoHolder.tvDetails.setText(
                    String.format("%dcc | %s", moto.getCilindrada(), moto.getTipoManejo())
            );
        }

        holder.itemView.setOnClickListener(v -> {
            if (listener != null) {
                listener.onItemClick(vehiculo, position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return vehiculos != null ? vehiculos.size() : 0;
    }

    public void updateData(List<Vehiculo> newVehiculos) {
        this.vehiculos = newVehiculos;
        notifyDataSetChanged();
    }

    /* ViewHolders */
    static class AutoViewHolder extends RecyclerView.ViewHolder {
        TextView tvTitle, tvDetails;

        AutoViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTitle = itemView.findViewById(R.id.tvTitle);
            tvDetails = itemView.findViewById(R.id.tvDetails);
        }
    }

    static class MotoViewHolder extends RecyclerView.ViewHolder {
        TextView tvTitle, tvDetails;

        MotoViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTitle = itemView.findViewById(R.id.tvTitle);
            tvDetails = itemView.findViewById(R.id.tvDetails);
        }
    }

    static class MotoModViewHolder extends RecyclerView.ViewHolder {
        TextView tvTitle, tvMod, tvDetails;

        MotoModViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTitle = itemView.findViewById(R.id.tvTitle);
            tvMod = itemView.findViewById(R.id.tvMod);
            tvDetails = itemView.findViewById(R.id.tvDetails);
        }
    }
}