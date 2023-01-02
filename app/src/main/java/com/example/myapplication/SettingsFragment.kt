package com.example.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView

class SettingsFragment : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings_fragment)

        val goback = findViewById<ImageView>(R.id.iv_settings)
        val Logout = findViewById<ImageView>(R.id.Quiter)
        val ContactAdmin = findViewById<ImageView>(R.id.messageicon)
        val aboutus = findViewById<ImageView>(R.id.Aboutas)
        goback.setOnClickListener {
            val intent = Intent(this, Profile::class.java)
            startActivity(intent)
        }

        ContactAdmin.setOnClickListener {
            val intent = Intent(this, ContactAdmins::class.java)
            startActivity(intent)
        }


        aboutus.setOnClickListener {
            val intent = Intent(this, AboutUs::class.java)
            startActivity(intent)
        }

        Logout.setOnClickListener() {
            val intent = Intent(this, Login::class.java)
            startActivity(intent)
        }
    }
}