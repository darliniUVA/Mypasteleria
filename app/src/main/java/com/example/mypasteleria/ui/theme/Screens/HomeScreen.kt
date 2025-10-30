package com.example.mypasteleria.ui.theme.Screens
import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.mypasteleria.Navegation.AppRoutes

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(onNavigate: (String) -> Unit) {
    Scaffold(
        topBar = { TopAppBar(title = { Text("🎂 Pastelería Mil Sabores") }) },
        bottomBar = {
            NavigationBar {
                NavigationBarItem(
                    selected = true,
                    onClick = { onNavigate(AppRoutes.Catalogo.route) },
                    icon = { Icon(Icons.Default.ShoppingCart, null) },
                    label = { Text("Catálogo") }
                )
                NavigationBarItem(
                    selected = false,
                    onClick = { onNavigate(AppRoutes.Perfil.route) },
                    icon = { Icon(Icons.Default.Person, null) },
                    label = { Text("Perfil") }
                )
            }
        }
    ) {
        Column(Modifier.padding(16.dp))
        {
            Text("¡Celebra la dulzura de la vida con nosotros!", fontSize = 20.sp)
            Button(onClick = { onNavigate(AppRoutes.Catalogo.route) }) {
                Text("Ver catálogo 🍰")
            }
        }
    }
}

