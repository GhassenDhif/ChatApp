package com.example.myapplication.models.modelResponse

import com.example.myapplication.models.User


data class LoginResponse(
    val message: String,
    val token: String,
    val _id: String,
)


