package com.example.mypasteleria.ViewModel

import androidx.lifecycle.ViewModel
import com.example.mypasteleria.Data.Model.UsuarioUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

data class UiState(
    val usuarioActual: UsuarioUiState? = null
)

class MainViewModel : ViewModel() {

    // Lista de usuarios registrados (en memoria)
    private val _usuarios = MutableStateFlow<List<UsuarioUiState>>(emptyList())
    val usuarios = _usuarios.asStateFlow()

    // Estado global
    private val _uiState = MutableStateFlow(UiState())
    val uiState = _uiState.asStateFlow()

    // 🧁 REGISTRO
    fun registrarUsuario(nombre: String, correo: String, clave: String, direccion: String = "") {

        val nuevo = UsuarioUiState(
            nombre = nombre.trim(),
            correo = correo.trim(),
            clave = clave.trim(),
            direccion = direccion.trim()
        )

        // Agrego el nuevo usuario a la lista
        _usuarios.value = _usuarios.value + nuevo

        // Lo dejo como usuario actual
        _uiState.value = UiState(usuarioActual = nuevo)
    }

    // 🔐 LOGIN
    fun validarLogin(correo: String, clave: String): Boolean {
        val email = correo.trim()
        val pass = clave.trim()

        val usuarioEncontrado = _usuarios.value.find {
            it.correo.equals(email, ignoreCase = true) && it.clave == pass
        }

        _uiState.value = UiState(usuarioActual = usuarioEncontrado)

        return usuarioEncontrado != null
    }
}
