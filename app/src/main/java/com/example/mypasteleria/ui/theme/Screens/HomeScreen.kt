package com.example.mypasteleria.ui.theme.screens

import ProductosDestacados
import RedesScreen
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.mypasteleria.Navigation.AppRoutes
import com.example.mypasteleria.ViewModel.CarritoViewModel
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.runtime.*
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    onNavigate: (String) -> Unit,
    carritoViewModel: CarritoViewModel
) {

    Scaffold(
        topBar = { RedesScreen() },
        bottomBar = { BarraInferior(onNavigate, carritoViewModel) }
    ) { paddingValues ->

        Column(
            modifier = Modifier
                .padding(paddingValues)
                .padding(16.dp)
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Text(
                "Bienvenido a Pastelería Mil Sabores",
                style = MaterialTheme.typography.headlineLarge
            )

            Spacer(Modifier.height(20.dp))

            Button(onClick = { onNavigate(AppRoutes.Catalogo.route) }) {
                Text("Ver catálogo")
            }

            Spacer(Modifier.height(20.dp))

            ProductosDestacados()
        }
    }
}
@Composable
fun BarraInferior(
    onNavigate: (String) -> Unit,
    carritoViewModel: CarritoViewModel
) {
    val productos by carritoViewModel.carrito.collectAsState()
    val mostrarCarrito = productos.isNotEmpty()

    NavigationBar {

        NavigationBarItem(
            selected = false,
            onClick = { onNavigate(AppRoutes.Home.route) },
            icon = { Icon(Icons.Default.Home, null) },
            label = { Text("Inicio") }
        )

        NavigationBarItem(
            selected = false,
            onClick = { onNavigate(AppRoutes.Catalogo.route) },
            icon = { Icon(Icons.Default.ShoppingCart, null) },
            label = { Text("Catálogo") }
        )

        if (mostrarCarrito) {
            NavigationBarItem(
                selected = false,
                onClick = { onNavigate(AppRoutes.Carrito.route) },
                icon = { Icon(Icons.Default.ShoppingCart, null) },
                label = { Text("Carrito") }
            )
        }

        NavigationBarItem(
            selected = false,
            onClick = { onNavigate(AppRoutes.Perfil.route) },
            icon = { Icon(Icons.Default.Person, null) },
            label = { Text("Perfil") }
        )
    }
}
