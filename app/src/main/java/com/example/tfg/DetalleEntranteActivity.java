package com.example.tfg;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.google.firebase.firestore.FirebaseFirestore;

/**
 * Actividad que muestra los detalles de un producto de la categoría "Entrantes".
 * Obtiene la información desde Firestore y permite al usuario ver la imagen,
 * descripción, precio y realizar la compra.
 */
public class DetalleEntranteActivity extends AppCompatActivity {

    private ImageView imagenProducto, flechaAtras;
    private TextView nombreProducto, descripcionProducto, precioProducto;
    private Button botonComprar;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_entrante);

        // Enlaces con el layout
        imagenProducto = findViewById(R.id.imagenProducto);
        nombreProducto = findViewById(R.id.nombreProducto);
        descripcionProducto = findViewById(R.id.descripcionProducto);
        precioProducto = findViewById(R.id.precioProducto);
        botonComprar = findViewById(R.id.botonComprar);
        flechaAtras = findViewById(R.id.flecha);

        // Obtener el ID del producto desde el intent
        String productoId = getIntent().getStringExtra("productoId");

        // Cargar datos desde Firestore
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        db.collection("entrantes").document(productoId).get().addOnSuccessListener(documentSnapshot -> {
            if (documentSnapshot.exists()) {
                String nombre = documentSnapshot.getString("nombre");
                String descripcion = documentSnapshot.getString("descripcion");
                String precio = documentSnapshot.getString("precio");
                String imagenUrl = documentSnapshot.getString("imagen");

                nombreProducto.setText(nombre);
                descripcionProducto.setText(descripcion);
                precioProducto.setText("Precio: " + precio + "€");

                Glide.with(this).load(imagenUrl).into(imagenProducto);
            }
        });

        // Evento del botón "atrás"
        flechaAtras.setOnClickListener(v -> {
            LayoutInflater inflater = getLayoutInflater();
            View layout = inflater.inflate(R.layout.custom_toast, null);

            TextView text = layout.findViewById(R.id.toast_text);
            text.setText("Clic detectado");


            Toast toast = new Toast(getApplicationContext());
            toast.setDuration(Toast.LENGTH_SHORT);
            toast.setView(layout);
            toast.show();

            finish();
        });
        // Evento del botón "comprar"
        botonComprar.setOnClickListener(v -> {
            Intent intent = new Intent(DetalleEntranteActivity.this, PedidosActivity.class);
            startActivity(intent);
        });
    }
}
