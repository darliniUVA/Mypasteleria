package com.example.mypasteleria.ViewModel

import androidx.lifecycle.ViewModel
import com.example.mypasteleria.Data.Model.UsuarioUiState
import com.example.mypasteleria.Data.Model.UsuarioErrores
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class UsuarioViewModel : ViewModel() {

    private val _usuarioState = MutableStateFlow(UsuarioUiState())
    val usuarioState = _usuarioState.asStateFlow()

    private val _erroresState = MutableStateFlow(UsuarioErrores())
    val erroresState = _erroresState.asStateFlow()

    fun registrarUsuario(
        nombre: String,
        correo: String,
        clave: String,
        direccion: String
    ): Boolean {

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
                    "Dirección inválida (mínimo 5 caracteres, solo letras y números)"
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

        // 👉 Guardar usuario para Perfil
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
}
