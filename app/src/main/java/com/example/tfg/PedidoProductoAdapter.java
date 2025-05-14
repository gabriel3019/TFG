package com.example.tfg;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

public class PedidoProductoAdapter extends RecyclerView.Adapter<PedidoProductoAdapter.PedidoProductoViewHolder> {

    private List<ProductoPedido> productos;

    public PedidoProductoAdapter(List<ProductoPedido> productos) {
        this.productos = productos;
    }

    @Override
    public PedidoProductoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_producto_pedido, parent, false);
        return new PedidoProductoViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(PedidoProductoViewHolder holder, int position) {
        ProductoPedido producto = productos.get(position);

        holder.nombre.setText(producto.getNombre());
        holder.precio.setText(producto.getPrecio() + " €"); // Mostramos el precio como un String
        Glide.with(holder.imagen.getContext())
                .load(producto.getImagen()) // <- aquí cambia a getImagen()
                .into(holder.imagen);

        holder.btnSumar.setOnClickListener(v -> {
            int cantidad = Integer.parseInt(holder.contador.getText().toString());
            holder.contador.setText(String.valueOf(cantidad + 1));
        });

        holder.btnRestar.setOnClickListener(v -> {
            int cantidad = Integer.parseInt(holder.contador.getText().toString());
            if (cantidad > 0) {
                holder.contador.setText(String.valueOf(cantidad - 1));
            }
        });
    }

    @Override
    public int getItemCount() {
        return productos.size();
    }

    public class PedidoProductoViewHolder extends RecyclerView.ViewHolder {

        public TextView nombre, precio, contador;
        public ImageView imagen;
        public Button btnSumar, btnRestar;

        public PedidoProductoViewHolder(View itemView) {
            super(itemView);

            nombre = itemView.findViewById(R.id.nombreProductoPedido);
            precio = itemView.findViewById(R.id.precioProductoPedido);
            imagen = itemView.findViewById(R.id.imagenProductoPedido);
            contador = itemView.findViewById(R.id.contadorProductoPedido);
            btnSumar = itemView.findViewById(R.id.btnSumarProductoPedido);
            btnRestar = itemView.findViewById(R.id.btnRestarProductoPedido);
        }
    }
}
