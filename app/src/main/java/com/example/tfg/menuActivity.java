package com.example.tfg;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class menuActivity extends AppCompatActivity {

    private ImageView Perfil;

    private Button btnCarta;
    private Button btnRestaurantes;

    private Button btnBebidas;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        Perfil = findViewById(R.id.perfil);
        btnCarta = findViewById(R.id.btnCarta);
        btnRestaurantes = findViewById(R.id.btnRestaurantes);
        btnBebidas = findViewById(R.id.btnBebidas);


        Perfil.setOnClickListener(v -> {
            Intent intent = new Intent(menuActivity.this, perfilActivity.class);
            startActivity(intent);
        });

        btnCarta.setOnClickListener(v -> {
            Intent intent = new Intent(menuActivity.this, cartaActivity.class);
            startActivity(intent);
        });

        btnRestaurantes.setOnClickListener(v -> {
            Intent intent = new Intent(menuActivity.this, elegirRestaurantesActivity.class);
            startActivity(intent);
        });

        btnBebidas.setOnClickListener( v -> {
            Intent intent = new Intent(menuActivity.this, bebidasActivity.class);
            startActivity(intent);
        } );
    }


}
