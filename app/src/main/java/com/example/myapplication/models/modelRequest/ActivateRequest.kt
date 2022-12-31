package com.example.myapplication.models.modelRequest

data class ActivateRequest(
    val Email: String,
    val Code: String,
)