package com.example.mypasteleria.ViewModel

import androidx.lifecycle.ViewModel
import com.example.mypasteleria.Navegation.AppRoutes
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class MainViewModel : ViewModel() {
    private val _selectedScreen = MutableStateFlow(AppRoutes.Home.route)
    val selectedScreen = _selectedScreen.asStateFlow()

    fun navigateTo(route: String) {
        _selectedScreen.value = route
    }
}
