package com.example.mypasteleria.ui.theme.Screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.mypasteleria.ViewModel.CarritoViewModel

@Composable
fun CarritoScreen(viewModel: CarritoViewModel) {
    val productos by viewModel.carrito.collectAsState()
    val total = viewModel.obtenerTotal()

    Column(modifier = Modifier.padding(16.dp)) {
        Text("🛒 Carrito de Compras", style = MaterialTheme.typography.headlineMedium)
        Spacer(modifier = Modifier.height(16.dp))

        if (productos.isEmpty()) {
            Text("Tu carrito está vacío.")
        } else {
            LazyColumn {
                items(productos) { producto ->
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 4.dp)
                    ) {
                        Column(modifier = Modifier.padding(12.dp)) {
                            Text(producto.nombre, style = MaterialTheme.typography.titleLarge)
                            Text("Precio: $${producto.precio}")
                            Spacer(modifier = Modifier.height(4.dp))
                            Button(onClick = { viewModel.eliminarProducto(producto) }) {
                                Text("Eliminar")
                            }
                        }
                    }
                }
            }
            Spacer(modifier = Modifier.height(16.dp))
            Text("Total: $${total} CLP", style = MaterialTheme.typography.titleLarge)
        }
    }
}
