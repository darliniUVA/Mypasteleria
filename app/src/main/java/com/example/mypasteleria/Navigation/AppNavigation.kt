package com.example.mypasteleria.Navigation

import LoginScreen
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.mypasteleria.ViewModel.UsuarioViewModel
import com.example.mypasteleria.ui.theme.Screens.HomeScreen
import com.example.mypasteleria.ui.theme.Screens.RegistroScreen

@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    val usuarioViewModel: UsuarioViewModel = viewModel()

    NavHost(
        navController = navController,
        startDestination = "login"
    ) {
        composable("login") {
            LoginScreen(
                viewModel = usuarioViewModel,
                onNavigate = { ruta -> navController.navigate(ruta) }
            )
        }

        composable("registro") {
            RegistroScreen(
                viewModel = usuarioViewModel,
                onNavigate = { ruta -> navController.navigate(ruta) }
            )
        }

        composable("home") {
            HomeScreen(
                onNavigate = { ruta -> navController.navigate(ruta) }
            )
        }
    }
}
