package com.example.mypasteleria.ui.theme.Screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import com.example.mypasteleria.Navigation.AppRoutes
import com.example.mypasteleria.ViewModel.UsuarioViewModel

@Composable
fun RegistroScreen(
    viewModel: UsuarioViewModel,
    onNavigate: (String) -> Unit
) {
    var nombre by remember { mutableStateOf("") }
    var correo by remember { mutableStateOf("") }
    var clave by remember { mutableStateOf("") }
    var direccion by remember { mutableStateOf("") }
    var mensajeError by remember { mutableStateOf<String?>(null) }
    var loading by remember { mutableStateOf(false) }

    val errores by viewModel.erroresState.collectAsState()

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Registro de usuario",
                style = MaterialTheme.typography.titleLarge
            )

            OutlinedTextField(
                value = nombre,
                onValueChange = { nombre = it },
                label = { Text("Nombre") },
                isError = errores.nombreError != null,
                modifier = Modifier.fillMaxWidth()
            )
            if (errores.nombreError != null) {
                Text(
                    text = errores.nombreError ?: "",
                    color = MaterialTheme.colorScheme.error,
                    style = MaterialTheme.typography.bodySmall
                )
            }

            OutlinedTextField(
                value = correo,
                onValueChange = { correo = it },
                label = { Text("Correo") },
                isError = errores.correoError != null,
                modifier = Modifier.fillMaxWidth()
            )
            if (errores.correoError != null) {
                Text(
                    text = errores.correoError ?: "",
                    color = MaterialTheme.colorScheme.error,
                    style = MaterialTheme.typography.bodySmall
                )
            }

            OutlinedTextField(
                value = clave,
                onValueChange = { clave = it },
                label = { Text("Contraseña") },
                visualTransformation = PasswordVisualTransformation(),
                isError = errores.claveError != null,
                modifier = Modifier.fillMaxWidth()
            )
            if (errores.claveError != null) {
                Text(
                    text = errores.claveError ?: "",
                    color = MaterialTheme.colorScheme.error,
                    style = MaterialTheme.typography.bodySmall
                )
            }

            OutlinedTextField(
                value = direccion,
                onValueChange = { direccion = it },
                label = { Text("Dirección") },
                isError = errores.direccionError != null,
                modifier = Modifier.fillMaxWidth()
            )
            if (errores.direccionError != null) {
                Text(
                    text = errores.direccionError ?: "",
                    color = MaterialTheme.colorScheme.error,
                    style = MaterialTheme.typography.bodySmall
                )
            }

            if (mensajeError != null) {
                Text(
                    text = mensajeError ?: "",
                    color = MaterialTheme.colorScheme.error,
                    style = MaterialTheme.typography.bodyMedium
                )
            }

            Spacer(modifier = Modifier.height(8.dp))

            Button(
                onClick = {
                    mensajeError = null
                    viewModel.registrarUsuarioEnBackend(
                        nombre = nombre,
                        correo = correo,
                        clave = clave,
                        direccion = direccion
                    ) { estado ->
                        loading = estado.loading
                        if (estado.error != null) {
                            mensajeError = estado.error
                        } else if (!estado.loading) {
                            onNavigate(AppRoutes.Login.route)
                        }
                    }
                },
                enabled = !loading,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(if (loading) "Registrando..." else "Registrar")
            }

            TextButton(
                onClick = { onNavigate(AppRoutes.Login.route) }
            ) {
                Text("¿Ya tienes cuenta? Inicia sesión")
            }
        }
    }
}
