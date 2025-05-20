package com.example.tfg;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

/**
 * Actividad que permite al usuario elegir entre ver una lista o un mapa de restaurantes.
 * <p>
 * Ofrece botones para navegar a la lista de restaurantes, al mapa de restaurantes
 * y para volver al menú principal.
 * También aplica configuraciones para mostrar contenido en modo Edge to Edge
 * respetando los Insets del sistema.
 * </p>
 */
public class elegirRestaurantesActivity extends AppCompatActivity {

    private Button btnLista;
    private Button btnMapa;
    private ImageButton btnHome;


    /**
     * Método llamado al crear la actividad.
     * <p>
     * Inicializa la vista, habilita el modo Edge to Edge y configura los
     * botones para navegar a las actividades correspondientes.
     * </p>
     *
     * @param savedInstanceState Estado previamente guardado de la actividad.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_elegir_restaurantes);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        btnLista = findViewById(R.id.buttonListaRest);
        btnMapa = findViewById(R.id.buttonMapaRest);
        btnHome = findViewById(R.id.buttonHome);


        btnLista.setOnClickListener(v -> {
            Intent intent = new Intent(elegirRestaurantesActivity.this, restaurantesListaActivity.class);
            startActivity(intent);
        });

        btnMapa.setOnClickListener(v -> {
            Intent intent = new Intent(elegirRestaurantesActivity.this, restaurantesMapaActivity.class);
            startActivity(intent);
        });

        btnHome.setOnClickListener(v -> {
            Intent intent = new Intent(elegirRestaurantesActivity.this, menuActivity.class);
            startActivity(intent);
        });
    }
}