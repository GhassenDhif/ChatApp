package com.example.myapplication.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class User(
 @SerializedName("_id") @Expose val _id: String,
 @SerializedName("Username")
 var Username: String,
 @SerializedName("Email")
 var Email: String,
 @SerializedName("Password")
 var Password: String,
 @SerializedName("Image")
 var Image: String,
 @SerializedName("Genre")
 var Genre: String,
 @SerializedName("Date_Naissance")
 var Date_Naissance: String,
 @SerializedName("Role")
 var Role: String ="User",
 @SerializedName("Classe")
 var Classe: String,
 @SerializedName("Filiere")
 var Filiere: String,
 @SerializedName("IsActive")
 var IsActive: Boolean,
 @SerializedName("Banne")
 var Banne: Boolean,


 var __v: Int


)
