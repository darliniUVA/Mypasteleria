package com.example.mypasteleria.ViewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mypasteleria.Data.Model.Post
import com.example.mypasteleria.Repository.PostRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

open class PostViewModel(
    private val repository: PostRepository,
    private val dispatcher: CoroutineDispatcher
) : ViewModel() {
    private val _postList =
        MutableStateFlow<List<Post>>(emptyList())
    open val postList: StateFlow<List<Post>> = _postList
    init { fetchPosts() }
    open fun fetchPosts() {
        viewModelScope.launch(dispatcher) {
            try {
                _postList.value = repository.getPosts()
            } catch (e: Exception) { }
        }
    }
}