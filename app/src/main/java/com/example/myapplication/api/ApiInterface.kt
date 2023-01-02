package com.example.myapplication.api

import com.example.myapplication.models.User
import com.example.myapplication.models.modelResponse.*
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface ApiInterface {

    @Headers("Content-Type:application/json")
    @POST("login")
    fun executeLogin(@Body map: HashMap<String, String>): Call<User>?
    @Headers("Content-Type:application/json")
    @POST("register")
    fun executeSignup(@Body map: HashMap<String?, String?>): Call<SignupResponse>?
    @Headers("Content-Type:application/json")
    @POST("Activation")
    fun executeActivate(@Body map: HashMap<String, String>): Call<ActivateResponse>?
    @Headers("Content-Type:application/json")
    @POST("findEmail")
    fun executeEmail(@Body map: HashMap<String, String>): Call<EmailResponse>?
    @Headers("Content-Type:application/json")
    @POST("resetpassword")
    fun executeReset(@Body map: HashMap<String, String>): Call<ResetResponse>?
    @Headers("Content-Type:application/json")
    @GET("api/user/show/{userId}")
    fun getUser(@Path("userId") userId :String): Call<ShowResponse>?
    @Headers("Content-Type:application/json")
    @PUT("api/user/update")
    fun updateUser(@Body map: HashMap<String?, String?>): Call<User>?
    @Headers("Content-Type:application/json")
    @GET("api/user/index")
    fun getUsers(): Call<ArrayList<User>>
    @Headers("Content-Type:application/json")
    @DELETE("api/user/delete/{userId}")
    fun deleteUser(@Path("userId") userId :String): Call<DeteleResponse>?
}