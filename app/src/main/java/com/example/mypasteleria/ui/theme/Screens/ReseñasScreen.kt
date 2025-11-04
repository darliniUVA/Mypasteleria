package com.example.mypasteleria.ui.theme.Screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ResenasScreen(onNavigate: (String) -> Unit) {
    val resenas = listOf(
        "⭐⭐⭐⭐⭐ — ¡Las mejores tortas! Súper frescas.",
        "⭐⭐⭐⭐ — Buen servicio y deliciosos postres.",
        "⭐⭐⭐⭐⭐ — Encargué una torta personalizada, quedó perfecta."
    )

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Reseñas") },
                navigationIcon = {
                    IconButton(onClick = { onNavigate("home") }) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = null)
                    }
                }
            )
        }
    ) { innerPadding ->
        Column(Modifier.padding(innerPadding).padding(16.dp)) {
            LazyColumn {
                items(resenas) { resena ->
                    Card(
                        Modifier.fillMaxWidth().padding(vertical = 4.dp),
                        elevation = CardDefaults.cardElevation(2.dp)
                    ) {
                        Text(resena, Modifier.padding(16.dp))
                    }
                }
            }
        }
    }
}
