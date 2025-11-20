package com.example.mypasteleria.Repository

import com.example.mypasteleria.Data.Model.Post
import com.example.mypasteleria.Data.Remote.RetrofitInstance

open class PostRepository {
    open suspend fun getPosts(): List<Post>{
        return RetrofitInstance.api.getPosts()
    }
}