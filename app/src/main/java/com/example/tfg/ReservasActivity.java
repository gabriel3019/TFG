package com.example.tfg;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class ReservasActivity extends AppCompatActivity {

    private static final int LOCATION_PERMISSION_REQUEST_CODE = 1;
    private FusedLocationProviderClient fusedLocationClient;
    private Location ubicacionActual;
    private Spinner spinnerProvincias, spinnerRestaurantes, spinnerHoras, spinnerPersonas;
    private ArrayList<Restaurante> listaRestaurantes = new ArrayList<>();
    private String[] provincias;
    private String[] personas;
    private String[] horas;
    private String provinciaSeleccionada;
    private String restauranteSeleccionado;
    private String personasSeleccionada;
    private String horaSeleccionada;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_reservas);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets sys = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(sys.left, sys.top, sys.right, sys.bottom);
            return insets;
        });

        // Ubicación FAKE para testing sin GPS real
        Location ubicacionFake = new Location("mock");
        ubicacionFake.setLatitude(40.43376);
        ubicacionFake.setLongitude(-3.63169);

        // Referencias UI
        spinnerProvincias = findViewById(R.id.spinnerProvincias);
        spinnerRestaurantes = findViewById(R.id.spinnerRestaurantes);
        spinnerHoras = findViewById(R.id.spinnerHora);
        spinnerPersonas = findViewById(R.id.spinnerPersonas);

        // Adapter de provincias
        provincias = getResources().getStringArray(R.array.provincias);
        ArrayAdapter<String> adapterProv = new ArrayAdapter<>(
                this,
                android.R.layout.simple_spinner_item,
                provincias
        );
        adapterProv.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerProvincias.setAdapter(adapterProv);

        // Adapter de personas
        personas = getResources().getStringArray(R.array.personas);
        ArrayAdapter<String> adapterPers = new ArrayAdapter<>(
                this,
                android.R.layout.simple_spinner_item,
                personas
        );
        adapterPers.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerPersonas.setAdapter(adapterPers);

        // Adapter de horas
        horas = getResources().getStringArray(R.array.horas);
        ArrayAdapter<String> adapterHoras = new ArrayAdapter<>(
                this,
                android.R.layout.simple_spinner_item,
                horas
        );
        adapterHoras.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerHoras.setAdapter(adapterHoras);

        // Al seleccionar provincia, recarga restaurantes
        spinnerProvincias.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
                provinciaSeleccionada = provincias[pos];
                if (pos > 0) {
                    cargarRestaurantes(provinciaSeleccionada, ubicacionFake);
                } else {
                    // Limpiar spinnerRestaurantes si no hay provincia
                    List<String> vacio = new ArrayList<>();
                    vacio.add("— No hay restaurantes —");
                    ArrayAdapter<String> a = new ArrayAdapter<>(
                            ReservasActivity.this,
                            android.R.layout.simple_spinner_item,
                            vacio
                    );
                    a.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spinnerRestaurantes.setAdapter(a);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        spinnerPersonas.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
                if (pos > 0) {
                    personasSeleccionada = personas[pos];
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        spinnerHoras.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
                if (pos > 0) {
                    horaSeleccionada = horas[pos];
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        // Inicializar ubicación real
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this);
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED) {
            obtenerUbicacion();
        } else {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                    LOCATION_PERMISSION_REQUEST_CODE);
        }
    }

    @Override
    public void onRequestPermissionsResult(int code, String[] perms, int[] grants) {
        super.onRequestPermissionsResult(code, perms, grants);
        if (code == LOCATION_PERMISSION_REQUEST_CODE
                && grants.length > 0 && grants[0] == PackageManager.PERMISSION_GRANTED) {
            obtenerUbicacion();
        }
    }

    private void obtenerUbicacion() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) return;
        fusedLocationClient.getLastLocation()
                .addOnSuccessListener(this, new OnSuccessListener<Location>() {
                    @Override
                    public void onSuccess(Location loc) {
                        if (loc != null) ubicacionActual = loc;
                    }
                });
    }

    // Carga restaurantes y actualiza spinnerRestaurantes
    private void cargarRestaurantes(String provincia, Location ubicacionFake) {
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        db.collection("Restaurantes")
                .whereEqualTo("provincia", provincia)
                .get()
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        listaRestaurantes.clear();
                        for (DocumentSnapshot doc : task.getResult()) {
                            String name = doc.getString("nombre");
                            String addr = doc.getString("direccion");
                            double tel = doc.getDouble("telefono");
                            double lat = doc.getDouble("latitud");
                            double lng = doc.getDouble("longitud");
                            if (lat != 0.0 && lng != 0.0) {
                                Location origen = (ubicacionActual != null) ? ubicacionActual : ubicacionFake;
                                double dist = calcularDistanciaKM(
                                        origen.getLatitude(),
                                        origen.getLongitude(),
                                        lat, lng
                                );
                                listaRestaurantes.add(
                                        new Restaurante(name, addr, provincia, (int) tel, lat, lng, dist)
                                );
                            }
                        }
                        Collections.sort(listaRestaurantes,
                                Comparator.comparingDouble(Restaurante::getDistance));

                        // Rellenar spinnerRestaurantes y capturar selección
                        List<String> nombres = new ArrayList<>();
                        for (Restaurante r : listaRestaurantes) nombres.add(r.getName());
                        if (nombres.isEmpty()) nombres.add("— No hay restaurantes —");

                        ArrayAdapter<String> adapterRest = new ArrayAdapter<>(
                                ReservasActivity.this,
                                android.R.layout.simple_spinner_item,
                                nombres
                        );
                        adapterRest.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        spinnerRestaurantes.setAdapter(adapterRest);

                        spinnerRestaurantes.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                            @Override
                            public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
                                restauranteSeleccionado = parent.getItemAtPosition(pos).toString();
                            }

                            @Override
                            public void onNothingSelected(AdapterView<?> parent) {
                            }
                        });
                    } else {
                        Toast.makeText(this,
                                "Error cargando restaurantes", Toast.LENGTH_SHORT).show();
                    }
                });
    }

    private double calcularDistanciaKM(
            double lat1, double lng1, double lat2, double lng2) {
        Location a = new Location("A"), b = new Location("B");
        a.setLatitude(lat1);
        a.setLongitude(lng1);
        b.setLatitude(lat2);
        b.setLongitude(lng2);
        return a.distanceTo(b) / 1000;
    }
}
