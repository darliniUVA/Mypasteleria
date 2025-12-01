package com.example.mypasteleria.ViewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mypasteleria.Data.Model.UsuarioUiState
import com.example.mypasteleria.Data.Model.UsuarioErrores
import com.example.mypasteleria.Data.Model.LoginRequest
import com.example.mypasteleria.Data.Model.LoginResponse
import com.example.mypasteleria.Data.Remote.RetrofitInstance
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class UsuarioViewModel : ViewModel() {

    private val _usuarioState = MutableStateFlow(UsuarioUiState())
    val usuarioState = _usuarioState.asStateFlow()

    private val _erroresState = MutableStateFlow(UsuarioErrores())
    val erroresState = _erroresState.asStateFlow()

    private val _loginBackendState = MutableStateFlow<LoginBackendState?>(null)
    val loginBackendState = _loginBackendState.asStateFlow()

    fun registrarUsuario(nombre: String, correo: String, clave: String, direccion: String): Boolean {
        val errores = UsuarioErrores(
            nombreError = when {
                nombre.isBlank() -> "El nombre es obligatorio"
                !Regex("^[A-Za-zÁÉÍÓÚáéíóúÑñ ]{2,}$").matches(nombre.trim()) ->
                    "Solo letras, mínimo 2 caracteres"
                else -> null
            },

            correoError = when {
                correo.isBlank() -> "El correo es obligatorio"
                !correo.trim().endsWith("@gmail.com") -> "Debe ser un correo @gmail.com"
                else -> null
            },

            claveError = when {
                clave.isBlank() -> "La contraseña es obligatoria"
                clave.length < 6 -> "Mínimo 6 caracteres"
                else -> null
            },

            direccionError = when {
                direccion.isBlank() -> "La dirección es obligatoria"
                !Regex("^[A-Za-zÁÉÍÓÚáéíóúÑñ0-9 ]{5,}$").matches(direccion.trim()) ->
                    "Dirección inválida"
                else -> null
            }
        )

        _erroresState.value = errores

        val hayErrores = listOf(
            errores.nombreError,
            errores.correoError,
            errores.claveError,
            errores.direccionError
        ).any { it != null }

        if (hayErrores) return false

        _usuarioState.value = UsuarioUiState(
            nombre = nombre.trim(),
            correo = correo.trim(),
            clave = clave.trim(),
            direccion = direccion.trim()
        )

        return true
    }

    fun validarLogin(correo: String, clave: String): Boolean {
        val registrado = _usuarioState.value
        if (registrado.correo.isBlank() || registrado.clave.isBlank()) return false
        return registrado.correo.equals(correo.trim(), ignoreCase = true) &&
                registrado.clave == clave.trim()
    }

    fun actualizarPerfil(nombre: String, correo: String, direccion: String) {
        _usuarioState.update { actual ->
            actual.copy(
                nombre = nombre.trim(),
                correo = correo.trim(),
                direccion = direccion.trim()
            )
        }
    }

    fun cerrarSesion() {
        _usuarioState.value = UsuarioUiState()
    }

    fun loginConBackend(correo: String, clave: String, onResult: (LoginBackendState) -> Unit) {
        val request = LoginRequest(username = correo.trim(), password = clave.trim())

        _loginBackendState.value = LoginBackendState(true, null, null)
        onResult(_loginBackendState.value!!)

        viewModelScope.launch {
            try {
                val response = RetrofitInstance.api.login(request)
                val state = LoginBackendState(false, null, response)
                _loginBackendState.value = state
                onResult(state)
            } catch (e: Exception) {
                val state = LoginBackendState(false, "Error iniciar sesión", null)
                _loginBackendState.value = state
                onResult(state)
            }
        }
    }
}

data class LoginBackendState(
    val loading: Boolean,
    val error: String?,
    val response: LoginResponse?
)
