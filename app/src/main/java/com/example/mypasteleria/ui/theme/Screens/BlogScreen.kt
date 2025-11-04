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
fun BlogScreen(onNavigate: (String) -> Unit) {
    val posts = listOf(
        "5 Tips para Decorar Tortas en Casa",
        "Receta de Cupcakes Sin Gluten",
        "Tendencias de Pastelería 2025"
    )

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Blog") },
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
                items(posts) { post ->
                    Card(
                        Modifier.fillMaxWidth().padding(vertical = 4.dp),
                        elevation = CardDefaults.cardElevation(2.dp)
                    ) {
                        Text(post, Modifier.padding(16.dp))
                    }
                }
            }
        }
    }
}
