package com.example.tfg;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class cartaActivity extends AppCompatActivity {

    private Button btnMenu;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_carta);

       btnMenu = findViewById(R.id.btnMenu);

        // Establecer el OnClickListener para el botón
        btnMenu.setOnClickListener(v -> {
            // Crear un Intent para ir a la actividad del menú
            Intent intent = new Intent(cartaActivity.this, menuActivity.class); // Asegúrate de que el nombre de la actividad sea correcto
            startActivity(intent); // Iniciar la actividad
        });

    }

}