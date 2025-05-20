package com.example.tfg;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.bumptech.glide.Glide;
import com.google.firebase.firestore.FirebaseFirestore;

/**
 * Actividad que muestra los detalles de un producto de la categoría "Chicken Lovers".
 * Recupera la información desde Firestore y permite al usuario iniciar el proceso de compra.
 */
public class detalleChickenLover extends AppCompatActivity {

    private ImageView imagenProducto, flechaAtras;
    private TextView nombreProducto, descripcionProducto, precioProducto;
    private Button botonComprar;

    /**
     * Método llamado al crear la actividad. Carga la información del producto desde Firestore
     * y configura los botones de navegación y acción.
     *
     * @param savedInstanceState Estado previamente guardado de la actividad (si lo hay).
     */
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_chicken_lover);

        imagenProducto = findViewById(R.id.imagenProducto);
        nombreProducto = findViewById(R.id.nombreProducto);
        descripcionProducto = findViewById(R.id.descripcionProducto);
        precioProducto = findViewById(R.id.precioProducto);
        botonComprar = findViewById(R.id.botonComprar);
        flechaAtras = findViewById(R.id.flecha);

        String productoId = getIntent().getStringExtra("productoId");

        FirebaseFirestore db = FirebaseFirestore.getInstance();
        db.collection("chickenlovers").document(productoId).get().addOnSuccessListener(documentSnapshot -> {
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

        flechaAtras.setOnClickListener(v -> {
            finish();
        });
        botonComprar.setOnClickListener(v -> {
            Intent intent = new Intent(detalleChickenLover.this, PedidosActivity.class);
            startActivity(intent);
        });
    }
}