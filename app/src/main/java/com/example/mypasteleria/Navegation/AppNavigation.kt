package com.example.mypasteleria.Navegation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.mypasteleria.ui.theme.Screens.CatalogoScreen
import com.example.mypasteleria.ui.theme.Screens.HomeScreen
import com.example.mypasteleria.ui.theme.Screens.PerfilScreen

@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    NavHost(navController, startDestination = AppRoutes.Home.route) {
        composable(AppRoutes.Home.route) { HomeScreen { navController.navigate(it) } }
        composable(AppRoutes.Catalogo.route) { CatalogoScreen() }
        composable(AppRoutes.Perfil.route) { PerfilScreen() }
    }
}
