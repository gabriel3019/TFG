package com.example.tfg;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

/**
 * Adaptador personalizado para mostrar una lista de restaurantes en un RecyclerView.
 * Utiliza el layout {@code restaurante_item.xml} para cada ítem.
 */
public class RestAdapter extends RecyclerView.Adapter<RestAdapter.RestViewHolder> {

    private final List<Restaurante> restaurantes;

    /**
     * Constructor del adaptador.
     *
     * @param restaurantes Lista de restaurantes a mostrar.
     */
    public RestAdapter(List<Restaurante> restaurantes) {
        this.restaurantes = restaurantes;
    }

    /**
     * Crea una nueva instancia de {@link RestViewHolder} inflando el layout del ítem.
     *
     * @param parent   El ViewGroup padre donde se añadirá la vista.
     * @param viewType Tipo de vista (no se usa en este caso).
     * @return Una nueva instancia de {@link RestViewHolder}.
     */

    @Override
    public RestViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.restaurante_item, parent, false);
        return new RestViewHolder(view);
    }


    /**
     * Asocia los datos del restaurante con los elementos de la vista.
     *
     * @param holder   ViewHolder que contiene los elementos de UI.
     * @param position Posición del elemento en la lista.
     */
    @Override
    public void onBindViewHolder(RestViewHolder holder, int position) {
        Restaurante restaurante = restaurantes.get(position);
        holder.nameTextView.setText(restaurante.getName());
        holder.addressTextView.setText(restaurante.getAddress());
        holder.telephoneTextView.setText(String.valueOf(restaurante.getTelephone()));
        holder.distanceTextView.setText(restaurante.getDistanciaKm());
    }

    /**
     * Devuelve el número total de elementos en la lista.
     *
     * @return Número de restaurantes.
     */
    @Override
    public int getItemCount() {
        return restaurantes.size();
    }

    /**
     * ViewHolder que representa un único ítem en la lista del RecyclerView.
     */
    public static class RestViewHolder extends RecyclerView.ViewHolder {
        public TextView nameTextView;
        public TextView addressTextView;
        public TextView telephoneTextView;
        public TextView distanceTextView;

        /**
         * Constructor del ViewHolder.
         *
         * @param itemView Vista que representa el ítem de restaurante.
         */
        public RestViewHolder(View itemView) {
            super(itemView);
            nameTextView = itemView.findViewById(R.id.restName);
            addressTextView = itemView.findViewById(R.id.restAddress);
            telephoneTextView = itemView.findViewById(R.id.restTelephone);
            distanceTextView = itemView.findViewById(R.id.restDistance);
        }
    }
}
