package com.example.tfg;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class perfilActivity extends AppCompatActivity {

    private EditText etNombre, etApellidos, etCorreo;
    private FirebaseAuth mAuth;
    private FirebaseFirestore db;

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
        etCorreo = findViewById(R.id.correo_electronico);

        cargarDatosUsuario();
    }

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
