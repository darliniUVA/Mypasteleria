package com.example.mypasteleria.ViewModel

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

data class Usuario(
    val nombre: String,
    val correo: String,
    val clave: String,
    val direccion: String = ""
)

class UsuarioViewModel : ViewModel() {
    private val _usuarios = MutableStateFlow<List<Usuario>>(emptyList())
    val usuarios: StateFlow<List<Usuario>> = _usuarios

    private val _usuarioActual = MutableStateFlow<Usuario?>(null)
    val usuarioActual: StateFlow<Usuario?> = _usuarioActual

    fun registrarUsuario(nombre: String, correo: String, clave: String, direccion: String = "") {
        val nuevo = Usuario(nombre, correo, clave, direccion)
        _usuarios.value = _usuarios.value + nuevo
        _usuarioActual.value = nuevo
    }

    fun validarLogin(correo: String, clave: String): Boolean {
        val usuario = _usuarios.value.find { it.correo == correo && it.clave == clave }
        return if (usuario != null) {
            _usuarioActual.value = usuario
            true
        } else false
    }

    fun actualizarPerfil(nombre: String, correo: String, direccion: String) {
        val actual = _usuarioActual.value ?: return
        val actualizado = actual.copy(nombre = nombre, correo = correo, direccion = direccion)
        _usuarioActual.value = actualizado
        _usuarios.value = _usuarios.value.map {
            if (it.correo == actual.correo) actualizado else it
        }
    }

    fun cerrarSesion() {
        _usuarioActual.value = null
    }
}
