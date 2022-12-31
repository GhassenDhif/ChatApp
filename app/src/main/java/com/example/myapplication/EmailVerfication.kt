package com.example.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import com.example.myapplication.api.RetrofitBuilder
import com.example.myapplication.models.modelResponse.EmailResponse


class EmailVerfication : AppCompatActivity() {

    private lateinit var Bverif: Button
    private lateinit var Everif: EditText

    val apiInterface = RetrofitBuilder.create()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_email_verfication)
        Bverif = findViewById(R.id.b1)
        Everif = findViewById(R.id.Edt1)

        Bverif.setOnClickListener {
            val code = Everif.text.toString()
            if (code.isEmpty()) {
                Everif.error = "Please enter your Email"
                Everif.requestFocus()
                return@setOnClickListener
            } else {
                doVerifEmail()
            }
        }

    }

    private fun doVerifEmail() {
        val map: HashMap<String, String> = HashMap()
        map["Email"] = Everif.text.toString()
        val call = apiInterface.executeEmail(map)
        call?.enqueue(object : retrofit2.Callback<EmailResponse> {
            override fun onResponse(
                call: retrofit2.Call<EmailResponse>,
                response: retrofit2.Response<EmailResponse>
            ) {
                if (response.isSuccessful) {
                    val intent = Intent(this@EmailVerfication, resetpassword::class.java)
                    startActivity(intent)
                }
            }

            override fun onFailure(call: retrofit2.Call<EmailResponse>, t: Throwable) {
                Everif.error = "Please enter your Email"
                Everif.requestFocus()
            }
        })
    }
}








