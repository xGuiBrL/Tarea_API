package com.example.tarea_api.adapters;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.tarea_api.R;
import com.example.tarea_api.models.MenuItem;
import java.util.List;

public class MenuAdapter extends BaseAdapter {
    private Context context;
    private List<MenuItem> items;

    public MenuAdapter(Context context, List<MenuItem> items) {
        this.context = context;
        this.items = items;
    }

    @Override
    public int getCount() {
        return items != null ? items.size() : 0;
    }

    @Override
    public Object getItem(int position) {
        return items != null ? items.get(position) : null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = View.inflate(context, R.layout.item_menu, null);
        }

        MenuItem item = items.get(position);

        ImageView ivIcon = convertView.findViewById(R.id.ivIcon);
        TextView tvTitle = convertView.findViewById(R.id.tvTitle);

        ivIcon.setImageResource(item.getIconResource());
        tvTitle.setText(item.getTitle());

        return convertView;
    }

    // Método para actualizar datos
    public void updateData(List<MenuItem> newItems) {
        this.items = newItems;
        notifyDataSetChanged();
    }
}