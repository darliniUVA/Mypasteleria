package com.example.mypasteleria.ViewModel

import android.app.Application
import android.content.Context
import androidx.lifecycle.AndroidViewModel
import com.example.mypasteleria.Model.Producto
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.serialization.encodeToString
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json

class CarritoViewModel(application: Application) : AndroidViewModel(application) {
    private val context = application.applicationContext
    private val prefs = context.getSharedPreferences("carrito_prefs", Context.MODE_PRIVATE)

    private val _carrito = MutableStateFlow<List<Producto>>(cargarCarrito())
    val carrito: StateFlow<List<Producto>> = _carrito

    fun agregarProducto(producto: Producto) {
        _carrito.value = _carrito.value + producto
        guardarCarrito()
    }

    fun eliminarProducto(producto: Producto) {
        _carrito.value = _carrito.value - producto
        guardarCarrito()
    }

    fun obtenerTotal(): Int {
        return _carrito.value.sumOf { it.precio }
    }

    fun vaciarCarrito() {
        _carrito.value = emptyList()
        guardarCarrito()
    }

    private fun guardarCarrito() {
        val json = Json.encodeToString(_carrito.value)
        prefs.edit().putString("carrito_guardado", json).apply()
    }

    private fun cargarCarrito(): List<Producto> {
        val json = prefs.getString("carrito_guardado", null) ?: return emptyList()
        return try {
            Json.decodeFromString(json)
        } catch (e: Exception) {
            emptyList()
        }
    }
}
