package com.example.myapplication

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

import android.util.Log.d
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.Adapter.MyAdapter
import com.example.myapplication.api.RetrofitBuilder
import retrofit2.Call
import retrofit2.Callback

import retrofit2.Response

class Admin : AppCompatActivity() {


    private val apiInterface = RetrofitBuilder.create()

    val recycler= findViewById<RecyclerView>(R.id.recyclerView)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_admin)
            
        val users = ArrayList<users>()
        recycler.layoutManager = LinearLayoutManager(this)
        recycler.setHasFixedSize(true)
        apiInterface.getUsers().enqueue(object : retrofit2.Callback<List<users>> {
            override fun onResponse(call: Call<List<users>>, response: Response<List<users>>) {
                d("daniel","onResponse: response.body()!![1].Username ")
                users.addAll(response.body()!!)
            }

            override fun onFailure(call: Call<List<users>>, t: Throwable) {
                d("daniel","onFailure: ${t.message}")
            }
        })
        

    }
}

fun <T> Call<T>.enqueue(callback: Callback<List<users>>) {

}











