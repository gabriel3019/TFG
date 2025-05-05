package com.example.tfg;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class parapequesActivity extends AppCompatActivity {

    private ImageButton buttonHome;
    private ImageView flecha;

    private ImageView goiko_kids_de_carne, goiko_kids_de_pollo;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_parapeques);

        buttonHome = findViewById(R.id.buttonHome);
        flecha = findViewById(R.id.flecha);

        goiko_kids_de_carne = findViewById(R.id.goiko_kids_de_carne);
        goiko_kids_de_pollo = findViewById(R.id.goiko_kids_de_pollo);

        // Establecer el OnClickListener para el botón
        buttonHome.setOnClickListener(v -> {
            // Crear un Intent para ir a la actividad del menú
            Intent intent = new Intent(parapequesActivity.this, menuActivity.class); // Asegúrate de que el nombre de la actividad sea correcto
            startActivity(intent); // Iniciar la actividad
        });

        flecha.setOnClickListener(v -> {
            Intent intent = new Intent(parapequesActivity.this, cartaActivity.class);
            startActivity(intent);
        });

        goiko_kids_de_carne.setOnClickListener(v -> abrirDetalleProducto("u1JNFZQpsS6qyl0Ao8li"));
        goiko_kids_de_pollo.setOnClickListener(v -> abrirDetalleProducto("SXQE3RtwPPKc2A1YfSml"));

    }

    private void abrirDetalleProducto (String productoId){
        Intent intent = new Intent(parapequesActivity.this, detalleParaPeques.class);
        intent.putExtra("productoId", productoId);
        startActivity(intent);
    }

}