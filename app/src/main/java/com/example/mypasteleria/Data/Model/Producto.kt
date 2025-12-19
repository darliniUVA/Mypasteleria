package com.example.mypasteleria.Data.Model
import android.media.Image
import kotlinx.serialization.Serializable


@Serializable
data class Producto(
    val codigo: String,
    val categoria: String,
    val nombre: String,
    val descripcion: String,
    val precio: Int,
    val image: Int,
    val stock: Int
)

