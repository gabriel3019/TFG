package com.example.tfg;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

/**
 * Muestra la sección “Hamburguesas” de la carta y
 * permite navegar al detalle de cada producto.
 *
 * <p><b>Navegación:</b></p>
 * <ul>
 *   <li><em>buttonHome</em> &nbsp;→&nbsp; {@link menuActivity}</li>
 *   <li><em>flecha</em> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;→&nbsp; {@link cartaActivity}</li>
 *   <li>Miniaturas de hamburguesas &nbsp;→&nbsp; {@link detallesHamburguesasActivity}</li>
 * </ul>
 *
 */
public class activity_Hamburguesas extends AppCompatActivity {

    /** Botón que devuelve al menú principal. */
    private ImageButton buttonHome;

    /** Flecha para volver a la pantalla de carta completa. */
    private ImageView flecha;

    // ───────────────────────────────────────────────────────────────────────────
    // Thumbnails de hamburguesas (cada una abre su detalle al pulsar)
    // ───────────────────────────────────────────────────────────────────────────
    private ImageView theBeast, kevinChingona, kevinBacon, kevinCostner,
            bombaSexy, mTreinta, pigma, retro, kikiller, moza, greenchofa;

    /**
     * Punto de entrada del ciclo de vida de la actividad.
     *
     * @param savedInstanceState estado previamente guardado de la interfaz, o
     *                           {@code null} si se crea por primera vez.
     */
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);                 // Layout a pantalla completa
        setContentView(R.layout.activity_hamburguesas);

        // ─── Enlaza vistas con el XML ───
        buttonHome     = findViewById(R.id.buttonHome);
        flecha         = findViewById(R.id.flecha);

        theBeast       = findViewById(R.id.theBeast);
        kevinChingona  = findViewById(R.id.kevinChingona);
        kevinBacon     = findViewById(R.id.kevinBacon);
        kevinCostner   = findViewById(R.id.kevinCostner);
        bombaSexy      = findViewById(R.id.bombaSexy);
        mTreinta       = findViewById(R.id.mTreinta);
        pigma          = findViewById(R.id.pigma);
        retro          = findViewById(R.id.retro);
        kikiller       = findViewById(R.id.kikiller);
        moza           = findViewById(R.id.moza);
        greenchofa     = findViewById(R.id.greenchofa);

        // ─── Acciones de navegación ───
        buttonHome.setOnClickListener(v ->
                startActivity(new Intent(this, menuActivity.class)));

        flecha.setOnClickListener(v ->
                startActivity(new Intent(this, cartaActivity.class)));

        // ─── Listeners de cada burger ───
        theBeast      .setOnClickListener(v -> abrirDetalleProducto("7Ne71jl0zeYML9c82syo"));
        kevinChingona .setOnClickListener(v -> abrirDetalleProducto("SIcRTnCFvKhosMUNAUTd"));
        kevinBacon    .setOnClickListener(v -> abrirDetalleProducto("Q5U9ARGlfKehAVbOvKFu"));
        kevinCostner  .setOnClickListener(v -> abrirDetalleProducto("kZ58W5cpQgiOfeoGqw6R"));
        bombaSexy     .setOnClickListener(v -> abrirDetalleProducto("bxEBA5dnEpBgj3umsQLV"));
        mTreinta      .setOnClickListener(v -> abrirDetalleProducto("xVDswLfGv6d0YIkFGbJ1"));
        pigma         .setOnClickListener(v -> abrirDetalleProducto("b58dgzXLBVrlKDYHjTIC"));
        retro         .setOnClickListener(v -> abrirDetalleProducto("CKUhxaEwJmnHq1zms56f"));
        kikiller      .setOnClickListener(v -> abrirDetalleProducto("6TwyRUUz80jyRElBfdmD"));
        moza          .setOnClickListener(v -> abrirDetalleProducto("ED47DRhpteGtOAiLlcR9"));
        greenchofa    .setOnClickListener(v -> abrirDetalleProducto("4vOMsxPBDGzNESbm4ao3"));
    }

    /**
     * Lanza la pantalla de detalles para la hamburguesa seleccionada.
     *
     * @param productoId ID del documento / registro en la base de datos que
     *                   identifica la hamburguesa.
     */
    private void abrirDetalleProducto(@NonNull String productoId) {
        Intent intent = new Intent(this, detallesHamburguesasActivity.class);
        intent.putExtra("productoId", productoId);
        startActivity(intent);
    }
}
