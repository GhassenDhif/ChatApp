package com.example.myapplication

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.*
import com.example.myapplication.api.RetrofitBuilder.create
import com.example.myapplication.models.User
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*
import kotlin.collections.HashMap

class AdminModifierUser: AppCompatActivity() {

    @SuppressLint("CutPasteId", "SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_admin_modifier_user)

        //afficher les données dans les champs
        val Username: EditText = findViewById(R.id.username)
        val Email: EditText = findViewById(R.id.email)
        val Image: ImageView = findViewById(R.id.shapeableImageView)
        val GenreH: RadioButton = findViewById(R.id.radHomme)
        val GenreF: RadioButton = findViewById(R.id.radFemme)
        val Date_Naissance: TextView = findViewById(R.id.tB)
        val Role: EditText = findViewById(R.id.Role)
        val Classe: EditText = findViewById(R.id.Classe)
        val Filiere: EditText = findViewById(R.id.Filiere)
        val Banne: EditText = findViewById(R.id.Banne)
        val name : TextView = findViewById(R.id.TX1)
        val btn1: Button = findViewById(R.id.b4)
        val email : TextView = findViewById(R.id.TX2)

        val intent = intent
        val _id = intent.getStringExtra("_id")
        val username = intent.getStringExtra("Username")
        val emaill = intent.getStringExtra("Email")
        val image = intent.getStringExtra("Image")
        val genre = intent.getStringExtra("Genre")
        val date_naissance = intent.getStringExtra("Date-Naissance")
        val role = intent.getStringExtra("Role")
        val classe = intent.getStringExtra("Classe")
        val filiere = intent.getStringExtra("Filiere")
        val banne = intent.getStringExtra("Banne")
        val tbb=findViewById<TextView>(R.id.tB)

        Username.setText(username)
        Log.d("TAG", "onCreate: $username")
        name.text = username
        email.text = emaill
        Username.setText(username)
        Email.setText(emaill)
        if(genre=="Homme") {
            GenreH.isChecked = true
        } else{
            GenreF.isChecked=true
        }
        Date_Naissance.text = date_naissance
        Role.setText(role)
        Classe.setText(classe)
        Filiere.setText(filiere)
        Banne.setText(banne)

        btn1.setOnClickListener(View.OnClickListener {
            val map = HashMap<String?, String?>()
            if (GenreH.isChecked) {
                map["Genre"] = "Homme"
            } else {
                map["Genre"] = "Femme"
            }
            map["_id"] = _id
            if (Username.text.toString().isEmpty()) {
                Username.error = "Please enter your Username"
                Username.requestFocus()
                return@OnClickListener
            } else {
                map["Username"] = Username.text.toString()
            }
            if (Email.text.toString().isEmpty()) {
                Email.error = "Please enter your Email"
                Email.requestFocus()
                return@OnClickListener
            } else {
                map["Email"] = Email.text.toString()
            }
            map["Image"] = ""
            map["Date_Naissance"] = tbb.text.toString()
            if (Role.text.toString().isEmpty()) {
                Role.error = "Please enter your Role"
                Role.requestFocus()
                return@OnClickListener
            } else {
                map["Role"] = Role.text.toString()
            }
            if (Classe.text.toString().isEmpty()) {
                Classe.error = "Please enter your Classe"
                Classe.requestFocus()
                return@OnClickListener
            } else {
                map["Classe"] = Classe.text.toString()
            }
            if (Filiere.text.toString().isEmpty()) {
                Filiere.error = "Please enter your Filiere"
                Filiere.requestFocus()
                return@OnClickListener
            } else {
                map["Filiere"] = Filiere.text.toString()
            }
            if (Banne.text.toString().isEmpty()) {
                Banne.error = "Please enter your Banne"
                Banne.requestFocus()
                return@OnClickListener
            } else {
                map["Banne"] = Banne.text.toString()
            }

            create().updateUser(map)?.enqueue(object : Callback<User?> {
                override fun onResponse(call: Call<User?>, response: Response<User?>) {
                    if (response.isSuccessful) {
                        val intent = Intent(this@AdminModifierUser, Admin2::class.java)
                        startActivity(intent)
                    } else {
                        Toast.makeText(this@AdminModifierUser, "Error", Toast.LENGTH_SHORT).show()
                    }
                }

                override fun onFailure(call: Call<User?>, t: Throwable) {
                    Toast.makeText(this@AdminModifierUser, "Error", Toast.LENGTH_SHORT).show()
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
        })

}



}
