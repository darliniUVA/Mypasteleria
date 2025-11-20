package com.example.retrofit.viewmodel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.mypasteleria.Repository.PostRepository
import com.example.mypasteleria.ViewModel.PostViewModel
import kotlinx.coroutines.Dispatchers

class PostViewModelFactory(
    private val repo: PostRepository
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return PostViewModel(
            repository = repo,
            dispatcher = Dispatchers.IO
        ) as T
    }
}