package com.example.myapplication

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.widget.SearchView
import com.example.myapplication.models.users
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.Adapter.MyAdapter
import com.example.myapplication.api.RetrofitBuilder
import com.google.android.material.floatingactionbutton.FloatingActionButton
import retrofit2.Call
import retrofit2.Response
import java.util.*

class Admin2 : AppCompatActivity() {
    private val apiInterface = RetrofitBuilder.create()
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: MyAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_admin2)
        val recycler = findViewById<RecyclerView>(R.id.recyclerView)
        val users = ArrayList<users>()
        recycler.layoutManager = LinearLayoutManager(this)
        recycler.setHasFixedSize(true)
        apiInterface.getUsers().enqueue(object : retrofit2.Callback<List<users>> {
            override fun onResponse(call: Call<List<users>>, response: Response<List<users>>) {
                users.addAll(response.body()!!)
                adapter = MyAdapter(users)

                recycler.adapter = adapter
            }
            override fun onFailure(call: Call<List<users>>, t: Throwable) {



            }
        })
        val fab = findViewById<FloatingActionButton>(R.id.floating_action_button)
        fab.setOnClickListener {
            val intent = Intent(this, AjoutUser::class.java)
            startActivity(intent)
        }
    }
}





