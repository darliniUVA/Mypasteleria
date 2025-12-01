package com.example.mypasteleria.ui.theme.Screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import com.example.mypasteleria.Navigation.AppRoutes
import com.example.mypasteleria.ViewModel.UsuarioViewModel
import com.example.mypasteleria.ViewModel.LoginBackendState

@Composable
fun LoginScreen(
    viewModel: UsuarioViewModel,
    onNavigate: (String) -> Unit
) {
    var correo by remember { mutableStateOf("") }
    var clave by remember { mutableStateOf("") }
    var mensajeError by remember { mutableStateOf<String?>(null) }
    var loading by remember { mutableStateOf(false) }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(12.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Iniciar sesión", style = MaterialTheme.typography.titleLarge)

            OutlinedTextField(
                value = correo,
                onValueChange = { correo = it },
                label = { Text("Correo / Usuario") },
                singleLine = true,
                modifier = Modifier.fillMaxWidth()
            )

            OutlinedTextField(
                value = clave,
                onValueChange = { clave = it },
                label = { Text("Contraseña") },
                singleLine = true,
                visualTransformation = PasswordVisualTransformation(),
                modifier = Modifier.fillMaxWidth()
            )

            if (mensajeError != null) {
                Text(mensajeError!!, color = MaterialTheme.colorScheme.error)
            }

            Button(
                onClick = {
                    mensajeError = null
                    loading = true

                    viewModel.loginConBackend(correo, clave) { estado: LoginBackendState ->
                        loading = estado.loading
                        if (estado.error != null) {
                            mensajeError = estado.error
                        } else if (estado.response != null) {
                            onNavigate(AppRoutes.Home.route)
                        }
                    }
                },
                enabled = !loading,
                modifier = Modifier.fillMaxWidth()
            ) {
                if (loading) {
                    CircularProgressIndicator(
                        strokeWidth = 2.dp,
                        modifier = Modifier.size(18.dp)
                    )
                } else {
                    Text("Iniciar sesión")
                }
            }

            TextButton(
                onClick = { onNavigate(AppRoutes.Registro.route) }
            ) {
                Text("¿No tienes cuenta? Regístrate")
            }
        }
    }
}
