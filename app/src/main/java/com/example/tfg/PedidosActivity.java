package com.example.tfg;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.Tasks;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class PedidosActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private PedidoProductoAdapter pedidoProductoAdapter;
    private ArrayList<ProductoPedido> productos;
    private FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pedidos);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        productos = new ArrayList<>();
        pedidoProductoAdapter = new PedidoProductoAdapter(productos);
        recyclerView.setAdapter(pedidoProductoAdapter);

        db = FirebaseFirestore.getInstance();

        // Cargar productos desde Firebase
        loadProductos();

        Button btnRealizarPedido = findViewById(R.id.btnRealizarPedido);
        btnRealizarPedido.setOnClickListener(v -> {
            // Realizar el pedido
            Toast.makeText(PedidosActivity.this, "Pedido realizado con éxito", Toast.LENGTH_SHORT).show();

            // Ir al menú principal
            Intent intent = new Intent(PedidosActivity.this, menuActivity.class); // Cambia "MenuActivity" si tu clase tiene otro nombre
            startActivity(intent);
        });
    }

    private void loadProductos() {
        ArrayList<String> colecciones = new ArrayList<>();
        colecciones.add("entrantes");
        colecciones.add("hamburguesas");
        colecciones.add("smashburgers");
        colecciones.add("chickenlovers");
        colecciones.add("parapeques");
        colecciones.add("salads");
        colecciones.add("postres");

        // Crear una lista para almacenar las tareas de cada consulta
        ArrayList<Task<QuerySnapshot>> tasks = new ArrayList<>();

        // Recorrer las colecciones y agregar tareas a la lista
        for (String coleccion : colecciones) {
            tasks.add(db.collection(coleccion).get());
        }

        // Esperar a que todas las tareas se completen
        Tasks.whenAllSuccess(tasks)
                .addOnSuccessListener(results -> {
                    // Para cada consulta, agregar los productos a la lista
                    for (Object result : results) {
                        QuerySnapshot querySnapshot = (QuerySnapshot) result;
                        for (QueryDocumentSnapshot document : querySnapshot) {
                            ProductoPedido producto = document.toObject(ProductoPedido.class);
                            productos.add(producto);
                        }
                    }
                    // Actualizar el adaptador una sola vez después de cargar todos los productos
                    pedidoProductoAdapter.notifyDataSetChanged();
                })
                .addOnFailureListener(e -> {
                    Toast.makeText(PedidosActivity.this, "Error al cargar productos", Toast.LENGTH_SHORT).show();
                });
    }
}
