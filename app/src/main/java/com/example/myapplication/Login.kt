package com.example.myapplication

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.api.RetrofitBuilder
import com.example.myapplication.models.User
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response



const val PASSWORD = "PASSWORD"
const val EMAIL = "EMAIL"
const val PREF = "CHAT"



class Login : AppCompatActivity() {

    private lateinit var email: EditText
    private lateinit var password: EditText
    private val apiInterface = RetrofitBuilder.create()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)


        val loginButton = findViewById<Button>(R.id.B1)
        email = findViewById(R.id.T1)
        password = findViewById(R.id.T2)
        loginButton.setOnClickListener {
            val email = email.text.toString()
            val password = password.text.toString()
            if (email.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "Please fill all the fields", Toast.LENGTH_SHORT).show()
            } else {
                doLogin()
            }
        }
    }

    private fun doLogin() {
        var sharedPreference =this.getSharedPreferences("userConnected", Context.MODE_PRIVATE)
        val map: HashMap<String, String> = HashMap()
        map["Username"] = email.text.toString()
        map["Password"] = password.text.toString()
        CoroutineScope(Dispatchers.IO).launch {
            apiInterface.executeLogin(map)?.enqueue(object : Callback<User> {
                override fun onResponse(call: Call<User>, response: Response<User>) {
                    Log.e("response", response.message())
                    if (response.isSuccessful) {
                        val loginResponse: User? = response.body()
                        if (loginResponse != null) {
                            Log.d("TAG", "onResponse: ${loginResponse.Username}")
                            Log.e("TAG" , "onResponse: ${loginResponse._id}")
                            val editor = sharedPreference.edit()
                            editor!!.putString("_id",loginResponse._id)
                            editor!!.putString("username",loginResponse.Username)
                            editor!!.putString("email",loginResponse.Email)
                            editor!!.putString("role",loginResponse.Role)
                            editor!!.putString("genre",loginResponse.Genre)
                            editor!!.putString("filiere",loginResponse.Filiere)
                            editor!!.putString("date-naissance",loginResponse.Date_Naissance)
                            editor!!.putString("classe",loginResponse.Classe)
                            editor!!.putString("image" ,loginResponse.Image)
                            editor.commit()
                            if (loginResponse.Role == "User") {
                                val intent = Intent(this@Login, Profile::class.java)
                                startActivity(intent)
                                finish()

                            } else {
                                val intent = Intent(this@Login, Admin::class.java)
                                startActivity(intent)
                                finish()
                            }
                            } else {
                            if (loginResponse != null) {
                                Toast.makeText( this@Login, "login failed", Toast.LENGTH_SHORT).show()
                            }
                            }
                        }
                    }
                override fun onFailure(call: Call<User>, t: Throwable) {
                    Toast.makeText(this@Login, t.message, Toast.LENGTH_SHORT).show()
                }

            })
        }
    }
}


























