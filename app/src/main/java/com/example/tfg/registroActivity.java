package com.example.tfg;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import java.util.HashMap;
import java.util.Map;

public class registroActivity extends AppCompatActivity {

    private TextView etNombre, etApellidos, correo_electronico, etContrasena , repetir_contrasena, numero_telefono, fecha_nacimiento;
    private Button btnRegistrarse;
    private FirebaseAuth mAuth;
    private FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        // Inicializar Firebase Auth y Firestore
        mAuth = FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance();

        // Referencias a los elementos del layout
        etNombre = findViewById(R.id.nombre);
        etApellidos = findViewById(R.id.apellidos);
        correo_electronico = findViewById(R.id.correo_electronico);
        etContrasena = findViewById(R.id.contrasena);
        repetir_contrasena = findViewById(R.id.repetir_contrasena);
        numero_telefono = findViewById(R.id.numero_telefono);
        fecha_nacimiento = findViewById(R.id.fecha_nacimiento);
        btnRegistrarse = findViewById(R.id.btnRegistrarse);

        btnRegistrarse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registrarUsuario();
            }
        });
    }

    private void registrarUsuario() {
        String nombre = etNombre.getText().toString().trim();
        String apellidos = etApellidos.getText().toString().trim();
        String correo = correo_electronico.getText().toString().trim();
        String contrasena = etContrasena.getText().toString().trim();
        String repetirContrasena = repetir_contrasena.getText().toString().trim();
        String telefono = numero_telefono.getText().toString().trim();
        String fechaNacimiento = fecha_nacimiento.getText().toString().trim();

        // Validaciones
        if (TextUtils.isEmpty(nombre) || TextUtils.isEmpty(apellidos) || TextUtils.isEmpty(correo) ||
                TextUtils.isEmpty(contrasena) || TextUtils.isEmpty(repetirContrasena) ||
                TextUtils.isEmpty(telefono) || TextUtils.isEmpty(fechaNacimiento)) {
            Toast.makeText(this, "Por favor, completa todos los campos", Toast.LENGTH_SHORT).show();
            return;
        }

        if (!contrasena.equals(repetirContrasena)) {
            Toast.makeText(this, "Las contraseñas no coinciden", Toast.LENGTH_SHORT).show();
            return;
        }

        // Registrar en Firebase Authentication
        mAuth.createUserWithEmailAndPassword(correo, contrasena)
                .addOnCompleteListener(this, task -> {
                    if (task.isSuccessful()) {
                        String userId = mAuth.getCurrentUser().getUid();

                        // Guardar datos en Firestore
                        Map<String, Object> usuario = new HashMap<>();
                        usuario.put("nombre", nombre);
                        usuario.put("apellidos", apellidos);
                        usuario.put("correo_electronico", correo);
                        usuario.put("contrasena", contrasena);
                        usuario.put("numero_telefono", telefono);
                        usuario.put("fecha_nacimiento", fechaNacimiento);

                        db.collection("Usuarios").document(userId)
                                .set(usuario)
                                .addOnSuccessListener(aVoid -> {
                                    Toast.makeText(registroActivity.this, "Registro exitoso", Toast.LENGTH_SHORT).show();
                                    startActivity(new Intent(registroActivity.this, menuActivity.class));
                                    finish();
                                })
                                .addOnFailureListener(e -> Log.e("Firebase", "Error al guardar usuario", e));
                    } else {
                        Toast.makeText(registroActivity.this, "Error en el registro: " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
    }
}
