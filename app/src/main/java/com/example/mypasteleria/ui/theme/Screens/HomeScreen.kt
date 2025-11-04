package com.example.mypasteleria.ui.theme.Screens

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.mypasteleria.Navigation.AppRoutes
import com.example.mypasteleria.ViewModel.CarritoViewModel

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    carritoViewModel: CarritoViewModel,
    onNavigate: (String) -> Unit
) {
    val productosEnCarrito by carritoViewModel.carrito.collectAsState()
    val mostrarCarrito = productosEnCarrito.isNotEmpty()

    Scaffold(
        topBar = {
            TopAppBar(title = { Text("🎂 Pastelería Mil Sabores") })
        },
        bottomBar = {
            NavigationBar {
                NavigationBarItem(
                    selected = false,
                    onClick = { onNavigate(AppRoutes.Catalogo.route) },
                    icon = { Icon(Icons.Filled.Menu, contentDescription = "Catálogo") },
                    label = { Text("Catálogo") }
                )
                NavigationBarItem(
                    selected = false,
                    onClick = { onNavigate(AppRoutes.Perfil.route) },
                    icon = { Icon(Icons.Filled.Person, contentDescription = "Perfil") },
                    label = { Text("Perfil") }
                )
                NavigationBarItem(
                    selected = false,
                    onClick = { onNavigate(AppRoutes.Resenas.route) },
                    icon = { Icon(Icons.Filled.Star, contentDescription = "Reseñas") },
                    label = { Text("Reseñas") }
                )
                NavigationBarItem(
                    selected = false,
                    onClick = { onNavigate(AppRoutes.Blog.route) },
                    icon = { Icon(Icons.Filled.Info, contentDescription = "Blog") },
                    label = { Text("Blog") }
                )
                if (mostrarCarrito) {
                    NavigationBarItem(
                        selected = false,
                        onClick = { onNavigate(AppRoutes.Carrito.route) },
                        icon = { Icon(Icons.Filled.ShoppingCart, contentDescription = "Carrito") },
                        label = { Text("Carrito") }
                    )
                }
            }
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
                .padding(16.dp)
        ) {
            Text(
                "Bienvenido a Pastelería Mil Sabores",
                fontSize = 24.sp,
                style = MaterialTheme.typography.headlineMedium
            )
            Spacer(Modifier.height(8.dp))
            Text(
                "Descubre nuestras tortas, postres y delicias artesanales hechas con amor. ¡Endulza tu día!",
                fontSize = 16.sp
            )
            Spacer(Modifier.height(16.dp))
            Button(onClick = { onNavigate(AppRoutes.Catalogo.route) }) {
                Text("Explorar catálogo")
            }
        }
    }
}
