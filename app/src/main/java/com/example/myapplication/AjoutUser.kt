package com.example.myapplication

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.DatePicker
import android.widget.EditText
import android.widget.RadioButton
import android.widget.Spinner
import android.widget.TextView
import android.widget.Toast
import com.example.myapplication.api.ApiInterface
import com.example.myapplication.models.modelResponse.SignupResponse
import com.google.android.material.textfield.TextInputEditText
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.Calendar


class AjoutUser : AppCompatActivity() {

    private var retrofit: Retrofit? = null
    private var retrofitInterface: ApiInterface? = null
    private val BASE_URL = "http://192.168.1.28:5000/"






    @SuppressLint("MissingInflatedId", "WrongViewCast")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ajout_user);

        lateinit var btn:Button

        val etName=findViewById<TextInputEditText>(R.id.etName)
        val etEmail=findViewById<TextInputEditText>(R.id.etEmail)
        val etPassword=findViewById<TextInputEditText>(R.id.etPass)
        val etvPassword=findViewById<TextInputEditText>(R.id.etvPass)
        val radFemme=findViewById<RadioButton>(R.id.radFemme)
        val radHomme=findViewById<RadioButton>(R.id.radHomme)
        val role="User"
        val ccl=findViewById<TextView>(R.id.cl)
        val ffl=findViewById<TextView>(R.id.fl)
        val tbb=findViewById<TextView>(R.id.tB)


        fun isValidPassword(etPassword: String,etvPassword: String): Boolean {
            if (etPassword.length < 8) return false
            if (etPassword != etvPassword) return false
            return true
        }
        fun isValidEmail(etEmail: String): Boolean {
            if (etEmail.contains("@gmail.com")) return true
            return false
        }
        fun isValidName(etName: String): Boolean {
            if (etName.length < 3) return false
            return true
        }


        retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        retrofitInterface = retrofit!!.create(ApiInterface::class.java)


        /* navigation Signup */
        btn = findViewById(R.id.btnsignup)




        btn.setOnClickListener(View.OnClickListener {
            val map = HashMap<String?, String?>()
            if(isValidName(etName.text.toString())){
                map["Username"] = etName.text.toString()
            }else{
                etName.setError("Username is not valid")
                etName.requestFocus()
                return@OnClickListener           }
            if (isValidEmail(etEmail.text.toString())) {
                map["Email"] = etEmail.text.toString()
            } else {
                etEmail.setError("Email is not valid")
                etEmail.requestFocus()
                return@OnClickListener
            }
            if (isValidPassword(etPassword.text.toString(),etvPassword.text.toString())) {
                map["Password"] = etPassword.text.toString()
            } else {
                etPassword.setError("Password must be at least 8 characters")
                etPassword.requestFocus()
                return@OnClickListener
            }
            map["Image"]=""
            if ((radHomme.isChecked || radFemme.isChecked)) {
                if (radHomme.isChecked) {
                    map["Genre"] = radHomme.text.toString()
                } else {
                    map["Genre"] = radFemme.text.toString()
                }
            }
            map["Date_Naissance"] = tbb.text.toString()
            map["Role"] = role
            map["Classe"] = ccl.text.toString()
            map["Filiere"] = ffl.text.toString()
            if (etName.text.toString().isNotEmpty() && etEmail.text.toString().isNotEmpty() && etPassword.text.toString().isNotEmpty() && etvPassword.text.toString().isNotEmpty()) {
                val call: Call<SignupResponse>? = retrofitInterface!!.executeSignup(map)
                call!!.enqueue(object : Callback<SignupResponse?> {
                    override fun onResponse(
                        call: Call<SignupResponse?>?,
                        response: Response<SignupResponse?>
                    ) {
                        if (response.code() == 200) {
                            Toast.makeText(
                                this@AjoutUser,
                                map["Username"],
                                Toast.LENGTH_LONG
                            ).show()
                            val intent = Intent(this@AjoutUser, Admin2::class.java)
                            startActivity(intent)
                        } else if (response.code() == 400) {
                            Toast.makeText(
                                this@AjoutUser,
                                "Email already exists",
                                Toast.LENGTH_LONG
                            ).show()
                        }
                    }

                    override fun onFailure(call: Call<SignupResponse?>?, t: Throwable) {
                        Toast.makeText(this@AjoutUser, t.message, Toast.LENGTH_LONG).show()
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


        val spinner: Spinner = findViewById(R.id.planets_spinner)
        val textView: TextView = findViewById(R.id.cl)
        val spinner1: Spinner = findViewById(R.id.planet_spinner)
        val textView1: TextView = findViewById(R.id.fl)

        val classe = arrayOf("1er", "2eme", "3eme", "4eme", "5eme")
        val Fliere =
            arrayOf("SIM", "TWIN", "NIDS", "BI", "DS", "CLOUD", "SAE", "SE", "GAMIX", "AI", "WIN")
        val adapter = ArrayAdapter(
            this,
            androidx.appcompat.R.layout.support_simple_spinner_dropdown_item,
            classe
        )
        val adapter1 = ArrayAdapter(
            this,
            androidx.appcompat.R.layout.support_simple_spinner_dropdown_item,
            Fliere
        )
        spinner.adapter = adapter
        spinner1.adapter = adapter1
        spinner1.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                textView1.text = Fliere[position]
            }
            override fun onNothingSelected(parent: AdapterView<*>?) {
            }
        }
        spinner.onItemSelectedListener = object:AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                textView.text = classe[position]
            }
            override fun onNothingSelected(parent: AdapterView<*>?) {
            }

        }




    }
}







