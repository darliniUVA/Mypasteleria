package com.example.mypasteleria.ViewModel

import androidx.lifecycle.ViewModel
import com.example.mypasteleria.Data.Model.Producto
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class CarritoViewModel : ViewModel() {

    private val _carrito = MutableStateFlow<List<Producto>>(emptyList())
    val carrito: StateFlow<List<Producto>> = _carrito

    fun agregarProducto(producto: Producto): Boolean {
        val existente = _carrito.value.find { it.codigo == producto.codigo }
        if (existente != null) {
            val cantidadActual = _carrito.value.count { it.codigo == producto.codigo }
            if (cantidadActual >= producto.stock) {
                return false
            }
        }
        _carrito.value = _carrito.value + producto
        return true
    }
    fun eliminarProducto(producto: Producto) {
        _carrito.value = _carrito.value - producto
    }

    fun obtenerTotal(): Int {
        return _carrito.value.sumOf { it.precio }
    }
}
