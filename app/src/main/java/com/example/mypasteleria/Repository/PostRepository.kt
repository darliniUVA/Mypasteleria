package com.example.mypasteleria.Repository

import com.example.mypasteleria.Data.Model.Post
import com.example.mypasteleria.Data.Remote.ApiService
import com.example.mypasteleria.Data.Remote.RetrofitInstance

open class PostRepository(
    private val api: ApiService = RetrofitInstance.api
) {
    open suspend fun getPosts(): List<Post> {
        return api.getPosts()
    }
}
