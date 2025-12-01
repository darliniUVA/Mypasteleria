package com.example.mypasteleria.Data.Model

data class LoginRequest(
    val username: String,
    val password: String
)

data class LoginResponse(
    val role: String,
    val token: String,
    val username: String
)
