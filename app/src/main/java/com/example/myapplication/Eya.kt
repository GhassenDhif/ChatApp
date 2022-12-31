package com.example.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.myapplication.api.RetrofitBuilder
import com.example.myapplication.models.modelResponse.ActivateResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Eya : AppCompatActivity() {
    private lateinit var Bverif: Button
    private lateinit var Cverif: EditText

    val apiInterface = RetrofitBuilder.create()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_eya)
        Bverif = findViewById(R.id.b1)
        Cverif = findViewById(R.id.Edt1)



        Bverif.setOnClickListener {
            val code = Cverif.text.toString()
            if (code.isEmpty()) {
                Cverif.error = "Please enter your code"
                Cverif.requestFocus()
                return@setOnClickListener
            } else {
                doVerif()
            }
        }

    }
    private fun doVerif() {
        val map: HashMap<String, String> = HashMap()
        map["ActivationCode"] = Cverif.text.toString()
        val call = apiInterface.executeActivate(map)
        Log.e(map["ActivationCode"].toString(), "doVerif: ")
        call?.enqueue(object : Callback<ActivateResponse> {
            override fun onResponse(call: Call<ActivateResponse>, response: Response<ActivateResponse>) {
                if (response.isSuccessful) {
                    val intent = Intent(this@Eya, Profile::class.java)
                    startActivity(intent)
                    val ActivateResponse = response.body()
                    if (ActivateResponse != null) {
                        if (ActivateResponse.success) {
                            val intent = Intent(this@Eya, Profile::class.java)
                            startActivity(intent)
                        } else {
                            Toast.makeText(
                                this@Eya,
                                "Code wrong",
                                Toast.LENGTH_LONG
                            ).show()
                        }
                    }
                }
            }

            override fun onFailure(call: Call<ActivateResponse>, t: Throwable) {
                Toast.makeText(this@Eya, "Error", Toast.LENGTH_LONG).show()
            }
        })
    }
}
