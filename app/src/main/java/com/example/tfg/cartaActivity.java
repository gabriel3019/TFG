package com.example.tfg;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class cartaActivity extends AppCompatActivity {

    private ImageButton buttonHome;
    private ImageView entrantes, hamburgesas, smashburgers, chicken_lovers, para_peques, salad, postres, bebidas;


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

        // para_peques.setOnClickListener(v -> {
//     Intent intent = new Intent(cartaActivity.this, parapequesActivity.class);
//     startActivity(intent);
// });

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