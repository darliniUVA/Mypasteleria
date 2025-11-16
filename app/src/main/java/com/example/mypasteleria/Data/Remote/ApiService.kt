package com.example.mypasteleria.Data.Remote

import com.example.mypasteleria.Data.Model.Post
import retrofit2.http.GET

interface ApiService {
    @GET("/posts")
    suspend fun getPosts(): List<Post>
}