package com.example.mypasteleria.ui.theme.Screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.mypasteleria.Navigation.AppRoutes
import com.example.mypasteleria.ViewModel.UsuarioViewModel

@Composable
fun PerfilScreen(viewModel: UsuarioViewModel, onNavigate: (String) -> Unit) {
    val estado by viewModel.uiState.collectAsState()
    var mostrarMensaje by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Mi Perfil", style = MaterialTheme.typography.headlineMedium)
        Spacer(modifier = Modifier.height(16.dp))
        OutlinedTextField(
            value = estado.nombre,
            onValueChange = { viewModel.actualizarCampo("nombre", it) },
            label = { Text("Nombre completo") }
        )
        Spacer(modifier = Modifier.height(8.dp))
        OutlinedTextField(
            value = estado.correo,
            onValueChange = { viewModel.actualizarCampo("correo", it) },
            label = { Text("Correo electrónico") }
        )
        Spacer(modifier = Modifier.height(8.dp))
        OutlinedTextField(
            value = estado.direccion,
            onValueChange = { viewModel.actualizarCampo("direccion", it) },
            label = { Text("Dirección") }
        )
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = { mostrarMensaje = true }) {
            Text("Actualizar datos")
        }
        Spacer(modifier = Modifier.height(12.dp))
        Button(onClick = {
            viewModel.limpiarUsuario()
            onNavigate(AppRoutes.Login.route)
        }) {
            Text("Cerrar sesión")
        }
        if (mostrarMensaje) {
            Spacer(modifier = Modifier.height(12.dp))
            Text(
                text = "Datos actualizados correctamente ",
                color = MaterialTheme.colorScheme.primary
            )
        }
    }
}
