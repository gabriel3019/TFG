package com.example.tfg;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class RestAdapter extends RecyclerView.Adapter<RestAdapter.RestViewHolder> {

    private final List<Restaurante> restaurantes;

    public RestAdapter(List<Restaurante> restaurantes) {
        this.restaurantes = restaurantes;
    }

    @Override
    public RestViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.restaurante_item, parent, false);
        return new RestViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RestViewHolder holder, int position) {
        Restaurante restaurante = restaurantes.get(position);
        holder.nameTextView.setText(restaurante.getName());
        holder.addressTextView.setText(restaurante.getAddress());
        holder.telephoneTextView.setText(String.valueOf(restaurante.getTelephone()));
        holder.distanceTextView.setText(restaurante.getDistanciaKm());
    }

    @Override
    public int getItemCount() {
        return restaurantes.size();
    }

    public static class RestViewHolder extends RecyclerView.ViewHolder {
        public TextView nameTextView;
        public TextView addressTextView;
        public TextView telephoneTextView;
        public TextView distanceTextView;

        public RestViewHolder(View itemView) {
            super(itemView);
            nameTextView = itemView.findViewById(R.id.restName);
            addressTextView = itemView.findViewById(R.id.restAddress);
            telephoneTextView = itemView.findViewById(R.id.restTelephone);
            distanceTextView = itemView.findViewById(R.id.restDistance);
        }
    }
}
