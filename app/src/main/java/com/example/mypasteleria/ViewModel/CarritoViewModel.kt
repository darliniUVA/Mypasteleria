package com.example.mypasteleria.ViewModel

import androidx.lifecycle.ViewModel
import com.example.mypasteleria.Model.Producto
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class CarritoViewModel : ViewModel() {
    private val _carrito = MutableStateFlow<List<Producto>>(emptyList())
    val carrito: StateFlow<List<Producto>> = _carrito

    fun agregarProducto(producto: Producto) {
        _carrito.value = _carrito.value + producto
    }

    fun eliminarProducto(producto: Producto) {
        _carrito.value = _carrito.value - producto
    }

    fun obtenerTotal(): Int {
        return _carrito.value.sumOf { it.precio }
    }

    fun vaciarCarrito() {
        _carrito.value = emptyList()
    }
}
