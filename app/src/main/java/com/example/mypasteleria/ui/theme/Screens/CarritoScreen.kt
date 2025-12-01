package com.example.mypasteleria.ui.theme.screens.CarritoScreen

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.mypasteleria.Navigation.AppRoutes
import com.example.mypasteleria.ViewModel.CarritoViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CarritoScreen(viewModel: CarritoViewModel, onNavigate: (String) -> Unit) {
    val productos by viewModel.carrito.collectAsState()
    val total = productos.sumOf { it.precio }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("🛒 Carrito de Compras") },
                navigationIcon = {
                    IconButton(onClick = { onNavigate(AppRoutes.Home.route) }) {
                        Icon(Icons.Filled.ArrowBack, contentDescription = "Volver")
                    }
                }
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .padding(16.dp)
        ) {
            if (productos.isEmpty()) {
                Text("Tu carrito está vacío.", style = MaterialTheme.typography.bodyLarge)
                Spacer(modifier = Modifier.height(12.dp))
                Button(onClick = { onNavigate(AppRoutes.Catalogo.route) }) {
                    Text("Ir al catálogo")
                }
            } else {
                LazyColumn {
                    items(productos) { producto ->
                        Card(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 6.dp)
                        ) {
                            Column(modifier = Modifier.padding(12.dp)) {
                                Text(producto.nombre, style = MaterialTheme.typography.titleLarge)
                                Text("Precio: $${producto.precio}", style = MaterialTheme.typography.bodyMedium)
                                Spacer(modifier = Modifier.height(8.dp))
                                Row {
                                    Button(onClick = { viewModel.eliminarProducto(producto) }) {
                                        Text("Eliminar")
                                    }
                                }
                            }
                        }
                    }
                }
                Spacer(modifier = Modifier.height(16.dp))
                Text("Total: $${total} CLP", style = MaterialTheme.typography.titleLarge)
                Spacer(modifier = Modifier.height(12.dp))
                Button(onClick = { /* acción de compra */ }, modifier = Modifier.fillMaxWidth()) {
                    Text("Finalizar Compra")
                }
            }
        }
    }
}
