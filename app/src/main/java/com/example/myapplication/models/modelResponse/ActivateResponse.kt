package com.example.myapplication.models.modelResponse

data class ActivateResponse (
    val token: String,
    val message: String,
        ) {
    val success: Boolean
        get() = message == "Account activated successfully"
}
