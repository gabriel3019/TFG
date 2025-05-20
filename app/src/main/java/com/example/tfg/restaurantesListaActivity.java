package com.example.tfg;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 * Actividad que muestra una lista de restaurantes ordenados por cercanía a la ubicación actual del usuario.
 *
 * Utiliza Firebase Firestore para obtener los datos de los restaurantes, y la ubicación del usuario
 * para calcular la distancia entre este y cada restaurante. Luego, la lista se muestra en un RecyclerView.
 *
 * Requiere permisos de ubicación para funcionar correctamente.
 */
public class restaurantesListaActivity extends AppCompatActivity {

    private static final int LOCATION_PERMISSION_REQUEST_CODE = 1;
    private FusedLocationProviderClient fusedLocationClient;
    private Location ubicacionActual;
    private RecyclerView restRecyclerView;
    private RestAdapter restAdapter;
    private ArrayList<Restaurante> listaRestaurantes = new ArrayList<>();
    private ImageButton btnHome;

    /**
     * Método que se ejecuta al crear la actividad.
     * Se configura la interfaz, se solicita el permiso de ubicación y se obtiene la ubicación del usuario.
     *
     * @param savedInstanceState Estado guardado de la instancia.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_restaurantes_lista);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        btnHome = findViewById(R.id.buttonHome);

        btnHome.setOnClickListener(v -> {
            Intent intent = new Intent(restaurantesListaActivity.this, menuActivity.class);
            startActivity(intent);
        });

        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this);

        // Verificar permisos de ubicación
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            obtenerUbicacion();
        } else {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, LOCATION_PERMISSION_REQUEST_CODE);
        }

        restRecyclerView = findViewById(R.id.restRecyclerView);
        restRecyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    /**
     * Método que se llama cuando el usuario responde a la solicitud de permisos.
     *
     * @param requestCode  Código de solicitud.
     * @param permissions  Lista de permisos solicitados.
     * @param grantResults Resultados de la solicitud.
     */
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == LOCATION_PERMISSION_REQUEST_CODE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                obtenerUbicacion();
            } else {
                Toast.makeText(this, "Permiso de ubicación denegado", Toast.LENGTH_SHORT).show();
            }
        }
    }

    /**
     * Obtiene la ubicación actual del usuario utilizando el FusedLocationProviderClient.
     * Si la ubicación se obtiene correctamente, llama a {@link #cargarRestaurantes()}.
     */
    private void obtenerUbicacion() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        fusedLocationClient.getLastLocation().addOnSuccessListener(this, new OnSuccessListener<Location>() {
            @Override
            public void onSuccess(Location location) {
                if (location != null) {
                    ubicacionActual = location;
                    cargarRestaurantes();
                } else {
                    Toast.makeText(restaurantesListaActivity.this, "No se pudo obtener la ubicación", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    /**
     * Carga la lista de restaurantes desde Firebase Firestore.
     * Calcula la distancia desde la ubicación del usuario a cada restaurante y ordena la lista por cercanía.
     * Llama a {@link #mostrarRestaurantes()} para mostrar la lista en pantalla.
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
                mostrarRestaurantes();
            } else {
                Toast.makeText(this, "Error cargando restaurantes", Toast.LENGTH_SHORT).show();
            }
        });
    }

    /**
     * Muestra la lista de restaurantes en el RecyclerView utilizando un adaptador personalizado.
     */
    private void mostrarRestaurantes() {
        restAdapter = new RestAdapter(listaRestaurantes);
        restRecyclerView.setAdapter(restAdapter);
    }

    /**
     * Calcula la distancia en kilómetros entre dos ubicaciones geográficas.
     *
     * @param lat1 Latitud del primer punto.
     * @param lng1 Longitud del primer punto.
     * @param lat2 Latitud del segundo punto.
     * @param lng2 Longitud del segundo punto.
     * @return Distancia entre los dos puntos en kilómetros.
     */
    private double calcularDistanciaKM(double lat1, double lng1, double lat2, double lng2) {
        Location location1 = new Location("start");
        location1.setLatitude(lat1);
        location1.setLongitude(lng1);

        Location location2 = new Location("end");
        location2.setLatitude(lat2);
        location2.setLongitude(lng2);

        double distanciaEnMetros = location1.distanceTo(location2);
        return distanciaEnMetros / 1000;
    }
}