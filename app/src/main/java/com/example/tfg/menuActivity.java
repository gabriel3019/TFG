package com.example.tfg;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

public class menuActivity extends AppCompatActivity {

    private ImageButton btnPerfil;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        btnPerfil = findViewById(R.id.Perfil);

        btnPerfil.setOnClickListener(v -> {
            Intent intent = new Intent(menuActivity.this, perfilActivity.class);
            startActivity(intent);
        });
    }
}
