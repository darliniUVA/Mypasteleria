package com.example.mypasteleria.Navigation

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.mypasteleria.ViewModel.CarritoViewModel
import com.example.mypasteleria.ViewModel.PostViewModel
import com.example.mypasteleria.ViewModel.UsuarioViewModel
import com.example.mypasteleria.ui.theme.Screens.*


@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    val usuarioViewModel: UsuarioViewModel = viewModel()
    val carritoViewModel: CarritoViewModel = viewModel()

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
            HomeScreen(
                carritoViewModel = carritoViewModel,
                onNavigate = { navController.navigate(it) }
            )
        }

        composable(AppRoutes.Catalogo.route) {
            CatalogoScreen(
                carritoViewModel = carritoViewModel,
                onNavigate = { navController.navigate(it) }
            )
        }

        composable(AppRoutes.Carrito.route) {
            CarritoScreen(
                viewModel = carritoViewModel,
                onNavigate = { navController.navigate(it) }
            )
        }

        composable(AppRoutes.Perfil.route) {
            PerfilScreen(usuarioViewModel) { navController.navigate(it) }
        }

        composable(AppRoutes.Resenas.route) {
            ResenasScreen { route -> navController.navigate(route) }
        }

        composable(AppRoutes.Blog.route) {
            BlogScreen { route -> navController.navigate(route) }
        }
        composable(AppRoutes.PostScreen.route) {
            val postViewModel: PostViewModel = viewModel()
            PostScreen(viewModel = postViewModel)
        }
    }
}
