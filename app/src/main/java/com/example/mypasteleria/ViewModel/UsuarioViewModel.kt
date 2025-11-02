package com.example.mypasteleria.ViewModel

import androidx.lifecycle.ViewModel
import com.example.mypasteleria.Model.UsuarioErrores
import com.example.mypasteleria.Model.UsuarioUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class UsuarioViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(UsuarioUiState())
    val uiState = _uiState.asStateFlow()

    private val _errores = MutableStateFlow(UsuarioErrores())
    val errores = _errores.asStateFlow()

    fun actualizarCampo(campo: String, valor: String) {
        when (campo) {
            "nombre" -> _uiState.value = _uiState.value.copy(nombre = valor)
            "correo" -> _uiState.value = _uiState.value.copy(correo = valor)
            "clave" -> _uiState.value = _uiState.value.copy(clave = valor)
            "direccion" -> _uiState.value = _uiState.value.copy(direccion = valor)
        }
    }

    fun validarFormulario(): Boolean {
        val erroresAct = UsuarioErrores(
            nombreError = if (_uiState.value.nombre.isBlank()) "Campo obligatorio" else null,
            correoError = if (!Regex("^[A-Za-z0-9+_.-]+@(.+)$").matches(_uiState.value.correo)) "Correo inválido" else null,
            claveError = if (_uiState.value.clave.length < 6) "Mínimo 6 caracteres" else null,
            direccionError = if (_uiState.value.direccion.isBlank()) "Campo obligatorio" else null
        )
        _errores.value = erroresAct
        return erroresAct == UsuarioErrores()
    }
    fun limpiarUsuario() {
        _uiState.value = UsuarioUiState()
    }
}
