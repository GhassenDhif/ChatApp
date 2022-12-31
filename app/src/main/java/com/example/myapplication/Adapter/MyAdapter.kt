package com.example.myapplication.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.example.myapplication.models.users

class MyAdapter(private val users: ArrayList<users>):
    RecyclerView.Adapter<MyAdapter.ViewHolder>() {



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_users,parent,false)
        return ViewHolder(view)

    }
    override fun getItemCount()=users.size


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(users[position])

    }
    class ViewHolder (itemView: View) : RecyclerView.ViewHolder(itemView){
        val Username: TextView = itemView.findViewById(R.id.textname)
        val Email:TextView=itemView.findViewById(R.id.textemail)
        fun bind(users: users) {
            Username.text = users.Username
            Email.text = users.Email
        }
    }
    }








