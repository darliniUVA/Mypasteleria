package com.example.mypasteleria.ViewModel

import androidx.lifecycle.ViewModel
import com.example.mypasteleria.Data.Model.UsuarioUiState
import com.example.mypasteleria.Data.Model.UsuarioErrores
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class UsuarioViewModel : ViewModel() {

    // Usuario registrado / logueado
    private val _usuarioState = MutableStateFlow(UsuarioUiState())
    val usuarioState = _usuarioState.asStateFlow()

    // Errores de validación del formulario de registro
    private val _erroresState = MutableStateFlow(UsuarioErrores())
    val erroresState = _erroresState.asStateFlow()

    // 🧁 REGISTRO CON VALIDACIÓN
    fun registrarUsuario(
        nombre: String,
        correo: String,
        clave: String,
        direccion: String
    ): Boolean {

        val errores = UsuarioErrores(
            nombreError = if (nombre.isBlank()) "El nombre es obligatorio" else null,
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
            direccionError = if (direccion.isBlank()) "La dirección es obligatoria" else null
        )

        // Actualizo los errores para que la UI los muestre
        _erroresState.value = errores

        // ¿Hay algún error?
        val hayErrores = listOf(
            errores.nombreError,
            errores.correoError,
            errores.claveError,
            errores.direccionError
        ).any { it != null }

        if (hayErrores) {
            return false   // ❌ no se registra
        }

        // ✅ Datos válidos: guardo usuario
        _usuarioState.value = UsuarioUiState(
            nombre = nombre.trim(),
            correo = correo.trim(),
            clave = clave.trim(),
            direccion = direccion.trim()
        )

        return true
    }

    // 🔐 LOGIN (contra el usuario registrado)
    fun validarLogin(correo: String, clave: String): Boolean {
        val registrado = _usuarioState.value

        if (registrado.correo.isBlank() || registrado.clave.isBlank()) return false

        return registrado.correo.equals(correo.trim(), ignoreCase = true) &&
                registrado.clave == clave.trim()
    }

    // ✏️ PERFIL
    fun actualizarPerfil(nombre: String, correo: String, direccion: String) {
        _usuarioState.update { actual ->
            actual.copy(
                nombre = nombre.trim(),
                correo = correo.trim(),
                direccion = direccion.trim()
            )
        }
    }

    // 🚪 CERRAR SESIÓN
    fun cerrarSesion() {
        _usuarioState.value = UsuarioUiState()
    }
}
