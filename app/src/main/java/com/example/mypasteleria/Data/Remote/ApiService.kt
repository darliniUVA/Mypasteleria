package com.example.mypasteleria.Data.Remote

import com.example.mypasteleria.Data.Model.LoginRequest
import com.example.mypasteleria.Data.Model.LoginResponse
import com.example.mypasteleria.Data.Model.RegisterResponse
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiService {

    @POST("auth/login")
    suspend fun login(@Body request: LoginRequest): LoginResponse

    @POST("auth/register")
    suspend fun register(@Body request: LoginRequest): RegisterResponse
}
