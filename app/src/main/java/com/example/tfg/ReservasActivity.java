package com.example.tfg;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class ReservasActivity extends AppCompatActivity {

    private Spinner spinnerComunidades;
    private String[] comunidades;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_reservas);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;


        });

        // Obtener referencia del Spinner
        spinnerComunidades = findViewById(R.id.spinnerComunidades);

        // Cargar el array de comunidades desde strings.xml
        comunidades = getResources().getStringArray(R.array.comunidades);

        // Configurar el adaptador
        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                this,
                R.layout.spinner_item, // Layout personalizado
                comunidades
        );
        //adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerComunidades.setAdapter(adapter);

        // Manejar selección del usuario
        spinnerComunidades.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String comunidadSeleccionada = comunidades[position];
                // Aquí puedes usar el valor seleccionado
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // Acción cuando no se selecciona nada
            }
        });
    }
}