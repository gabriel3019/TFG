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
 * Actividad que muestra los detalles de una Smash Burger.
 * <p>
 * Esta actividad recupera el ID del producto recibido mediante un Intent,
 * consulta la base de datos Firestore para obtener información como
 * nombre, descripción, precio e imagen, y los muestra en la interfaz.
 * También ofrece un botón para proceder a la compra y una flecha para volver.
 * </p>
 */
public class detalleSmashBurgers extends AppCompatActivity {

    private ImageView imagenProducto, flechaAtras;

    private TextView nombreProducto, descripcionProducto, precioProducto;

    private Button botonComprar;

    /**
     * Método llamado al crear la actividad.
     * <p>
     * Inicializa las vistas, recupera el ID del producto desde el Intent,
     * obtiene los datos desde Firestore y los muestra.
     * Maneja casos donde el ID no es recibido o la consulta falla.
     * </p>
     *
     * @param savedInstanceState Estado previamente guardado de la actividad.
     */
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_smash_burgers);

        imagenProducto = findViewById(R.id.imagenProducto);
        nombreProducto = findViewById(R.id.nombreProducto);
        descripcionProducto = findViewById(R.id.descripcionProducto);
        precioProducto = findViewById(R.id.precioProducto);
        botonComprar = findViewById(R.id.botonComprar);
        flechaAtras = findViewById(R.id.flecha);

        String productoId = getIntent().getStringExtra("productoId");

        if (productoId == null) {
            Toast.makeText(this, "Error: no se recibió el ID del producto", Toast.LENGTH_LONG).show();
            finish(); // Cierra la actividad para evitar más errores
            return;
        }

        FirebaseFirestore db = FirebaseFirestore.getInstance();
        db.collection("smashburgers").document(productoId).get().addOnSuccessListener(documentSnapshot -> {
            if (documentSnapshot.exists()) {
                String nombre = documentSnapshot.getString("nombre");
                String descripcion = documentSnapshot.getString("descripcion");
                String precio = documentSnapshot.getString("precio");
                String imagenUrl = documentSnapshot.getString("imagen");

                nombreProducto.setText(nombre);
                descripcionProducto.setText(descripcion);
                precioProducto.setText("Precio: " + precio + "€");

                Glide.with(this).load(imagenUrl).into(imagenProducto);
            } else {
                Toast.makeText(this, "Producto no encontrado en la base de datos", Toast.LENGTH_LONG).show();
            }
        }).addOnFailureListener(e -> {
            Toast.makeText(this, "Error al cargar los datos: " + e.getMessage(), Toast.LENGTH_LONG).show();
        });

        flechaAtras.setOnClickListener(v -> {
            Toast.makeText(this, "Clic detectado", Toast.LENGTH_SHORT).show();
            finish();
        });
        botonComprar.setOnClickListener(v -> {
            Intent intent = new Intent(detalleSmashBurgers.this, PedidosActivity.class);
            startActivity(intent);
        });
    }
}