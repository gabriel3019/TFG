package com.example.tfg;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

/**
 * Actividad que muestra las bebidas del menú de Goiko.
 * Permite la navegación hacia el menú principal, la carta y el detalle de cada bebida.
 */
public class bebidasActivity extends AppCompatActivity {

    private ImageButton buttonHome;
    private ImageView flecha;

    private ImageView goiko_ice_tea, limonada_goiko_fresh, refrescos, cervezas;

    /**
     * Método llamado al crear la actividad.
     *
     * @param savedInstanceState Estado previamente guardado de la actividad (si existe).
     */
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_bebidas);

        // Botones de navegación
        buttonHome = findViewById(R.id.buttonHome);
        flecha = findViewById(R.id.flecha);

        // Productos
        goiko_ice_tea = findViewById(R.id.icetea);
        limonada_goiko_fresh = findViewById(R.id.limonada);
        refrescos = findViewById(R.id.refrescos);
        cervezas = findViewById(R.id.cerveza);

        // Navegar a Menú
        buttonHome.setOnClickListener(v -> {
            Intent intent = new Intent(bebidasActivity.this, menuActivity.class);
            startActivity(intent);
        });

        // Navegar a Carta
        flecha.setOnClickListener(v -> {
            Intent intent = new Intent(bebidasActivity.this, cartaActivity.class);
            startActivity(intent);
        });

        // Listeners de productos
        goiko_ice_tea.setOnClickListener(v -> abrirDetalleProducto("DTOfYjPyYsJ7tHmLgtw2"));
        limonada_goiko_fresh.setOnClickListener(v -> abrirDetalleProducto("R6phRChVg1b8qYrl9JNn"));
        refrescos.setOnClickListener(v -> abrirDetalleProducto("7hfWjmWWMPAIrxCTqaxh"));
        cervezas.setOnClickListener(v -> abrirDetalleProducto("pdB9R4z5fxagwzwmGntU"));
    }

    /**
     * Abre la actividad de detalles de un producto específico.
     *
     * @param productoId El ID del producto a mostrar en detalle.
     */
    private void abrirDetalleProducto(String productoId) {
        Intent intent = new Intent(bebidasActivity.this, detallesBebidas.class);
        intent.putExtra("productoId", productoId);
        startActivity(intent);
    }
}
