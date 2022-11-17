package com.example.myapplication

import android.app.DatePickerDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.DatePicker
import android.widget.Spinner
import android.widget.TextView
import java.util.Calendar


class MainActivity : AppCompatActivity() {

private lateinit var btn:Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main);
        /* navigation Signup */
        btn = findViewById(R.id.btnsignup)
        btn.setOnClickListener {
        val intent = Intent(this, Profile::class.java)
        startActivity(intent)
    }

        //CAlender
        val tB :TextView = findViewById(R.id.tB)
        val c =Calendar.getInstance()
        val year=c.get(Calendar.YEAR)
        val month=c.get(Calendar.MONTH)
        val day = c.get(Calendar.DAY_OF_MONTH)
        // button show
        val dateBtn:Button=findViewById(R.id.dateBtn)
        dateBtn.setOnClickListener{
            val dpd = DatePickerDialog(this,DatePickerDialog.OnDateSetListener{view:DatePicker , mYear:Int , mMonth:Int , mDay:Int ->
            tB.setText(""+mDay+"/"+mMonth+"/"+mYear)
            },year,month,day)
            dpd.show()
        }


        val spinner:Spinner = findViewById(R.id.planets_spinner)
        val textView:TextView = findViewById(R.id.cl)
        val spinner1:Spinner = findViewById(R.id.planet_spinner)
        val textView1:TextView = findViewById(R.id.fl)

        val classe = arrayOf("1er","2eme","3eme","4eme","5eme")
        val Fliere = arrayOf("SIM","TWIN","NIDS","BI","DS","CLOUD","SAE","SE","GAMIX","AI","WIN")
        val adapter = ArrayAdapter(this,androidx.appcompat.R.layout.support_simple_spinner_dropdown_item,classe)
        val adapter1 = ArrayAdapter(this,androidx.appcompat.R.layout.support_simple_spinner_dropdown_item,Fliere)
        spinner.adapter=adapter
        spinner1.adapter=adapter1

        spinner1.onItemSelectedListener= object : AdapterView.OnItemSelectedListener {
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






