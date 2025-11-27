package com.example.mypasteleria.Data.Model

data class UsuarioUiState(
    val nombre: String = "",
    val correo: String = "",
    val clave: String = "",
    val direccion: String = ""
) {
    companion object
}

data class UsuarioErrores(
    val nombreError: String? = null,
    val correoError: String? = null,
    val claveError: String? = null,
    val direccionError: String? = null
)
