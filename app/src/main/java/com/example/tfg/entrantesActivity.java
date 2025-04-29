package com.example.tfg;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class entrantesActivity extends AppCompatActivity {

    private ImageButton buttonHome;
    private ImageView flecha;

    private ImageView crispyChickenWings, teques, tequesVinci, chiquenTenders, nachorreo, onionRings;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_entrantes);

        // Botones de navegación
        buttonHome = findViewById(R.id.buttonHome);
        flecha = findViewById(R.id.flecha);

        // Productos
        crispyChickenWings = findViewById(R.id.crispy_chicken_wings);
        teques = findViewById(R.id.teques);
        tequesVinci = findViewById(R.id.teques_vinci);
        chiquenTenders = findViewById(R.id.chiquen_tenders);
        nachorreo = findViewById(R.id.nachorreo);
        onionRings = findViewById(R.id.onion_rings);

        // Navegar a Menú
        buttonHome.setOnClickListener(v -> {
            Intent intent = new Intent(entrantesActivity.this, menuActivity.class);
            startActivity(intent);
        });

        // Navegar a Carta
        flecha.setOnClickListener(v -> {
            Intent intent = new Intent(entrantesActivity.this, cartaActivity.class);
            startActivity(intent);
        });

        // Listeners de productos
        crispyChickenWings.setOnClickListener(v -> abrirDetalleProducto("IlhyoXPWEVzkTANxkLTZ"));
        teques.setOnClickListener(v -> abrirDetalleProducto("vNQfAhDnKzrRKFFjk0l7"));
        tequesVinci.setOnClickListener(v -> abrirDetalleProducto("91Ov3v5GtGXrNNlUsC3w"));
        chiquenTenders.setOnClickListener(v -> abrirDetalleProducto("ZEjJa475Fb9Yfet9VLzb"));
        nachorreo.setOnClickListener(v -> abrirDetalleProducto("Q4Qw71ZC9OIZOf5LvxB4"));
        onionRings.setOnClickListener(v -> abrirDetalleProducto("cmVDTqz7Cg1VffPGSvw9")); // ID real de ejemplo
    }

    // Función que abre el detalle del producto
    private void abrirDetalleProducto(String productoId) {
        Intent intent = new Intent(entrantesActivity.this, DetalleEntranteActivity.class);
        intent.putExtra("productoId", productoId);
        startActivity(intent);
    }
}