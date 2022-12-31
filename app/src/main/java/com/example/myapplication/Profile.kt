package com.example.myapplication

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView
import com.example.myapplication.api.RetrofitBuilder
import com.example.myapplication.models.modelResponse.ShowResponse
import com.google.android.material.textfield.TextInputEditText
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response



class Profile : AppCompatActivity() {
    lateinit var btn:Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)
        val etName=findViewById<TextView>(R.id.TX1)
        val etEmail=findViewById<TextView>(R.id.TX2)
        val etEmail1=findViewById<TextView>(R.id.TX3)
        val etName1=findViewById<TextView>(R.id.TX4)
        val Genre=findViewById<TextView>(R.id.TX5)
        val date=findViewById<TextView>(R.id.TX6)

        val Class=findViewById<TextView>(R.id.TX7)
        val filiere=findViewById<TextView>(R.id.TX8)

        btn = findViewById(R.id.b4)

//get user connected
        val sharedPref= this.getSharedPreferences("userConnected", Context.MODE_PRIVATE)
        val currentUser_id = sharedPref?.getString("_id","default value").toString()
        val currentUser_username = sharedPref?.getString("username","default value").toString()
        val currentUser_image = sharedPref?.getString("image","default value").toString()
        val currentUser_email = sharedPref?.getString("email","default value").toString()
        val currentUser_role = sharedPref?.getString("role","default value").toString()
        val currentUser_filiere = sharedPref?.getString("filiere","default value").toString()
        val currentUser_date_naissance = sharedPref?.getString("date-naissance","default value").toString()
        val currentUser_classe = sharedPref?.getString("classe","default value").toString()
        val currentUser_genre= sharedPref?.getString("genre","default value").toString()
        etEmail.setText(currentUser_email)
        etName.setText(currentUser_username)
        etName1.setText(currentUser_role)
        etEmail1.setText(currentUser_email)
        Genre.setText(currentUser_genre)
        date.setText(currentUser_date_naissance)
        Class.setText(currentUser_classe)
        filiere.setText(currentUser_filiere)

        btn.setOnClickListener {
            val intent = Intent(this, fragmentProfile::class.java)
            startActivity(intent)
        }


    }
}















