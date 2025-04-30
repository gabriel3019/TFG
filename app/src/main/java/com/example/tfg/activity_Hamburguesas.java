package com.example.tfg;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class activity_Hamburguesas extends AppCompatActivity {

    private ImageButton buttonHome;
    private ImageView flecha;

    private ImageView theBeast, kevinChingona, kevinBacon, kevinCostner, bombaSexy, mTreinta, pigma, retro, kikiller, moza, greenchofa;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_hamburguesas);

        buttonHome = findViewById(R.id.buttonHome);
        flecha = findViewById(R.id.flecha);

        theBeast = findViewById(R.id.theBeast);
        kevinChingona = findViewById(R.id.kevinChingona);
        kevinBacon = findViewById(R.id.kevinBacon);
        kevinCostner = findViewById(R.id.kevinCostner);
        bombaSexy = findViewById(R.id.bombaSexy);
        mTreinta = findViewById(R.id.mTreinta);
        pigma = findViewById(R.id.pigma);
        retro = findViewById(R.id.retro);
        kikiller = findViewById(R.id.kikiller);
        moza = findViewById(R.id.moza);
        greenchofa = findViewById(R.id.greenchofa);

        // Establecer el OnClickListener para el botón
        buttonHome.setOnClickListener(v -> {
            // Crear un Intent para ir a la actividad del menú
            Intent intent = new Intent(activity_Hamburguesas.this, menuActivity.class); // Asegúrate de que el nombre de la actividad sea correcto
            startActivity(intent); // Iniciar la actividad
        });

        flecha.setOnClickListener(v -> {
            Intent intent = new Intent(activity_Hamburguesas.this, cartaActivity.class);
            startActivity(intent);
        });

        // Listeners de productos
        theBeast.setOnClickListener(v -> abrirDetalleProducto("7Ne71jl0zeYML9c82syo"));
        kevinChingona.setOnClickListener(v -> abrirDetalleProducto("SIcRTnCFvKhosMUNAUTd"));
        kevinBacon.setOnClickListener(v -> abrirDetalleProducto("Q5U9ARGlfKehAVbOvKFu"));
        kevinCostner.setOnClickListener(v -> abrirDetalleProducto("kZ58W5cpQgiOfeoGqw6R"));
        bombaSexy.setOnClickListener(v -> abrirDetalleProducto("bxEBA5dnEpBgj3umsQLV"));
        mTreinta.setOnClickListener(v -> abrirDetalleProducto("xVDswLfGv6d0YIkFGbJ1"));
        pigma.setOnClickListener(v -> abrirDetalleProducto("b58dgzXLBVrlKDYHjTIC"));
        retro.setOnClickListener(v -> abrirDetalleProducto("CKUhxaEwJmnHq1zms56f"));
        kikiller.setOnClickListener(v -> abrirDetalleProducto("6TwyRUUz80jyRElBfdmD"));
        moza.setOnClickListener(v -> abrirDetalleProducto("ED47DRhpteGtOAiLlcR9"));
        greenchofa.setOnClickListener(v -> abrirDetalleProducto("4vOMsxPBDGzNESbm4ao3"));
    }

    // Función que abre el detalle del producto
    private void abrirDetalleProducto(String productoId) {
        Intent intent = new Intent(activity_Hamburguesas.this, detallesHamburguesasActivity.class);
        intent.putExtra("productoId", productoId);
        startActivity(intent);
    }

    }

