package com.example.tfg;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.Toast;

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
import com.google.android.gms.maps.model.BitmapDescriptor;
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

    private static final int LOCATION_PERMISSION_REQUEST_CODE = 1;

    private GoogleMap mMap;
    private FusedLocationProviderClient fusedLocationClient;
    private Location ubicacionActual;
    private Location ubicacionFake;
    private ArrayList<Restaurante> listaRestaurantes = new ArrayList<>();
    private ImageButton btnHome;

    /**
     * Método llamado al crear la actividad.
     *
     * @param savedInstanceState Estado previamente guardado de la actividad.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurantes_mapa);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets sys = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(sys.left, sys.top, sys.right, sys.bottom);
            return insets;
        });

        btnHome = findViewById(R.id.buttonHome);
        btnHome.setOnClickListener(v -> {
            Intent intent = new Intent(restaurantesMapaActivity.this, menuActivity.class);
            startActivity(intent);
        });

        // Ubicación FAKE para testing sin GPS real
        ubicacionFake = new Location("mock");
        ubicacionFake.setLatitude(40.43376);
        ubicacionFake.setLongitude(-3.63169);


        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this);

        SupportMapFragment mapFragment =
                (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        if (mapFragment != null) {
            mapFragment.getMapAsync(this);
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
        // Cambiado a mapa normal (calles, POIs, etc.)
        mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);

        // Verificar permisos de ubicación
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(
                    this,
                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                    LOCATION_PERMISSION_REQUEST_CODE
            );
            return;
        }

        // Permiso concedido: activar capa de Mi Ubicación
        mMap.setMyLocationEnabled(true);

        // Obtener última ubicación conocida
        fusedLocationClient.getLastLocation()
                .addOnSuccessListener(location -> {
                    if (location == null) {
                        Toast.makeText(this, "No se pudo obtener ubicación", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    ubicacionActual = location;
                    LatLng current = new LatLng(location.getLatitude(), location.getLongitude());
                    mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(current, 15));

                    // Cargar y marcar restaurantes
                    cargarRestaurantes();
                });
    }

    /**
     * Callback que se llama tras solicitar permisos.
     */
    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == LOCATION_PERMISSION_REQUEST_CODE
                && grantResults.length > 0
                && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            // Reintentar inicialización del mapa
            SupportMapFragment mapFragment =
                    (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
            if (mapFragment != null) {
                mapFragment.getMapAsync(this);
            }
        }
    }

    /**
     * Carga los restaurantes desde Firestore y los añade a la lista ordenada por distancia.
     */
    private void cargarRestaurantes() {
        if (ubicacionActual == null || mMap == null) return;

        FirebaseFirestore db = FirebaseFirestore.getInstance();
        db.collection("Restaurantes").get().addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                listaRestaurantes.clear();
                for (DocumentSnapshot doc : task.getResult()) {
                    Double lat = doc.getDouble("latitud");
                    Double lng = doc.getDouble("longitud");
                    String name = doc.getString("nombre");
                    String address = doc.getString("direccion");
                    String province = doc.getString("provincia");
                    Double telD = doc.getDouble("telefono");
                    if (lat != null && lng != null && lat != 0.0 && lng != 0.0) {
                        double distance = calcularDistanciaKM(
                                ubicacionActual.getLatitude(),
                                ubicacionActual.getLongitude(),
                                lat, lng
                        );
                        int telephone = telD != null ? telD.intValue() : 0;
                        listaRestaurantes.add(
                                new Restaurante(name, address, province, telephone, lat, lng, distance)
                        );
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
        mMap.clear();
        BitmapDescriptor iconoLogo = BitmapDescriptorFactory.fromResource(R.drawable.logo_mapa);
        for (Restaurante r : listaRestaurantes) {
            LatLng pos = new LatLng(r.getLat(), r.getLng());
            mMap.addMarker(new MarkerOptions()
                    .position(pos)
                    .title(r.getName())
                    .snippet(String.format("Distancia: %.2f km", r.getDistance()))
                    .icon(iconoLogo)
                    .anchor(0.5f, 0.5f)
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
}
