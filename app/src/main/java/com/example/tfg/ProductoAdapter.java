package com.example.tfg;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

/**
 * Adaptador personalizado para mostrar una lista de productos en un RecyclerView.
 */
public class ProductoAdapter extends RecyclerView.Adapter<ProductoAdapter.ProductoViewHolder> {

    private List<Producto> productos;


    /**
     * Constructor del adaptador que recibe una lista de productos.
     *
     * @param productos Lista de productos a mostrar.
     */
    public ProductoAdapter(List<Producto> productos) {
        this.productos = productos;
    }

    @NonNull
    @Override
    public ProductoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_producto, parent, false);
        return new ProductoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductoViewHolder holder, int position) {
        Producto producto = productos.get(position);
        holder.bind(producto);
    }

    @Override
    public int getItemCount() {
        return productos.size();
    }

    /**
     * ViewHolder que representa cada elemento individual de la lista.
     */
    static class ProductoViewHolder extends RecyclerView.ViewHolder {
        private TextView tvNombre, tvDescripcion;

        /**
         * Constructor del ViewHolder.
         *
         * @param itemView Vista del ítem inflado desde el XML.
         */
        public ProductoViewHolder(@NonNull View itemView) {
            super(itemView);
            tvNombre = itemView.findViewById(R.id.tvNombre);
            tvDescripcion = itemView.findViewById(R.id.tvDescripcion);
        }

        /**
         * Método para enlazar los datos del producto con los elementos de la interfaz.
         *
         * @param producto Objeto producto a mostrar.
         */
        public void bind(Producto producto) {
            tvNombre.setText(producto.getNombre());
            tvDescripcion.setText(producto.getDescripcion());
        }
    }
}
