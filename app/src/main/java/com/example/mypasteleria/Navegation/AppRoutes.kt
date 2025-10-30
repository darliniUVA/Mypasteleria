package com.example.mypasteleria.Navegation

sealed class AppRoutes(val route: String) {
    object Home : AppRoutes("home")
    object Catalogo : AppRoutes("catalogo")
    object Perfil : AppRoutes("perfil")
}
