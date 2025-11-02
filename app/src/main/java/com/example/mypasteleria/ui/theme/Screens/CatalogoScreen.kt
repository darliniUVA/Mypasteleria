package com.example.mypasteleria.ui.theme.Screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.mypasteleria.Model.listaProductos
import com.example.mypasteleria.ViewModel.CarritoViewModel

@Composable
fun CatalogoScreen() {
    var filtro by remember { mutableStateOf("Todos") }
    val productosFiltrados = if (filtro == "Todos") listaProductos else listaProductos.filter { it.categoria == filtro }

    Column(modifier = Modifier.padding(16.dp)) {
        Text("Catálogo de Productos", style = MaterialTheme.typography.headlineMedium)
        Spacer(modifier = Modifier.height(8.dp))
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
                    }
                }
            }
        }
    }
}

@Composable
fun DropdownMenuCategoria(seleccionActual: String, onSeleccionar: (String) -> Unit) {
    var expandido by remember { mutableStateOf(false) }
    val categorias = listOf("Todos", "Tortas Cuadradas", "Tortas Circulares", "Postres Individuales", "Sin Azúcar", "Sin Gluten", "Veganos")

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
    @Composable
    fun CatalogoScreen() {
        val carritoViewModel: CarritoViewModel = viewModel()
        var filtro by remember { mutableStateOf("Todos") }
        val productosFiltrados = if (filtro == "Todos") listaProductos else listaProductos.filter { it.categoria == filtro }

        Column(modifier = Modifier.padding(16.dp)) {
            Text("Catálogo de Productos", style = MaterialTheme.typography.headlineMedium)
            Spacer(modifier = Modifier.height(8.dp))
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
