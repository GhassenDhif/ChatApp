package com.example.myapplication

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView

class AboutUs : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_about_us)
        val goBack = findViewById<ImageView>(R.id.iv_settingss)

        goBack.setOnClickListener {
            val intent = Intent(this, SettingsFragment::class.java)
            startActivity(intent)
        }
    }
}