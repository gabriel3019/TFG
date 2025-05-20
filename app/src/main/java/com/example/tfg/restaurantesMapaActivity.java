package com.example.tfg;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 * Actividad que muestra un mapa con la ubicación del usuario y los restaurantes cercanos.
 * Utiliza Firestore para obtener los datos de los restaurantes y Google Maps para mostrarlos.
 */
public class restaurantesMapaActivity extends AppCompatActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private ImageButton btnHome;
    private static final int LOCATION_PERMISSION_REQUEST_CODE = 1;
    private FusedLocationProviderClient fusedLocationClient;
    private Location ubicacionActual;
    private ArrayList<Restaurante> listaRestaurantes = new ArrayList<>();

    /**
     * Método llamado al crear la actividad.
     *
     * @param savedInstanceState Estado previamente guardado de la actividad.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_restaurantes_mapa);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        btnHome = findViewById(R.id.buttonHome);
        btnHome.setOnClickListener(v -> {
            Intent intent = new Intent(restaurantesMapaActivity.this, menuActivity.class);
            startActivity(intent);
        });

        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this);

        // Verificar permisos y cargar mapa
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            obtenerUbicacionYConfigurarMapa();
        } else {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, LOCATION_PERMISSION_REQUEST_CODE);
        }

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        if (mapFragment != null) {
            mapFragment.getMapAsync(this);
        }
    }

    /**
     * Obtiene la ubicación del dispositivo y configura el mapa.
     */
    private void obtenerUbicacionYConfigurarMapa() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        fusedLocationClient.getLastLocation().addOnSuccessListener(location -> {
            if (location != null) {
                ubicacionActual = location;
                if (mMap != null) {
                    configurarMapaConUbicacion(location);
                    LatLng currentLocation = new LatLng(location.getLatitude(), location.getLongitude());
                    mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(currentLocation, 15));
                }
            } else {
                Toast.makeText(this, "No se pudo obtener ubicación", Toast.LENGTH_SHORT).show();
            }
        });
    }

    /**
     * Callback que se llama tras solicitar permisos.
     */
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == LOCATION_PERMISSION_REQUEST_CODE && grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            obtenerUbicacionYConfigurarMapa();
        }
    }

    /**
     * Callback llamado cuando el mapa está listo para usarse.
     *
     * @param googleMap El objeto GoogleMap.
     */
    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {
        mMap = googleMap;
        mMap.getUiSettings().setZoomControlsEnabled(true);
        mMap.setMapType(GoogleMap.MAP_TYPE_TERRAIN);

        if (ubicacionActual != null) {
            configurarMapaConUbicacion(ubicacionActual);
        }
    }

    /**
     * Configura el mapa mostrando la ubicación actual y los restaurantes cercanos.
     *
     * @param location Ubicación actual del usuario.
     */
    private void configurarMapaConUbicacion(Location location) {
        LatLng currentLatLng = new LatLng(location.getLatitude(), location.getLongitude());
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(currentLatLng, 15));
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            mMap.setMyLocationEnabled(true);
        }
        cargarRestaurantes();
    }

    /**
     * Carga los restaurantes desde Firestore y los añade a la lista ordenada por distancia.
     */
    private void cargarRestaurantes() {
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        db.collection("Restaurantes").get().addOnCompleteListener(task -> {
            if (task.isSuccessful() && ubicacionActual != null) {
                listaRestaurantes.clear();
                for (DocumentSnapshot doc : task.getResult()) {
                    try {
                        String name = doc.getString("nombre");
                        String address = doc.getString("direccion");
                        String province = doc.getString("provincia");
                        double telephone = doc.getDouble("telefono");
                        double latitude = doc.getDouble("latitud");
                        double longitude = doc.getDouble("longitud");

                        int telephoneInt = (int) telephone;

                        // Verificación null para coordenadas
                        if (latitude != 0.0 && longitude != 0.0) {
                            double distance = calcularDistanciaKM(
                                    ubicacionActual.getLatitude(),
                                    ubicacionActual.getLongitude(),
                                    latitude,
                                    longitude
                            );
                            listaRestaurantes.add(new Restaurante(name, address, province, telephoneInt, latitude, longitude, distance));
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                Collections.sort(listaRestaurantes, Comparator.comparingDouble(Restaurante::getDistance));
                ponerMarcadoresAlMapa();
            } else {
                Toast.makeText(this, "Error cargando restaurantes", Toast.LENGTH_SHORT).show();
            }
        });
    }

    /**
     * Añade los marcadores de restaurantes al mapa.
     */
    private void ponerMarcadoresAlMapa() {
        if (mMap == null) return;

        mMap.clear();
        for (Restaurante restaurante : listaRestaurantes) {
            LatLng posicion = new LatLng(restaurante.getLat(), restaurante.getLng());

            mMap.addMarker(new MarkerOptions()
                    .position(posicion)
                    .title(restaurante.getName())
                    .snippet("Distancia: " + String.format("%.2f km", restaurante.getDistance()))
                    .icon(BitmapDescriptorFactory.fromResource(R.drawable.logo_mapa)) // Icono personalizado
                    .anchor(0.5f, 1.0f) // Ajusta el punto de anclaje (opcional)
            );
        }
    }

    /**
     * Calcula la distancia en kilómetros entre dos coordenadas.
     *
     * @param lat1 Latitud del punto 1.
     * @param lon1 Longitud del punto 1.
     * @param lat2 Latitud del punto 2.
     * @param lon2 Longitud del punto 2.
     * @return Distancia en kilómetros.
     */
    private double calcularDistanciaKM(double lat1, double lon1, double lat2, double lon2) {
        float[] results = new float[1];
        Location.distanceBetween(lat1, lon1, lat2, lon2, results);
        return results[0] / 1000.0;
    }

    /**
     * Callback llamado cuando cambia la captura de puntero.
     *
     * @param hasCapture true si hay captura, false si no.
     */
    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {
        super.onPointerCaptureChanged(hasCapture);
    }
}