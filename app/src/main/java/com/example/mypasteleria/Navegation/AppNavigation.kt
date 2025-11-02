package com.example.mypasteleria.Navegation

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.mypasteleria.ViewModel.CarritoViewModel
import com.example.mypasteleria.ViewModel.UsuarioViewModel
import com.example.mypasteleria.ui.theme.Screens.CarritoScreen
import com.example.mypasteleria.ui.theme.Screens.CatalogoScreen
import com.example.mypasteleria.ui.theme.Screens.HomeScreen
import com.example.mypasteleria.ui.theme.Screens.LoginScreen
import com.example.mypasteleria.ui.theme.Screens.PerfilScreen
import com.example.mypasteleria.ui.theme.Screens.RegistroScreen

@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    NavHost(navController, startDestination = AppRoutes.Login.route) {
        composable(AppRoutes.Login.route) {
            LoginScreen { navController.navigate(it) }
        }
        composable(AppRoutes.Registro.route) {
            val usuarioViewModel: UsuarioViewModel = viewModel()
            RegistroScreen(usuarioViewModel) { navController.navigate(it) }
        }
        composable(AppRoutes.Home.route) {
            HomeScreen { navController.navigate(it) }
        }
        composable(AppRoutes.Catalogo.route) {
            CatalogoScreen()
        }
        composable(AppRoutes.Perfil.route) {
            val usuarioViewModel: UsuarioViewModel = viewModel()
            PerfilScreen(usuarioViewModel) { navController.navigate(it) }
        }
        composable("carrito") {
            val carritoViewModel: CarritoViewModel = viewModel()
            CarritoScreen(carritoViewModel)
        }
    }
}
