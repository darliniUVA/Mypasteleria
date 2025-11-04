package com.example.mypasteleria.ui.theme.Screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.mypasteleria.Navigation.AppRoutes
import com.example.mypasteleria.Model.listaProductos
import com.example.mypasteleria.ViewModel.CarritoViewModel


@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun CarritoScreen(viewModel: CarritoViewModel, onNavigate: (String) -> Unit) {
    val productos by viewModel.carrito.collectAsState()
    val total = viewModel.obtenerTotal()
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Carrito de Compras") },
                navigationIcon = {
                    IconButton(onClick = { onNavigate(AppRoutes.Home.route) }) {
                        Icon(Icons.Filled.ArrowBack, contentDescription = "Volver")
                    }
                }
            )
        }
    ) { innerPadding ->
        Column(Modifier.padding(innerPadding).padding(16.dp)) {
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
}

@Composable

fun DropdownMenuCategoria(seleccionActual: String, onSeleccionar: (String) -> Unit) {
    var expandido by remember { mutableStateOf(false) }
    val categorias = listOf("Todos", "Tortas Cuadradas", "Tortas Circulares",
        "Postres Individuales", "Sin Azúcar", "Sin Gluten", "Veganos")

    Box {
        Button(onClick = { expandido = true }) {
            Text("Categoría: $seleccionActual")
        }
        DropdownMenu(expanded = expandido, onDismissRequest = { expandido = false }) {
            categorias.forEach { categoria ->
                DropdownMenuItem(
                    text = { Text(categoria) },
                    onClick = {
                        onSeleccionar(categoria)
                        expandido = false
                    }
                )
            }
        }
    }
}



@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun CatalogoScreen(carritoViewModel: CarritoViewModel, onNavigate: (String) -> Unit) {
    var filtro by remember { mutableStateOf("Todos") }
    val productosFiltrados = if (filtro == "Todos") listaProductos else listaProductos.filter { it.categoria == filtro }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Catálogo de Productos") },
                navigationIcon = {
                    IconButton(onClick = { onNavigate(AppRoutes.Home.route) }) {
                        Icon(Icons.Filled.ArrowBack, contentDescription = "Volver")
                    }
                }
            )
        }
    ) { innerPadding ->
        Column(modifier = Modifier.padding(innerPadding).padding(16.dp)) {
            DropdownMenuCategoria(filtro) { filtro = it }
            Spacer(modifier = Modifier.height(8.dp))
            LazyColumn {
                items(productosFiltrados) { producto ->
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 6.dp),
                        elevation = CardDefaults.cardElevation(4.dp)
                    ) {
                        Column(modifier = Modifier.padding(16.dp)) {
                            Text(producto.nombre, style = MaterialTheme.typography.titleLarge)
                            Text(producto.categoria, style = MaterialTheme.typography.labelMedium)
                            Text(producto.descripcion)
                            Text("Precio: $${producto.precio} CLP", style = MaterialTheme.typography.bodyLarge)
                            Spacer(modifier = Modifier.height(8.dp))
                            Button(onClick = { carritoViewModel.agregarProducto(producto) }) {
                                Text("Agregar al carrito 🛒")
                            }
                        }
                    }
                }
            }
        }
    }
}
