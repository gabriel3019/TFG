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

public class chickenloverActivity extends AppCompatActivity {

    private ImageButton buttonHome;
    private ImageView flecha;

    private ImageView goikotenders, muslona, kiki;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_chickenlover);

        buttonHome = findViewById(R.id.buttonHome);
        flecha = findViewById(R.id.flecha);

        goikotenders = findViewById(R.id.goikotenders);
        muslona = findViewById(R.id.muslona);
        kiki = findViewById(R.id.kiki);

        // Establecer el OnClickListener para el botón
        buttonHome.setOnClickListener(v -> {
            // Crear un Intent para ir a la actividad del menú
            Intent intent = new Intent(chickenloverActivity.this, menuActivity.class); // Asegúrate de que el nombre de la actividad sea correcto
            startActivity(intent); // Iniciar la actividad
        });

        flecha.setOnClickListener(v -> {
            Intent intent = new Intent(chickenloverActivity.this, cartaActivity.class);
            startActivity(intent);
        });

        goikotenders.setOnClickListener(v -> abrirDetalleProducto("A7EgcOkFcd25qklZcFuU"));
        muslona.setOnClickListener(v -> abrirDetalleProducto("IyULpescxZh45AGdAvmP"));
        kiki.setOnClickListener(v -> abrirDetalleProducto("DkSPZu4UBNl0xmFJpg96"));
    }
        private void abrirDetalleProducto (String productoId){
            Intent intent = new Intent(chickenloverActivity.this, detalleChickenLover.class);
            intent.putExtra("productoId", productoId);
            startActivity(intent);
        }


    }
