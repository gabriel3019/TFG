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

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.bumptech.glide.Glide;
import com.google.firebase.firestore.FirebaseFirestore;

/**
 * Actividad que muestra los detalles de una ensalada (salad).
 * Recupera los datos desde Firebase Firestore y los muestra en pantalla,
 * incluyendo nombre, descripción, precio e imagen del producto.
 * También permite al usuario iniciar el proceso de compra.
 */
public class detalleSalad extends AppCompatActivity {
    private ImageView imagenProducto, flechaAtras;
    private TextView nombreProducto, descripcionProducto, precioProducto;
    private Button botonComprar;

    /**
     * Método que se ejecuta al crear la actividad.
     * Inicializa las vistas, obtiene el ID del producto, consulta los datos en Firestore
     * y muestra la información en pantalla.
     *
     * @param savedInstanceState Estado guardado anteriormente (si existe).
     */
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_salad);

        imagenProducto = findViewById(R.id.imagenProducto);
        nombreProducto = findViewById(R.id.nombreProducto);
        descripcionProducto = findViewById(R.id.descripcionProducto);
        precioProducto = findViewById(R.id.precioProducto);
        botonComprar = findViewById(R.id.botonComprar);
        flechaAtras = findViewById(R.id.flecha);

        String productoId = getIntent().getStringExtra("productoId");

        FirebaseFirestore db = FirebaseFirestore.getInstance();
        db.collection("salads").document(productoId).get().addOnSuccessListener(documentSnapshot -> {
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

        botonComprar.setOnClickListener(v -> {
            Intent intent = new Intent(detalleSalad.this, PedidosActivity.class);
            startActivity(intent);
        });
    }
}