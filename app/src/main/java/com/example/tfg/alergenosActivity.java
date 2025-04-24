package com.example.tfg;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.ScrollView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class alergenosActivity extends AppCompatActivity {

    private List<Producto> todosProductos = new ArrayList<>();
    private List<Producto> productosFiltrados = new ArrayList<>();
    private RecyclerView recyclerView;
    private ProductoAdapter adapter;

    // Checkboxes
    private CheckBox cbGluten, cbCrustaceos, cbHuevos, cbPescado, cbCacahuetes, cbSoja;
    private CheckBox cbLacteos, cbFrutosSecos, cbApio, cbMostaza, cbSesamo, cbSulfitos;
    private ScrollView scrollView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alergenos);

        // Inicializar vistas
        inicializarVistas();

        // Configurar RecyclerView
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new ProductoAdapter(productosFiltrados);
        recyclerView.setAdapter(adapter);

        // Cargar datos de productos
        cargarProductos();

        // Configurar botón de filtrado
        Button btnFiltrar = findViewById(R.id.btnFiltrar);
        btnFiltrar.setOnClickListener(v -> filtrarProductos());

        // Configurar botón de home
        ImageButton btnHome = findViewById(R.id.buttonHome);
        btnHome.setOnClickListener(v -> {
            Intent intent = new Intent(alergenosActivity.this, menuActivity.class);
            startActivity(intent);
            finish();
        });
    }

    private void inicializarVistas() {
        // Checkboxes
        cbGluten = findViewById(R.id.cbGluten);
        cbCrustaceos = findViewById(R.id.cbCrustaceos);
        cbHuevos = findViewById(R.id.cbHuevos);
        cbPescado = findViewById(R.id.cbPescado);
        cbCacahuetes = findViewById(R.id.cbCacahuetes);
        cbSoja = findViewById(R.id.cbSoja);
        cbLacteos = findViewById(R.id.cbLacteos);
        cbFrutosSecos = findViewById(R.id.cbFrutosSecos);
        cbApio = findViewById(R.id.cbApio);
        cbMostaza = findViewById(R.id.cbMostaza);
        cbSesamo = findViewById(R.id.cbSesamo);
        cbSulfitos = findViewById(R.id.cbSulfitos);

        // Otras vistas
        recyclerView = findViewById(R.id.recyclerViewResultados);
        scrollView = findViewById(R.id.scrollView);
    }

    private void cargarProductos() {
        // Limpiar listas
        todosProductos.clear();
        productosFiltrados.clear();

        // Ejemplo de productos de Goiko (deberías completar con los productos reales)
        todosProductos.add(new Producto("Kevin Bacon", "Hamburguesa con carne picada a mano, bacon crujiente y queso americano", Arrays.asList("Gluten", "Lácteos", "Huevos", "Soja", "Sulfitos")));
        todosProductos.add(new Producto("M-30", "Con huevo frito, plátano macho, bacon y queso", Arrays.asList("Gluten", "Lácteos", "Huevos", "Soja", "Sulfitos")));
        todosProductos.add(new Producto("Yankee", "Clásica cheeseburger americana", Arrays.asList("Gluten", "Lácteos", "Huevos", "Soja", "Sulfitos")));
        todosProductos.add(new Producto("La Pigma", "Con pulled pork, bacon, cebolla crunchy y queso", Arrays.asList("Gluten", "Lácteos", "Huevos", "Soja", "Sulfitos")));
        todosProductos.add(new Producto("Camburger", "Con champiñones salteados, queso suizo y mayonesa trufada", Arrays.asList("Gluten", "Lácteos", "Huevos", "Soja", "Sulfitos")));
        todosProductos.add(new Producto("Kaifuku", "Hamburguesa con teriyaki, queso cheddar, cebolla caramelizada y mayo picante", Arrays.asList("Gluten", "Lácteos", "Huevos", "Soja", "Sulfitos", "Sésamo")));
        todosProductos.add(new Producto("Truffe burger", "Con salsa trufada, queso fundido y champiñones", Arrays.asList("Gluten", "Lácteos", "Huevos", "Soja", "Sulfitos")));
        todosProductos.add(new Producto("Chiliraptor", "Con chili con carne, cheddar, nachos y salsa picante", Arrays.asList("Gluten", "Lácteos", "Huevos", "Soja", "Sulfitos")));
        todosProductos.add(new Producto("Kevin Costner", "Versión especial del Kevin Bacon con carne de costilla desmenuzada", Arrays.asList("Gluten", "Lácteos", "Huevos", "Soja", "Sulfitos")));

        todosProductos.add(new Producto("Crispy Chicken Wings", "Alitas de pollo crujientes", Arrays.asList("Huevo", "Gluten", "Lácteos", "Soja", "Sulfitos")));
        todosProductos.add(new Producto("Teques®", "Palitos de queso latinoamericanos", Arrays.asList("Gluten", "Lácteos", "Huevos", "Soja", "Sulfitos")));
        todosProductos.add(new Producto("Teque Vinci", "Variante de teques con sabor único", Arrays.asList("Lácteos", "Huevos", "Soja", "Sulfitos")));
        todosProductos.add(new Producto("Chicken Tenders", "Tiras de pollo empanadas", Arrays.asList("Gluten", "Huevos", "Lácteos", "Soja", "Sulfitos")));
        todosProductos.add(new Producto("Nachorreo", "Nachos con queso, guacamole y toppings", Arrays.asList("Gluten", "Huevos", "Lácteos", "Soja", "Sulfitos")));
        todosProductos.add(new Producto("Onion rings", "Aros de cebolla rebozados", Arrays.asList("Gluten", "Huevos", "Lácteos", "Soja", "Sulfitos")));
        todosProductos.add(new Producto("Ave César", "Ensalada César con pollo", Arrays.asList("Gluten", "Lácteos", "Huevos")));

        todosProductos.add(new Producto("Ensalada César", "Lechuga, pollo crujiente, parmesano, croutons y salsa César", Arrays.asList("Gluten", "Lácteos", "Huevos", "Pescado", "Soja", "Mostaza")));
        todosProductos.add(new Producto("Ensalada Thai", "Base de lechugas, pollo marinado, cacahuetes, zanahoria y aliño oriental", Arrays.asList("Cacahuetes", "Soja", "Sésamo", "Mostaza", "Sulfitos")));
        todosProductos.add(new Producto("Ensalada Veggie", "Mix de hojas verdes, aguacate, tomate cherry, cebolla morada y vinagreta balsámica", Arrays.asList("Sulfitos", "Mostaza")));
        todosProductos.add(new Producto("Ensalada Mediterránea", "Tomate, pepino, aceitunas negras, cebolla roja, queso feta y orégano", Arrays.asList("Lácteos", "Sulfitos")));
        todosProductos.add(new Producto("Ensalada de Quinoa", "Con quinoa, espinacas, garbanzos, zanahoria rallada y aliño cítrico", Arrays.asList("Sulfitos", "Mostaza")));

        todosProductos.add(new Producto("Brownie con Helado", "Brownie de chocolate caliente con nueces y bola de helado de vainilla", Arrays.asList("Gluten", "Lácteos", "Huevos", "Frutos de cáscara", "Soja")));
        todosProductos.add(new Producto("Cookie caliente", "Galleta de chocolate recién horneada con helado", Arrays.asList("Gluten", "Lácteos", "Huevos", "Soja")));
        todosProductos.add(new Producto("Cheesecake de Oreo", "Tarta de queso con base de galleta Oreo", Arrays.asList("Gluten", "Lácteos", "Huevos", "Soja")));
        todosProductos.add(new Producto("Carrot Cake", "Bizcocho de zanahoria con crema de queso", Arrays.asList("Gluten", "Lácteos", "Huevos", "Frutos de cáscara")));
        todosProductos.add(new Producto("Coulant de Chocolate", "Bizcocho caliente con corazón de chocolate fundido", Arrays.asList("Gluten", "Lácteos", "Huevos", "Soja")));


    }

    private void filtrarProductos() {
        List<String> alergenosSeleccionados = obtenerAlergenosSeleccionados();

        productosFiltrados.clear();

        for (Producto producto : todosProductos) {
            if (!producto.contieneAlergenos(alergenosSeleccionados)) {
                productosFiltrados.add(producto);
            }
        }

        mostrarResultados();
    }

    private List<String> obtenerAlergenosSeleccionados() {
        List<String> seleccionados = new ArrayList<>();

        if (cbGluten.isChecked()) seleccionados.add("Gluten");
        if (cbCrustaceos.isChecked()) seleccionados.add("Crustáceos");
        if (cbHuevos.isChecked()) seleccionados.add("Huevos");
        if (cbPescado.isChecked()) seleccionados.add("Pescado");
        if (cbCacahuetes.isChecked()) seleccionados.add("Cacahuetes");
        if (cbSoja.isChecked()) seleccionados.add("Soja");
        if (cbLacteos.isChecked()) seleccionados.add("Lácteos");
        if (cbFrutosSecos.isChecked()) seleccionados.add("Frutos secos");
        if (cbApio.isChecked()) seleccionados.add("Apio");
        if (cbMostaza.isChecked()) seleccionados.add("Mostaza");
        if (cbSesamo.isChecked()) seleccionados.add("Sésamo");
        if (cbSulfitos.isChecked()) seleccionados.add("Sulfitos");

        return seleccionados;
    }

    private void mostrarResultados() {
        if (productosFiltrados.isEmpty()) {
            Toast.makeText(this, "No hay productos que cumplan con tus filtros", Toast.LENGTH_LONG).show();
            recyclerView.setVisibility(View.GONE);
            scrollView.setVisibility(View.VISIBLE);
        } else {
            adapter.notifyDataSetChanged();
            recyclerView.setVisibility(View.VISIBLE);
            scrollView.setVisibility(View.GONE);
        }
    }
}