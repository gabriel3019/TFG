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

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_entrantes);

        buttonHome = findViewById(R.id.buttonHome);
        flecha = findViewById(R.id.flecha);

        // Establecer el OnClickListener para el botón
        buttonHome.setOnClickListener(v -> {
            // Crear un Intent para ir a la actividad del menú
            Intent intent = new Intent(entrantesActivity.this, menuActivity.class); // Asegúrate de que el nombre de la actividad sea correcto
            startActivity(intent); // Iniciar la actividad
        });

        flecha.setOnClickListener(v -> {
            Intent intent = new Intent(entrantesActivity.this, cartaActivity.class);
            startActivity(intent);
        });

    }

}