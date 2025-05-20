package com.example.tfg;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.core.content.ContextCompat;
import androidx.core.splashscreen.SplashScreen;
import androidx.appcompat.app.AppCompatActivity;
import com.google.firebase.auth.FirebaseAuth;

/**
 * Actividad que permite al usuario iniciar sesión con su correo y contraseña,
 * utilizando Firebase Authentication. También permite recordar las credenciales
 * mediante SharedPreferences y navegar a la pantalla de registro.
 */
public class loginActivity extends AppCompatActivity {

    private EditText etCorreo, etContrasena;
    private Button registrarse;
    private Button IniciarSesion;
    private FirebaseAuth mAuth;
    private CheckBox checkRecordarme;
    private boolean isPasswordVisible = false;

    /**
     * Método llamado cuando se crea la actividad. Se encarga de inicializar los componentes,
     * gestionar la visibilidad de la contraseña, recuperar datos guardados, y definir los listeners.
     *
     * @param savedInstanceState Estado previamente guardado de la actividad (si existe).
     */

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        SplashScreen.installSplashScreen(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mAuth = FirebaseAuth.getInstance();

        etCorreo = findViewById(R.id.correo);
        etContrasena = findViewById(R.id.contrasena);
        IniciarSesion = findViewById(R.id.btnIniciarSesion);
        registrarse = findViewById(R.id.registrarse);
        checkRecordarme = findViewById(R.id.checkRecordarme);

        // Cargar correo y contraseña guardados
        SharedPreferences preferences = getSharedPreferences("loginPrefs", MODE_PRIVATE);
        boolean recordar = preferences.getBoolean("recordar", false);

        if (recordar) {
            etCorreo.setText(preferences.getString("correo", ""));
            etContrasena.setText(preferences.getString("contrasena", ""));
            checkRecordarme.setChecked(true);
        }

        // Mostrar/Ocultar contraseña con icono de ojo
        ImageView ojoContraseña = findViewById(R.id.ojoContraseña);

        ojoContraseña.setOnClickListener(v -> {
            isPasswordVisible = !isPasswordVisible;
            if (isPasswordVisible) {
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






        IniciarSesion.setOnClickListener(v -> iniciarSesion());

        registrarse.setOnClickListener(v -> {
            Intent intent = new Intent(loginActivity.this, registroActivity.class);
            startActivity(intent);
        });
    }


    @Override
    protected void onStart() {
        super.onStart();

    }


    /**
     * Inicia sesión con las credenciales proporcionadas por el usuario.
     * Si se marca la opción "Recordarme", guarda los datos en SharedPreferences.
     * Si la autenticación es exitosa, navega a {@link menuActivity}.
     */
    private void iniciarSesion() {
        String correo = etCorreo.getText().toString().trim();
        String contrasena = etContrasena.getText().toString().trim();

        if (TextUtils.isEmpty(correo) || TextUtils.isEmpty(contrasena)) {
            Toast.makeText(this, "Por favor, completa todos los campos", Toast.LENGTH_SHORT).show();
            return;
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(correo).matches()) {
            Toast.makeText(this, "Introduce un correo electrónico válido", Toast.LENGTH_SHORT).show();
            return;
        }

        mAuth.signInWithEmailAndPassword(correo, contrasena)
                .addOnCompleteListener(this, task -> {
                    if (task.isSuccessful()) {
                        // Guardar correo y contraseña en SharedPreferences
                        SharedPreferences preferences = getSharedPreferences("loginPrefs", MODE_PRIVATE);
                        SharedPreferences.Editor editor = preferences.edit();

                        if (checkRecordarme.isChecked()) {
                            editor.putString("correo", correo);
                            editor.putString("contrasena", contrasena);
                            editor.putBoolean("recordar", true);
                        } else {
                            editor.clear(); // Borra todo si no está marcado
                        }
                        editor.apply();

                        // Ir al menú
                        startActivity(new Intent(loginActivity.this, menuActivity.class));
                        finish(); // Cierra loginActivity
                    } else {
                        Toast.makeText(loginActivity.this, "Error en el inicio de sesión: " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
    }
}
