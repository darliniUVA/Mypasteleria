package com.example.mypasteleria.Model
import kotlinx.serialization.Serializable


@Serializable
data class Producto(
    val codigo: String,
    val categoria: String,
    val nombre: String,
    val descripcion: String,
    val precio: Int
)

