package com.example.myapplication

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.*
import com.example.myapplication.api.RetrofitBuilder
import com.example.myapplication.models.User
import com.example.myapplication.models.modelResponse.UpdateResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*
import kotlin.collections.HashMap

class fragmentProfile : AppCompatActivity() {

    private val apiInterface = RetrofitBuilder.create()
    
    @SuppressLint("CutPasteId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fragment_profil)

        lateinit var btn:Button

        val txname = findViewById<TextView>(R.id.TX1)
        val txemail = findViewById<TextView>(R.id.TX2)
        val email=findViewById<EditText>(R.id.email)
        val name=findViewById<EditText>(R.id.username)
        val radFemme=findViewById<RadioButton>(R.id.radFemme)
        val radHomme=findViewById<RadioButton>(R.id.radHomme)
        val btn1=findViewById<Button>(R.id.b4)
        val tbb=findViewById<TextView>(R.id.tB)


        var sharedPreference =this.getSharedPreferences("userConnected", MODE_PRIVATE)
        val sharedPref= this.getSharedPreferences("userConnected", Context.MODE_PRIVATE)
        val currentUser_id = sharedPref?.getString("_id","default value").toString()
        val currentUser_username = sharedPref?.getString("username","default value").toString()
        val currentUser_image = sharedPref?.getString("image","default value").toString()
        val currentUser_email = sharedPref?.getString("email","default value").toString()
        val currentUser_date_naissance = sharedPref?.getString("date-naissance","default value").toString()
        val currentUser_genre= sharedPref?.getString("genre","default value").toString()
        txname.text = currentUser_username
        txemail.text = currentUser_email
        email.setText(currentUser_email)
        name.setText(currentUser_username)
        if(currentUser_genre=="Homme") {
            radHomme.isChecked = true
        } else{
            radFemme.isChecked=true
        }
        tbb.text = currentUser_date_naissance

        fun isValidEmail(email: String): Boolean {
            if (email.contains("@gmail.com")) return true
            return false
        }
        fun isValidName(name: String): Boolean {
            if (name.length < 3) return false
            return true
        }

        btn1.setOnClickListener(View.OnClickListener {
            val map = HashMap<String?, String?>()
            if(isValidName(name.text.toString())){
                map["userId"] = currentUser_id
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
            if (radHomme.isChecked) {
                map["Genre"] = "Homme"
            } else {
                map["Genre"] = "Femme"
            }
            map["Image"]=""
            map["Date_Naissance"] = tbb.text.toString()
            if (name.text.toString().isNotEmpty() || email.text.toString().isNotEmpty() || tbb.text.toString().isNotEmpty()) {
                val call = apiInterface.updateUser(map)
                call?.enqueue(object : Callback<User> {
                    override fun onResponse(call: Call<User>, response: Response<User>) {
                        Log.e("response", response.body().toString())
                        Log.e("map", map.toString())
                        if (response.isSuccessful) {
                            Toast.makeText(
                                this@fragmentProfile,
                                map["Username"],
                                Toast.LENGTH_LONG

                            ).show()
                            val editor = sharedPreference.edit()
                            editor.putString("username", map["Username"])
                            editor.putString("email", map["Email"])
                            editor.putString("genre", map["Genre"])
                            editor.putString("image", map["Image"])
                            editor.putString("date-naissance", map["Date_Naissance"])
                            editor.apply()
                            val intent = Intent(this@fragmentProfile, Profile::class.java)
                            startActivity(intent)


                        } else if (response.code() == 400) {
                            Toast.makeText(
                                this@fragmentProfile,
                                "Email already exists",
                                Toast.LENGTH_LONG
                            ).show()
                        }
                    }

                    override fun onFailure(call: Call<User>, t: Throwable) {
                        Toast.makeText(this@fragmentProfile, t.message, Toast.LENGTH_LONG).show()
                    }
                })
            }
        })

        //CAlender
        val tB: TextView = findViewById(R.id.tB)
        val c = Calendar.getInstance()
        val year = c.get(Calendar.YEAR)
        val month = c.get(Calendar.MONTH)
        val day = c.get(Calendar.DAY_OF_MONTH)
        // button show
        val dateBtn: Button = findViewById(R.id.dateBtn)
        dateBtn.setOnClickListener {
            val dpd = DatePickerDialog(
                this,
                DatePickerDialog.OnDateSetListener { view: DatePicker, mYear: Int, mMonth: Int, mDay: Int ->
                    tB.setText("" + mDay + "/" + mMonth + "/" + mYear)
                },
                year,
                month,
                day
            )
            dpd.show()
        }
        }

    }


