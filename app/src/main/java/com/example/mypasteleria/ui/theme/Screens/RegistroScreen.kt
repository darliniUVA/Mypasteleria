package com.example.mypasteleria.ui.theme.Screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import com.example.mypasteleria.ViewModel.UsuarioViewModel

@Composable
fun RegistroScreen(viewModel: UsuarioViewModel, onNavigate: (String) -> Unit) {
    val estado by viewModel.uiState.collectAsState()
    val errores by viewModel.errores.collectAsState()

    Column(Modifier.padding(16.dp)) {
        OutlinedTextField(
            value = estado.nombre,
            onValueChange = { viewModel.actualizarCampo("nombre", it) },
            label = { Text("Nombre completo") },
            isError = errores.nombreError != null,
            supportingText = { Text(errores.nombreError ?: "") }
        )
        OutlinedTextField(
            value = estado.correo,
            onValueChange = { viewModel.actualizarCampo("correo", it) },
            label = { Text("Correo electrónico") },
            isError = errores.correoError != null,
            supportingText = { Text(errores.correoError ?: "") }
        )
        OutlinedTextField(
            value = estado.clave,
            onValueChange = { viewModel.actualizarCampo("clave", it) },
            label = { Text("Contraseña") },
            visualTransformation = PasswordVisualTransformation(),
            isError = errores.claveError != null,
            supportingText = { Text(errores.claveError ?: "") }
        )
        OutlinedTextField(
            value = estado.direccion,
            onValueChange = { viewModel.actualizarCampo("direccion", it) },
            label = { Text("Dirección") },
            isError = errores.direccionError != null,
            supportingText = { Text(errores.direccionError ?: "") }
        )
        Button(onClick = {
            if (viewModel.validarFormulario()) onNavigate("resumen")
        }) {
            Text("Registrar")
        }
        Button(onClick = { onNavigate("login") }) {
            Text("Volver al Login")
        }
    }
}
