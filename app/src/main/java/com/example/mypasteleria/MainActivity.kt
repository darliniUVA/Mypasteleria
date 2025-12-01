package com.example.mypasteleria

import android.Manifest
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.Surface
import androidx.compose.ui.graphics.Color
import androidx.core.app.ActivityCompat
import androidx.navigation.compose.rememberNavController
import com.example.mypasteleria.Navigation.AppNavigation
import com.example.mypasteleria.ui.theme.MypasteleriaTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()


        setContent {
            MypasteleriaTheme {
                Surface(color = Color.White) {
                    val navController = rememberNavController()
                    AppNavigation()
                }
            }
        }
    }
}
