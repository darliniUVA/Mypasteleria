package com.example.mypasteleria.Navigation

sealed class AppRoutes(val route: String) {
    object Login : AppRoutes("login")
    object Registro : AppRoutes("registro")
    object Home : AppRoutes("home")
    object Catalogo : AppRoutes("catalogo")
    object Carrito : AppRoutes("carrito")
    object Perfil : AppRoutes("perfil")
}
