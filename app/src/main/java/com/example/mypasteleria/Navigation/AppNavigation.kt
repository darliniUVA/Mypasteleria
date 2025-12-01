package com.example.mypasteleria.Navigation

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.mypasteleria.ViewModel.UsuarioViewModel
import com.example.mypasteleria.ViewModel.CarritoViewModel
import com.example.mypasteleria.ui.theme.Screens.CarritoScreen
import com.example.mypasteleria.ui.theme.Screens.CatalogoScreen
import com.example.mypasteleria.ui.theme.Screens.HomeScreen
import com.example.mypasteleria.ui.theme.Screens.LoginScreen
import com.example.mypasteleria.ui.theme.Screens.RegistroScreen
import com.example.mypasteleria.ui.theme.Screens.PerfilScreen

@Composable
fun AppNavigation() {

    val navController = rememberNavController()
    val usuarioViewModel: UsuarioViewModel = viewModel()
    val carritoViewModel: CarritoViewModel = viewModel()

    NavHost(
        navController = navController,
        startDestination = AppRoutes.Login.route
    ) {

        composable(AppRoutes.Login.route) {
            LoginScreen(
                viewModel = usuarioViewModel,
                onNavigate = { ruta ->
                    navController.navigate(ruta) {
                        popUpTo(AppRoutes.Login.route) { inclusive = true }
                        launchSingleTop = true
                    }
                }
            )
        }

        composable(AppRoutes.Registro.route) {
            RegistroScreen(
                viewModel = usuarioViewModel,
                onNavigate = { ruta ->
                    navController.navigate(ruta)
                }
            )
        }

        composable(AppRoutes.Home.route) {
            HomeScreen(
                carritoViewModel = carritoViewModel,
                onNavigate = { ruta ->
                    navController.navigate(ruta)
                }
            )
        }

        composable(AppRoutes.Catalogo.route) {
            CatalogoScreen(
                carritoViewModel = carritoViewModel,
                onNavigate = { ruta ->
                    navController.navigate(ruta)
                }
            )
        }

        composable(AppRoutes.Carrito.route) {
            CarritoScreen(
                viewModel = carritoViewModel,
                onNavigate = { ruta ->
                    navController.navigate(ruta)
                }
            )
        }

        composable(AppRoutes.Perfil.route) {
            PerfilScreen(
                viewModel = usuarioViewModel,
                onNavigate = { ruta ->
                    navController.navigate(ruta)
                }
            )
        }
    }
}
