package com.example.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.example.myapplication.api.RetrofitBuilder
import com.example.myapplication.models.modelResponse.UpdateResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class fragmentProfil : AppCompatActivity() {

    val apiInterface = RetrofitBuilder.create()
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fragment_profil)
        lateinit var btn:Button

        val email=findViewById<TextView>(R.id.email)
        val name=findViewById<EditText>(R.id.username)

        val genre=findViewById<EditText>(R.id.genre)
        val date=findViewById<EditText>(R.id.date)


        fun isValidEmail(etEmail: String): Boolean {
            if (etEmail.contains("@gmail.com")) return true
            return false
        }
        fun isValidName(etName: String): Boolean {
            if (etName.length < 3) return false
            return true
        }

        btn.setOnClickListener(View.OnClickListener {
            val map = HashMap<String?, String?>()
            if(isValidName(name.text.toString())){
                map["Username"] = name.text.toString()
            }else{
                name.error = "Username is not valid"
                name.requestFocus()
                return@OnClickListener           }
            if (isValidEmail(email.text.toString())) {
                map["Email"] = email.text.toString()
            } else {
                email.error = "Email is not valid"
                email.requestFocus()
                return@OnClickListener
            }
            map["Image"]=""
            map["Date_Naissance"] = date.text.toString()

            if (name.text.toString().isNotEmpty() && email.text.toString().isNotEmpty() && date.text.toString().isNotEmpty() &&  date.text.toString().isNotEmpty() && genre.text.toString().isNotEmpty()) {
                val call = apiInterface.updateUser(map)
                call?.enqueue(object : Callback<UpdateResponse> {
                    override fun onResponse(
                        call: Call<UpdateResponse>,
                        response: Response<UpdateResponse>
                    ) {
                        if (response.code() == 200) {
                            Toast.makeText(
                                this@fragmentProfil,
                                map["Username"],
                                Toast.LENGTH_LONG
                            ).show()
                            val intent = Intent(this@fragmentProfil, fragmentProfil::class.java)
                            startActivity(intent)
                        } else if (response.code() == 400) {
                            Toast.makeText(
                                this@fragmentProfil,
                                "Email already exists",
                                Toast.LENGTH_LONG
                            ).show()
                        }
                    }

                    override fun onFailure(call: Call<UpdateResponse?>?, t: Throwable) {
                        Toast.makeText(this@fragmentProfil, t.message, Toast.LENGTH_LONG).show()
                    }
                })
            }
        })

    }
}