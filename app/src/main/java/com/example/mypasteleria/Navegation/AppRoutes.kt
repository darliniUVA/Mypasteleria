package com.example.mypasteleria.Navegation

sealed class AppRoutes(val route: String) {
    object Login : AppRoutes("login")
    object Registro : AppRoutes("registro")
    object Home : AppRoutes("home")
    object Catalogo : AppRoutes("catalogo")
    object Perfil : AppRoutes("perfil")
}
