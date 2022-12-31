package com.example.myapplication.models.modelRequest

data class ResetRequest(
    val Email: String,
    val Code: String,
    val Password: String,
    val token: String
)
