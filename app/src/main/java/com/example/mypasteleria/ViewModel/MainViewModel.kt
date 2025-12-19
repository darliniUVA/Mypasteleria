package com.example.mypasteleria.ViewModel

import androidx.lifecycle.ViewModel
import com.example.mypasteleria.Data.Model.UsuarioUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

data class UiState(
    val usuarioActual: UsuarioUiState? = null
)

class MainViewModel : ViewModel() {

    private val _usuarios = MutableStateFlow<List<UsuarioUiState>>(emptyList())
    val usuarios = _usuarios.asStateFlow()

    private val _uiState = MutableStateFlow(UiState())
    val uiState = _uiState.asStateFlow()

    fun registrarUsuario(
        nombre: String,
        correo: String,
        clave: String,
        direccion: String = ""
    ): Boolean {

        val email = correo.trim().lowercase()
        val pass = clave.trim()

        if (_usuarios.value.any { it.correo.equals(email, ignoreCase = true) }) {
            return false
        }

        val nuevo = UsuarioUiState(
            nombre = nombre.trim(),
            correo = email,
            clave = pass,
            direccion = direccion.trim()
        )

        _usuarios.value = _usuarios.value + nuevo
        _uiState.value = UiState(usuarioActual = nuevo)

        return true
    }

    fun validarLogin(correo: String, clave: String): Boolean {
        val email = correo.trim().lowercase()
        val pass = clave.trim()

        val usuario = _usuarios.value.firstOrNull {
            it.correo == email && it.clave == pass
        }

        _uiState.value = UiState(usuarioActual = usuario)

        return usuario != null
    }

    fun cerrarSesion() {
        _uiState.value = UiState(usuarioActual = null)
    }
}
