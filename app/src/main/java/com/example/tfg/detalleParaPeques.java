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
 * Actividad que muestra los detalles de un producto de la categoría "Para Peques".
 * Recupera los datos del producto desde Firestore y permite visualizar nombre, descripción,
 * precio e imagen del producto. También permite iniciar el proceso de compra.
 */
public class detalleParaPeques extends AppCompatActivity {

    private ImageView imagenProducto, flechaAtras;
    private TextView nombreProducto, descripcionProducto, precioProducto;
    private Button botonComprar;

    /**
     * Método llamado al crear la actividad. Inicializa las vistas, carga los datos del producto
     * desde Firebase Firestore y configura los botones de navegación y compra.
     *
     * @param savedInstanceState Estado anterior guardado de la actividad (si existe).
     */
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_para_peques);

        imagenProducto = findViewById(R.id.imagenProducto);
        nombreProducto = findViewById(R.id.nombreProducto);
        descripcionProducto = findViewById(R.id.descripcionProducto);
        precioProducto = findViewById(R.id.precioProducto);
        botonComprar = findViewById(R.id.botonComprar);
        flechaAtras = findViewById(R.id.flecha);

        String productoId = getIntent().getStringExtra("productoId");

        FirebaseFirestore db = FirebaseFirestore.getInstance();
        db.collection("parapeques").document(productoId).get().addOnSuccessListener(documentSnapshot -> {
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

            Toast toast = new Toast(getApplicationContext());
            toast.setDuration(Toast.LENGTH_SHORT);
            toast.setView(layout);
            toast.show();

            finish();
        });
        botonComprar.setOnClickListener(v -> {
            Intent intent = new Intent(detalleParaPeques.this, PedidosActivity.class);
            startActivity(intent);
        });
    }
}