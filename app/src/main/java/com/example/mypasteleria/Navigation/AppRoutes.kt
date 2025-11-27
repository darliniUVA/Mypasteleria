sealed class AppRoutes(val route: String) {
    object Home : AppRoutes("home")
    object Login : AppRoutes("login")
    object Perfil : AppRoutes("perfil")
    object Carrito : AppRoutes("carrito")
}
