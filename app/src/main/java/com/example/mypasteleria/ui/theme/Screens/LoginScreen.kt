package com.example.mypasteleria.ui.theme.Screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.mypasteleria.Navegation.AppRoutes
import com.example.mypasteleria.ui.theme.MypasteleriaTheme

@Composable
fun LoginScreen(onNavigate: (String) -> Unit) {
    var user by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var error by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Iniciar Sesión", style = MaterialTheme.typography.headlineMedium)
        Spacer(modifier = Modifier.height(20.dp))
        OutlinedTextField(
            value = user,
            onValueChange = { user = it },
            label = { Text("Usuario") },
            singleLine = true
        )
        Spacer(modifier = Modifier.height(12.dp))
        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            label = { Text("Contraseña") },
            singleLine = true,
            visualTransformation = PasswordVisualTransformation()
        )
        Spacer(modifier = Modifier.height(20.dp))
        Button(onClick = {
            if (user == "admin" && password == "1234") {
                onNavigate(AppRoutes.Home.route)
            } else {
                error = true
            }
        }) {
            Text("Ingresar")
        }
        if (error) {
            Spacer(modifier = Modifier.height(10.dp))
            Text("Usuario o contraseña incorrectos", color = MaterialTheme.colorScheme.error)
        }
        Spacer(modifier = Modifier.height(10.dp))
        TextButton(onClick = { onNavigate(AppRoutes.Registro.route) }) {
            Text("¿No tienes cuenta? Regístrate aquí")
        }
    }


}
