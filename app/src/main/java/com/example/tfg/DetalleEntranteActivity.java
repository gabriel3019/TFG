package com.example.tfg;


import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
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

    /**
     * Método que se ejecuta al iniciar la actividad. Recupera los datos del producto desde Firestore
     * y configura los botones de navegación y acción.
     *
     * @param savedInstanceState Estado previamente guardado de la actividad (si lo hay).
     */
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_entrante);

        imagenProducto = findViewById(R.id.imagenProducto);
        nombreProducto = findViewById(R.id.nombreProducto);
        descripcionProducto = findViewById(R.id.descripcionProducto);
        precioProducto = findViewById(R.id.precioProducto);
        botonComprar = findViewById(R.id.botonComprar);
        flechaAtras = findViewById(R.id.flecha);

        String productoId = getIntent().getStringExtra("productoId");

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

        flechaAtras.setOnClickListener(v -> {
            Toast.makeText(this, "Clic detectado", Toast.LENGTH_SHORT).show();
            finish();
        });
        botonComprar.setOnClickListener(v -> {
            Intent intent = new Intent(DetalleEntranteActivity.this, PedidosActivity.class);
            startActivity(intent);
        });
    }
}