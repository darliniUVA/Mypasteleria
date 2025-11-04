package com.example.mypasteleria.ui.theme.Screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import com.example.mypasteleria.Navigation.AppRoutes
import com.example.mypasteleria.ViewModel.UsuarioViewModel

@Composable
fun RegistroScreen(viewModel: UsuarioViewModel, onNavigate: (String) -> Unit) {
    var nombre by remember { mutableStateOf("") }
    var correo by remember { mutableStateOf("") }
    var clave by remember { mutableStateOf("") }
    var direccion by remember { mutableStateOf("") }

    Column(Modifier.padding(16.dp)) {
        OutlinedTextField(
            value = nombre,
            onValueChange = { nombre = it },
            label = { Text("Nombre completo") }
        )
        OutlinedTextField(
            value = correo,
            onValueChange = { correo = it },
            label = { Text("Correo electrónico") }
        )
        OutlinedTextField(
            value = clave,
            onValueChange = { clave = it },
            label = { Text("Contraseña") },
            visualTransformation = PasswordVisualTransformation()
        )
        OutlinedTextField(
            value = direccion,
            onValueChange = { direccion = it },
            label = { Text("Dirección") }
        )

        Button(onClick = {
            viewModel.registrarUsuario(nombre, correo, clave)
            onNavigate(AppRoutes.Login.route)
        }) { Text("Registrar") }


        Button(onClick = { onNavigate(AppRoutes.Login.route) }) {
            Text("Volver al Login")
        }
    }
}
