package com.example.tfg;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

/**
 * Actividad que muestra la carta del restaurante Goiko.
 * Contiene accesos visuales a las distintas categorías de productos del menú,
 * y permite la navegación hacia otras pantallas específicas.
 */
public class cartaActivity extends AppCompatActivity {

    private ImageButton buttonHome;
    private ImageView entrantes, hamburgesas, smashburgers, chicken_lovers, para_peques, salad, postres, bebidas;

    /**
     * Método llamado cuando se crea la actividad.
     * Inicializa los componentes visuales y configura la navegación por categorías.
     *
     * @param savedInstanceState Estado previamente guardado de la actividad (si existe).
     */
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_carta);

       buttonHome = findViewById(R.id.buttonHome);
       entrantes = findViewById(R.id.entrantes);
       hamburgesas = findViewById(R.id.hamburguesas);
       smashburgers = findViewById(R.id.smashburgers);
       chicken_lovers = findViewById(R.id.chicken_lovers);
       para_peques = findViewById(R.id.para_peques);
       salad = findViewById(R.id.salad);
       postres = findViewById(R.id.postres);
       bebidas = findViewById(R.id.bebidas);

       entrantes.setOnClickListener(v -> {
           Intent intent = new Intent(cartaActivity.this, entrantesActivity.class);
           startActivity(intent);
       });

       hamburgesas.setOnClickListener(v -> {
           Intent intent = new Intent(cartaActivity.this, activity_Hamburguesas.class);
           startActivity(intent);
       });

       smashburgers.setOnClickListener(v -> {
            Intent intent = new Intent(cartaActivity.this, smashburgerActivity.class);
            startActivity(intent);
       });

       chicken_lovers.setOnClickListener(v -> {
            Intent intent = new Intent(cartaActivity.this, chickenloverActivity.class);
            startActivity(intent);
       });

       para_peques.setOnClickListener(v -> {
            Intent intent = new Intent(cartaActivity.this, parapequesActivity.class);
            startActivity(intent);
       });

       salad.setOnClickListener(v -> {
            Intent intent = new Intent(cartaActivity.this, saladActivity.class);
            startActivity(intent);
       });

       postres.setOnClickListener(v -> {
            Intent intent = new Intent(cartaActivity.this, postresActivity.class);
            startActivity(intent);
       });

       bebidas.setOnClickListener(v -> {
            Intent intent = new Intent(cartaActivity.this, bebidasActivity.class);
            startActivity(intent);
       });




        // Establecer el OnClickListener para el botón
        buttonHome.setOnClickListener(v -> {
            // Crear un Intent para ir a la actividad del menú
            Intent intent = new Intent(cartaActivity.this, menuActivity.class); // Asegúrate de que el nombre de la actividad sea correcto
            startActivity(intent); // Iniciar la actividad
        });

    }

}