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

public class saladActivity extends AppCompatActivity {

    private ImageButton buttonHome;
    private ImageView flecha;

    private ImageView green_day, ave_cesar;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_salad);

        buttonHome = findViewById(R.id.buttonHome);
        flecha = findViewById(R.id.flecha);

        green_day = findViewById(R.id.green_day);
        ave_cesar = findViewById(R.id.ave_cesar);

        // Establecer el OnClickListener para el botón
        buttonHome.setOnClickListener(v -> {
            // Crear un Intent para ir a la actividad del menú
            Intent intent = new Intent(saladActivity.this, menuActivity.class); // Asegúrate de que el nombre de la actividad sea correcto
            startActivity(intent); // Iniciar la actividad
        });

        flecha.setOnClickListener(v -> {
            Intent intent = new Intent(saladActivity.this, cartaActivity.class);
            startActivity(intent);
        });

        green_day.setOnClickListener(v -> abrirDetalleProducto("kFbKh9mOJdje7OjDjxgh"));
        ave_cesar.setOnClickListener(v -> abrirDetalleProducto("h4SKKUrDwMYzEePCkPRI"));
    }

    private void abrirDetalleProducto (String productoId){
        Intent intent = new Intent(saladActivity.this, detalleSalad.class);
        intent.putExtra("productoId", productoId);
        startActivity(intent);
    }

}