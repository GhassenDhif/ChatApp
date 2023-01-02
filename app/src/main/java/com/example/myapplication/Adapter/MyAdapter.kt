package com.example.myapplication.Adapter

import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.AdminModifierUser
import com.example.myapplication.R
import com.example.myapplication.models.User

class MyAdapter(private val users: ArrayList<User>):
    RecyclerView.Adapter<MyAdapter.ViewHolder>() {



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_users,parent,false)
        return ViewHolder(view)

    }
    override fun getItemCount()=users.size


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(users[position])
            holder.itemView.setOnClickListener {
                val intent = Intent(holder.itemView.context, AdminModifierUser::class.java)
                holder.itemView.context.startActivity(intent)
                intent.putExtra("_id", users[position]._id)
                intent.putExtra("Username", users[position].Username)
                intent.putExtra("Email", users[position].Email)
                intent.putExtra("Image", users[position].Image)
                intent.putExtra("Genre", users[position].Genre)
                intent.putExtra("Date_Naissance", users[position].Date_Naissance)
                intent.putExtra("Role", users[position].Role)
                intent.putExtra("Classe", users[position].Classe)
                intent.putExtra("Filiere", users[position].Filiere)
                intent.putExtra("Banne", users[position].Banne)
        }
                Log.d("TAG", "intent: ${users[position].Username}")
                Log.d("intent", "intent: ${users[position].Email}")
    }
    class ViewHolder (itemView: View) : RecyclerView.ViewHolder(itemView){

        val Username: TextView = itemView.findViewById(R.id.textname)
        val Email:TextView=itemView.findViewById(R.id.textemail)
        val delete:ImageView=itemView.findViewById(R.id.delete)
        fun bind(users: User) {
            Username.text = users.Username
            Email.text = users.Email
        }
        //go to new activity
    }

    }








