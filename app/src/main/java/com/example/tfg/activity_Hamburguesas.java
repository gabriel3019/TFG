package com.example.tfg;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageButton;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class activity_Hamburguesas extends AppCompatActivity {

    private ImageButton buttonHome;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_hamburguesas);

        buttonHome = findViewById(R.id.buttonHome);

        // Establecer el OnClickListener para el botón
        buttonHome.setOnClickListener(v -> {
            // Crear un Intent para ir a la actividad del menú
            Intent intent = new Intent(activity_Hamburguesas.this, menuActivity.class); // Asegúrate de que el nombre de la actividad sea correcto
            startActivity(intent); // Iniciar la actividad
        });

    }

}