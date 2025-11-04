package com.example.mypasteleria.Navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.mypasteleria.ViewModel.CarritoViewModel
import com.example.mypasteleria.ViewModel.UsuarioViewModel
import com.example.mypasteleria.ui.theme.Screens.*

@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    val usuarioViewModel = remember { UsuarioViewModel() }
    val carritoViewModel = remember { CarritoViewModel() }

    NavHost(navController, startDestination = AppRoutes.Login.route) {
        composable(AppRoutes.Login.route) {
            LoginScreen(usuarioViewModel) { route ->
                navController.navigate(route) {
                    popUpTo(AppRoutes.Login.route) { inclusive = true }
                }
            }
        }
        composable(AppRoutes.Registro.route) {
            RegistroScreen(usuarioViewModel) { route ->
                navController.navigate(route) {
                    popUpTo(AppRoutes.Login.route) { inclusive = true }
                }
            }
        }
        composable(AppRoutes.Home.route) {
            HomeScreen { navController.navigate(it) }
        }
        composable(AppRoutes.Catalogo.route) {
            CatalogoScreen()
        }
        composable(AppRoutes.Perfil.route) {
            PerfilScreen(usuarioViewModel) { navController.navigate(it) }
        }
        composable(AppRoutes.Carrito.route) {
            CarritoScreen(carritoViewModel)
        }
    }
}
