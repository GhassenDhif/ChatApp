package com.example.myapplication.models.modelResponse

import com.example.myapplication.models.User
import com.google.gson.annotations.SerializedName

data class UpdateResponse(
    @SerializedName("Username")
    var Username: String,
    @SerializedName("Email")
    var Email: String,
    @SerializedName("Image")
    var Image: String ="fsdfsdfsd",
    @SerializedName("Genre")
    var Genre: String,
    @SerializedName("Date_Naissance")
    var Date_Naissance: String,
)
