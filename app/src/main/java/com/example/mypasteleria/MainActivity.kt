package com.example.mypasteleria

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.core.view.WindowCompat
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.mypasteleria.Repository.PostRepository
import com.example.mypasteleria.ViewModel.PostViewModel
import com.example.mypasteleria.ui.theme.MypasteleriaTheme
import com.example.mypasteleria.ui.theme.Screens.PostScreen
import com.example.retrofit.viewmodel.PostViewModelFactory

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window,false)
        setContent {
            MypasteleriaTheme {
                val vm: PostViewModel = viewModel(
                    factory =
                        PostViewModelFactory(PostRepository())
                )
                PostScreen(viewModel = vm)
            }
        }
    }
}

