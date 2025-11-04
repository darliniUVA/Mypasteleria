package com.example.mypasteleria.ui.theme

import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color


private val PasteleriaColorScheme = lightColorScheme(
    primary      = Color(0xFF8B4513),
    onPrimary    = Color.White,
    secondary    = Color(0xFFFFC0CB),
    onSecondary  = Color(0xFF5D4037),
    background   = Color(0xFFFFF5E1),
    onBackground = Color(0xFF5D4037),
    surface      = Color(0xFFFFF5E1),
    onSurface    = Color(0xFF5D4037)
)


@Composable
fun MypasteleriaTheme(content: @Composable () -> Unit) {
    MaterialTheme(
        colorScheme = PasteleriaColorScheme,
        typography  = Typography(),
        content     = content
    )
}
