package com.example.tfg;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

/**
 * Actividad que muestra el listado de hamburguesas tipo Smash disponibles.
 * Permite acceder al detalle de cada producto y navegar al menú principal o a la carta general.
 */
public class smashburgerActivity extends AppCompatActivity {

    private ImageButton buttonHome;
    private ImageView flecha;

    private ImageView hattrick, mas_s_mash, don_vito, smashic;

    /**
     * Método llamado al iniciar la actividad. Configura la interfaz y los listeners.
     *
     * @param savedInstanceState Estado guardado previamente (si lo hubiera).
     */
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_smashburger);

        buttonHome = findViewById(R.id.buttonHome);
        flecha = findViewById(R.id.flecha);

        hattrick = findViewById(R.id.hattrick);
        mas_s_mash = findViewById(R.id.mas_s_mash);
        don_vito = findViewById(R.id.don_vito);
        smashic = findViewById(R.id.smashic);

        // Establecer el OnClickListener para el botón
        buttonHome.setOnClickListener(v -> {
            // Crear un Intent para ir a la actividad del menú
            Intent intent = new Intent(smashburgerActivity.this, menuActivity.class); // Asegúrate de que el nombre de la actividad sea correcto
            startActivity(intent); // Iniciar la actividad
        });

        flecha.setOnClickListener(v -> {
            Intent intent = new Intent(smashburgerActivity.this, cartaActivity.class);
            startActivity(intent);
        });

        // Listeners de productos
        hattrick.setOnClickListener(v -> abrirDetalleProducto("wSkdMvTm95ogliUA08xB"));
        mas_s_mash.setOnClickListener(v -> abrirDetalleProducto("dPmu9s7t38uud7g6T3H8"));
        don_vito.setOnClickListener(v -> abrirDetalleProducto("i7ffBs4AXuJ2YjnRLWGa"));
        smashic.setOnClickListener(v -> abrirDetalleProducto("qWwVc5g6lwheydD0IfeF"));
    }

    /**
     * Abre la actividad de detalle para una Smash Burger específica.
     *
     * @param productoId ID del producto a mostrar en la pantalla de detalles.
     */
    private void abrirDetalleProducto(String productoId) {
        Intent intent = new Intent(smashburgerActivity.this, detalleSmashBurgers.class);
        intent.putExtra("productoId", productoId);
        startActivity(intent);
    }

}



