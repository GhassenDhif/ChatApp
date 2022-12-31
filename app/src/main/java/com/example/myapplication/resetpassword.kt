package com.example.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import com.example.myapplication.api.RetrofitBuilder
import com.example.myapplication.models.modelResponse.ResetResponse

class resetpassword : AppCompatActivity() {
    private lateinit var Bverif: Button
    private lateinit var Cverif: EditText
    private lateinit var Pverif: EditText

    val apiInterface = RetrofitBuilder.create()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_resetpassword)

        Bverif = findViewById(R.id.b1)
        Cverif = findViewById(R.id.Edt1)
        Pverif = findViewById(R.id.Edt2)

        Bverif.setOnClickListener {
            val code = Cverif.text.toString()
            val pass = Pverif.text.toString()
            if (code.isEmpty()) {
                Cverif.error = "Please enter your Code"
                Cverif.requestFocus()
                return@setOnClickListener
            } else if (pass.isEmpty()) {
                Pverif.error = "Please enter your Password"
                Pverif.requestFocus()
                return@setOnClickListener
            } else {
                changerPass()
            }
        }
    }

    private fun changerPass() {
        val map: HashMap<String, String> = HashMap()
        map["ActivationCode"] = Cverif.text.toString()
        map["Password"] = Pverif.text.toString()
        val call = apiInterface.executeReset(map)
        call?.enqueue(object : retrofit2.Callback<ResetResponse> {
            override fun onResponse(
                call: retrofit2.Call<ResetResponse>,
                response: retrofit2.Response<ResetResponse>
            ) {
                if (response.isSuccessful) {
                    val intent = Intent(this@resetpassword, Login::class.java)
                    startActivity(intent)
                }
            }

            override fun onFailure(call: retrofit2.Call<ResetResponse>, t: Throwable) {
                Cverif.error = "Please enter your Code"
                Cverif.requestFocus()
            }
        })
    }
}