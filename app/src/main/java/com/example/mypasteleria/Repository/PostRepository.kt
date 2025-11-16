package com.example.mypasteleria.Repository

import com.example.mypasteleria.Data.Model.Post
import com.example.mypasteleria.Data.Remote.RetrofitInstance

class PostRepository {
    suspend fun getPosts(): List<Post>{
        return RetrofitInstance.api.getPosts()
    }
}