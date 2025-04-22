package com.example.tfg;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class bebidasActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_bebidas);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        ImageView flecha = findViewById(R.id.flecha);
        flecha.setOnClickListener(v -> {
            Intent intent = new Intent(bebidasActivity.this, cartaActivity.class);
            startActivity(intent);
        });


        // 🔽 Aquí se pone la funcionalidad del botón
        ImageButton btnHome = findViewById(R.id.buttonHome);
        btnHome.setOnClickListener(v -> {
            Intent intent = new Intent(bebidasActivity.this, menuActivity.class);
            startActivity(intent);
        });
    }
}
