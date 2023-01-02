package com.example.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.Adapter.MyAdapter
import com.example.myapplication.api.RetrofitBuilder
import com.example.myapplication.databinding.ActivityAdmin2Binding
import com.example.myapplication.models.User
import com.google.android.material.floatingactionbutton.FloatingActionButton
import retrofit2.Call
import retrofit2.Response
import kotlin.collections.ArrayList

class Admin2 : AppCompatActivity() {
    lateinit var binding: ActivityAdmin2Binding
    private val apiInterface = RetrofitBuilder.create()
    private lateinit var recycler: RecyclerView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_admin2)
        binding = ActivityAdmin2Binding.inflate(layoutInflater)
        setContentView(binding.root)

         recycler = findViewById(R.id.recyclerView)
        val users = ArrayList<users>()
       // recycler.layoutManager = LinearLayoutManager(this)
       // recycler.setHasFixedSize(true)
        recycler.layoutManager= LinearLayoutManager(this, RecyclerView.VERTICAL,false);
        apiInterface.getUsers().enqueue(object : retrofit2.Callback<ArrayList<User>> {
            override fun onResponse(call: Call<ArrayList<User>>, response: Response<ArrayList<User>>) {
                //users.addAll(response.body()!!)
                if (response.isSuccessful) {
                    recycler.adapter = MyAdapter(response.body()!!)
                    Log.d("get all user","onResponse: yjib")
                }else{
                    Log.d("get all user","onResponse: mehou yjib fi chey")
                }


            }
            override fun onFailure(call: Call<ArrayList<User>>, t: Throwable) {

                Log.d("get all user","onFa: mehou yjib fi chey")

            }
        })
        val fab = findViewById<FloatingActionButton>(R.id.floating_action_button)
        fab.setOnClickListener {
            val intent = Intent(this, AjoutUser::class.java)
            startActivity(intent)
        }
        binding.searchUser.setOnQueryTextListener(object : androidx.appcompat.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
               binding.searchUser.clearFocus()
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                if (newText!!.isNotEmpty()) {
                    val search = newText.toLowerCase()
                    val list = ArrayList<User>()
                    for (item in list) {
                        if (item.Username.toLowerCase().contains(search)) {
                            list.add(item)
                        }
                    }
                    recycler.adapter!!.notifyDataSetChanged()
                } else {
                    recycler.adapter!!.notifyDataSetChanged()
                }
                return true
            }
        })


    }
}





