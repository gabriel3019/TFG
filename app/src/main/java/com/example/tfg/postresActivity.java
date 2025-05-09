package com.example.tfg;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class postresActivity extends AppCompatActivity {

    private ImageButton buttonHome;
    private ImageView flecha;

    private ImageView brownieSpears, frozenDeLaYaya, heladoDeChocolate, heladoDeVainilla, frozenGoikoOriginal, frozenPistacho, goikoCookie, cheeseCake;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_postres);

        // Botones de navegación
        buttonHome = findViewById(R.id.buttonHome);
        flecha = findViewById(R.id.flecha);

        // Productos
        brownieSpears = findViewById(R.id.brownie_spears);
        frozenDeLaYaya = findViewById(R.id.frozen_de_la_yaya);
        frozenGoikoOriginal = findViewById(R.id.frozen_goiko_original);
        frozenPistacho = findViewById(R.id.frozen_pistacho);
        goikoCookie = findViewById(R.id.goiko_cookie);
        heladoDeChocolate = findViewById(R.id.helado_de_chocolate);
        cheeseCake = findViewById(R.id.cheesecake);
        heladoDeVainilla = findViewById(R.id.helado_de_vainilla);

        // Establecer el OnClickListener para el botón
        buttonHome.setOnClickListener(v -> {
            // Crear un Intent para ir a la actividad del menú
            Intent intent = new Intent(postresActivity.this, menuActivity.class); // Asegúrate de que el nombre de la actividad sea correcto
            startActivity(intent); // Iniciar la actividad
        });

        flecha.setOnClickListener(v -> {
            Intent intent = new Intent(postresActivity.this, cartaActivity.class);
            startActivity(intent);
        });

        brownieSpears.setOnClickListener(v -> abrirDetalleProducto("1vacU2XVCSdiQvOQFJYq"));
        frozenDeLaYaya.setOnClickListener(v -> abrirDetalleProducto("8VZKXnkKOdXlP1owKl9s"));
        frozenGoikoOriginal.setOnClickListener(v -> abrirDetalleProducto("9M6FeDBu676qFhkcPmR3"));
        frozenPistacho.setOnClickListener(v -> abrirDetalleProducto("GzywUhr2HkbUhV0fc0Lh"));
        goikoCookie.setOnClickListener(v -> abrirDetalleProducto("NeYoJtiJFqlNdTBEGIy5"));
        heladoDeChocolate.setOnClickListener(v -> abrirDetalleProducto("TTuhHl3h9962LJgEFyMz"));
        cheeseCake.setOnClickListener(v -> abrirDetalleProducto("ntt24VtceMau2SK0z9Ga"));
        heladoDeVainilla.setOnClickListener(v -> abrirDetalleProducto("zgYyxoaI6O6N6OXkOKq7"));
    }

    private void abrirDetalleProducto (String productoId){
        Intent intent = new Intent(postresActivity.this, postresDetalle.class);
        intent.putExtra("productoId", productoId);
        startActivity(intent);
    }

}