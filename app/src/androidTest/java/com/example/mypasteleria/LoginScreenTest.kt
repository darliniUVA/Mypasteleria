package com.example.mypasteleria

import androidx.activity.ComponentActivity
import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import com.example.mypasteleria.ViewModel.UsuarioViewModel
import com.example.mypasteleria.ui.theme.Screens.LoginScreen
import org.junit.Rule
import org.junit.Test

class LoginScreenTest {

    @get:Rule
    val composeRule = createAndroidComposeRule<ComponentActivity>()

    @Test
    fun login_correcto_muestra_navegacion() {

        val viewModel = UsuarioViewModel()

        viewModel.registrarUsuarioLocal(
            nombre = "Maria",
            correo = "maria@gmail.com",
            clave = "123456",
            direccion = "Casa 1"
        )

        composeRule.setContent {
            LoginScreen(
                viewModel = viewModel,
                onNavigate = {}
            )
        }

        composeRule.onNodeWithText("Correo").performTextInput("maria@gmail.com")
        composeRule.onNodeWithText("Contraseña").performTextInput("123456")
        composeRule.onNodeWithText("Iniciar sesión").performClick()

        // Si no crashea → login OK
        composeRule.onNodeWithText("Iniciar sesión").assertExists()
    }

    @Test
    fun login_incorrecto_muestra_error() {

        val viewModel = UsuarioViewModel()

        composeRule.setContent {
            LoginScreen(
                viewModel = viewModel,
                onNavigate = {}
            )
        }

        composeRule.onNodeWithText("Correo").performTextInput("mal@gmail.com")
        composeRule.onNodeWithText("Contraseña").performTextInput("123")
        composeRule.onNodeWithText("Iniciar sesión").performClick()

        composeRule.onNodeWithText("Credenciales incorrectas")
            .assertIsDisplayed()
    }
}


