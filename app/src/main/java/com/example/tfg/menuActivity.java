package com.example.tfg;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

/**
 * Clase {@code menuActivity} que representa la pantalla principal del menú
 * donde el usuario puede acceder a diferentes secciones de la aplicación.
 *
 * Cada botón en esta actividad redirige a una pantalla diferente de la app
 * (carta, bebidas, restaurantes, alérgenos, reservas, pedidos, perfil).
 *
 */
public class menuActivity extends AppCompatActivity {

    private ImageView Perfil;

    private Button btnCarta;
    private Button btnRestaurantes;

    private Button btnBebidas;

    private Button btnAlergenos;

    private Button btnReservas;

    private Button btnPedidos;

    private Button btnInicio;
    /**
     * Método llamado al crear la actividad. Se inicializan todos los componentes
     * y se configuran los listeners para los botones.
     *
     * @param savedInstanceState Estado anterior de la actividad, si existe.
     */
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        Perfil = findViewById(R.id.perfil);
        btnCarta = findViewById(R.id.btnCarta);
        btnRestaurantes = findViewById(R.id.btnRestaurantes);
        btnBebidas = findViewById(R.id.btnBebidas);
        btnAlergenos = findViewById(R.id.btnAlergenos);
        btnReservas = findViewById(R.id.btnReservas);
        btnPedidos = findViewById(R.id.btnPedidos);
        btnInicio = findViewById(R.id.btnInicio);



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

        btnAlergenos.setOnClickListener(v -> {
            Intent intent = new Intent(menuActivity.this, alergenosActivity.class);
            startActivity(intent);
        });

        btnReservas.setOnClickListener(v -> {
            Intent intent = new Intent(menuActivity.this, ReservasActivity.class);
            startActivity(intent);
        });

        btnPedidos.setOnClickListener(v -> {
            Intent intent = new Intent(menuActivity.this, PedidosActivity.class);
            startActivity(intent);
        });

        btnInicio.setOnClickListener(v -> {
            Intent intent = new Intent(menuActivity.this, loginActivity.class);
            startActivity(intent);
        });
    }


}
