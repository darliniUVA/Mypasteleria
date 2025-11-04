package com.example.mypasteleria.Navigation

sealed class AppRoutes(val route: String) {
    object Login    : AppRoutes("login")
    object Registro : AppRoutes("registro")
    object Home     : AppRoutes("home")
    object Catalogo : AppRoutes("catalogo")
    object Perfil   : AppRoutes("perfil")
    object Carrito  : AppRoutes("carrito")
    object Resenas  : AppRoutes("resenas")
    object Blog     : AppRoutes("blog")
}
