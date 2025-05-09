package com.example.tfg;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.util.Patterns;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ImageView;
import android.widget.EditText;
import android.app.DatePickerDialog;
import java.util.Calendar;
import java.util.Locale;



import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

public class registroActivity extends AppCompatActivity {

    private EditText etContrasena, repetir_contrasena;
    private TextView etNombre, etApellidos, correo_electronico, numero_telefono, fecha_nacimiento;
    private Button btnRegistrarse;
    private FirebaseAuth mAuth;
    private FirebaseFirestore db;

    // Expresión regular para validar la contraseña
    private static final Pattern PASSWORD_PATTERN =
            Pattern.compile("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=!]).{8,}$");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        // Inicializar Firebase
        mAuth = FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance();

        // Referencias de campos
        etNombre = findViewById(R.id.nombre);
        etApellidos = findViewById(R.id.apellidos);
        correo_electronico = findViewById(R.id.correo);
        etContrasena = findViewById(R.id.contrasena);
        repetir_contrasena = findViewById(R.id.repetir_contrasena);
        numero_telefono = findViewById(R.id.numero_telefono);
        fecha_nacimiento = findViewById(R.id.fecha_nacimiento);
        fecha_nacimiento.setFocusable(false);
        fecha_nacimiento.setClickable(true);

        fecha_nacimiento.setOnClickListener(v -> {
            Calendar calendar = Calendar.getInstance();
            int year = calendar.get(Calendar.YEAR);
            int month = calendar.get(Calendar.MONTH);
            int day = calendar.get(Calendar.DAY_OF_MONTH);

            DatePickerDialog datePickerDialog = new DatePickerDialog(
                    registroActivity.this,
                    (view, year1, month1, dayOfMonth) -> {
                        String fecha = String.format(Locale.getDefault(), "%02d/%02d/%04d", dayOfMonth, month1 + 1, year1);
                        fecha_nacimiento.setText(fecha);
                    },
                    year, month, day
            );
            datePickerDialog.show();
        });

        btnRegistrarse = findViewById(R.id.btnRegistrarse);
        Button btnIrALogin = findViewById(R.id.btnIrALogin);

        // ---- OJO CONTRASEÑA ----
        ImageView ojoContraseña = findViewById(R.id.ojoContraseña);
        final boolean[] isPasswordVisible = {false};
        ojoContraseña.setOnClickListener(v -> {
            isPasswordVisible[0] = !isPasswordVisible[0];
            if (isPasswordVisible[0]) {
                etContrasena.setInputType(android.text.InputType.TYPE_CLASS_TEXT |
                        android.text.InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                ojoContraseña.setImageResource(R.drawable.eye_open);
            } else {
                etContrasena.setInputType(android.text.InputType.TYPE_CLASS_TEXT |
                        android.text.InputType.TYPE_TEXT_VARIATION_PASSWORD);
                ojoContraseña.setImageResource(R.drawable.eye_closed);
            }
            etContrasena.setSelection(etContrasena.getText().length());
        });

        // ---- OJO REPETIR CONTRASEÑA ----
        ImageView ojoRepetir = findViewById(R.id.ojoRepetirContrasena);
        final boolean[] isRepeatVisible = {false};
        ojoRepetir.setOnClickListener(v -> {
            isRepeatVisible[0] = !isRepeatVisible[0];
            if (isRepeatVisible[0]) {
                repetir_contrasena.setInputType(android.text.InputType.TYPE_CLASS_TEXT |
                        android.text.InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                ojoRepetir.setImageResource(R.drawable.eye_open);
            } else {
                repetir_contrasena.setInputType(android.text.InputType.TYPE_CLASS_TEXT |
                        android.text.InputType.TYPE_TEXT_VARIATION_PASSWORD);
                ojoRepetir.setImageResource(R.drawable.eye_closed);
            }
            repetir_contrasena.setSelection(repetir_contrasena.getText().length());
        });

        // Botón para volver al login
        btnIrALogin.setOnClickListener(v -> {
            Intent intent = new Intent(registroActivity.this, loginActivity.class);
            startActivity(intent);
            finish();
        });

        // Botón de registro
        btnRegistrarse.setOnClickListener(v -> registrarUsuario());
    }


    private void registrarUsuario() {
        String nombre = etNombre.getText().toString().trim();
        String apellidos = etApellidos.getText().toString().trim();
        String correo = correo_electronico.getText().toString().trim();
        String contrasena = etContrasena.getText().toString().trim();
        String repetirContrasena = repetir_contrasena.getText().toString().trim();
        String telefono = numero_telefono.getText().toString().trim();
        String fechaNacimiento = fecha_nacimiento.getText().toString().trim();

        // Validaciones generales
        if (TextUtils.isEmpty(nombre) || TextUtils.isEmpty(apellidos) || TextUtils.isEmpty(correo) ||
                TextUtils.isEmpty(contrasena) || TextUtils.isEmpty(repetirContrasena) ||
                TextUtils.isEmpty(telefono) || TextUtils.isEmpty(fechaNacimiento)) {
            Toast.makeText(this, "Por favor, completa todos los campos", Toast.LENGTH_SHORT).show();
            return;
        }

        // Validar formato de email
        if (!Patterns.EMAIL_ADDRESS.matcher(correo).matches()) {
            Toast.makeText(this, "Introduce un correo electrónico válido", Toast.LENGTH_SHORT).show();
            return;
        }

        // Validar formato de teléfono (solo números y al menos 9 dígitos)
        if (!telefono.matches("\\d{9,}")) {
            Toast.makeText(this, "Introduce un número de teléfono válido (mínimo 9 dígitos)", Toast.LENGTH_SHORT).show();
            return;
        }

        // Validar contraseña
        if (!PASSWORD_PATTERN.matcher(contrasena).matches()) {
            Toast.makeText(this, "La contraseña debe tener al menos 8 caracteres, una mayúscula, una minúscula, un número y un carácter especial (@#$%^&+=!)", Toast.LENGTH_LONG).show();
            return;
        }

        // Validar que las contraseñas coincidan
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
