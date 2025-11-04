package com.example.mypasteleria.ui.theme.Screens

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.mypasteleria.Navigation.AppRoutes
import com.example.mypasteleria.ViewModel.UsuarioViewModel


@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun PerfilScreen(viewModel: UsuarioViewModel, onNavigate: (String) -> Unit) {
    val usuario by viewModel.usuarioActual.collectAsState()
    var nombre by remember { mutableStateOf(usuario?.nombre ?: "") }
    var correo by remember { mutableStateOf(usuario?.correo ?: "") }
    var direccion by remember { mutableStateOf(usuario?.direccion ?: "") }
    var mensaje by remember { mutableStateOf("") }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Mi Perfil") },
                navigationIcon = {
                    IconButton(onClick = { onNavigate(AppRoutes.Home.route) }) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Volver"
                        )
                    }
                }
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            OutlinedTextField(value = nombre, onValueChange = { nombre = it }, label = { Text("Nombre") })
            Spacer(modifier = Modifier.height(8.dp))
            OutlinedTextField(value = correo, onValueChange = { correo = it }, label = { Text("Correo") })
            Spacer(modifier = Modifier.height(8.dp))
            OutlinedTextField(value = direccion, onValueChange = { direccion = it }, label = { Text("Dirección") })
            Spacer(modifier = Modifier.height(16.dp))
            Button(onClick = {
                viewModel.actualizarPerfil(nombre, correo, direccion)
                mensaje = "Datos actualizados correctamente"
            }) {
                Text("Actualizar datos")
            }
            Spacer(modifier = Modifier.height(12.dp))
            Button(onClick = {
                viewModel.cerrarSesion()
                onNavigate(AppRoutes.Login.route)
            }) {
                Text("Cerrar sesión")
            }
            if (mensaje.isNotEmpty()) {
                Spacer(modifier = Modifier.height(12.dp))
                Text(mensaje, color = MaterialTheme.colorScheme.primary)
            }
        }
    }
}
