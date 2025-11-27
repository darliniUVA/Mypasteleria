package com.example.mypasteleria.ui.theme.Screens

import android.Manifest
import android.annotation.SuppressLint
import android.content.pm.PackageManager
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices

@SuppressLint("MissingPermission")
@Composable
fun UbicacionScreen() {

    val context = LocalContext.current
    var latitud by remember { mutableStateOf("—") }
    var longitud by remember { mutableStateOf("—") }

    val fusedLocationClient: FusedLocationProviderClient =
        LocationServices.getFusedLocationProviderClient(context)

    Column(Modifier.padding(20.dp)) {

        Text(
            "Recurso Nativo: GPS 📍",
            style = MaterialTheme.typography.headlineMedium
        )
        Spacer(Modifier.height(20.dp))

        Button(onClick = {

            val permisoConcedido =
                ContextCompat.checkSelfPermission(
                    context,
                    Manifest.permission.ACCESS_FINE_LOCATION
                ) == PackageManager.PERMISSION_GRANTED

            if (permisoConcedido) {

                fusedLocationClient.lastLocation.addOnSuccessListener { location ->
                    if (location != null) {
                        latitud = location.latitude.toString()
                        longitud = location.longitude.toString()
                    } else {
                        latitud = "No disponible"
                        longitud = "No disponible"
                    }
                }

            } else {
                latitud = "Permiso DENEGADO"
                longitud = "Permiso DENEGADO"
            }

        }) {
            Text("Obtener mi ubicación")
        }

        Spacer(Modifier.height(20.dp))

        Text("Latitud: $latitud")
        Text("Longitud: $longitud")
    }
}
