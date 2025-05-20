package com.example.tfg;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

/**
 * Actividad que muestra los datos del perfil del usuario autenticado.
 * Permite visualizar el nombre, apellidos y correo electrónico, obtenidos desde Firestore.
 */
public class perfilActivity extends AppCompatActivity {

    private EditText etNombre, etApellidos, etCorreo;
    private Button btnMenu;
    private FirebaseAuth mAuth;
    private FirebaseFirestore db;

    /**
     * Método que se llama al crear la actividad.
     * Inicializa los componentes, Firebase y carga los datos del usuario.
     *
     * @param savedInstanceState Estado guardado de la actividad, si existe.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil); // Asegúrate de tener este layout

        // Inicializar Firebase
        mAuth = FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance();

        // Referencias a los EditText del perfil
        etNombre = findViewById(R.id.nombre);
        etApellidos = findViewById(R.id.apellidos);
        etCorreo = findViewById(R.id.correo);
        btnMenu = findViewById(R.id.buttonHome);

        // Establecer el OnClickListener para el botón
        btnMenu.setOnClickListener(v -> {
            // Crear un Intent para ir a la actividad del menú
            Intent intent = new Intent(perfilActivity.this, menuActivity.class); // Asegúrate de que el nombre de la actividad sea correcto
            startActivity(intent); // Iniciar la actividad
        });


        cargarDatosUsuario();
    }

    /**
     * Método que obtiene los datos del usuario actual desde Firestore
     * y los muestra en los campos de texto correspondientes.
     */
    private void cargarDatosUsuario() {
        String userId = mAuth.getCurrentUser().getUid();

        db.collection("Usuarios").document(userId)
                .get()
                .addOnSuccessListener(documentSnapshot -> {
                    if (documentSnapshot.exists()) {
                        String nombre = documentSnapshot.getString("nombre");
                        String apellidos = documentSnapshot.getString("apellidos");
                        String correo = documentSnapshot.getString("correo_electronico");

                        etNombre.setText(nombre);
                        etApellidos.setText(apellidos);
                        etCorreo.setText(correo);
                    } else {
                        Toast.makeText(this, "No se encontraron los datos del usuario", Toast.LENGTH_SHORT).show();
                    }
                })
                .addOnFailureListener(e ->
                        Toast.makeText(this, "Error al cargar los datos: " + e.getMessage(), Toast.LENGTH_SHORT).show());
    }
}
