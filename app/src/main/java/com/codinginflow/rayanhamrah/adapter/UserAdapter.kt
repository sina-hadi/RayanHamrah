package com.codinginflow.rayanhamrah.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.codinginflow.rayanhamrah.R
import com.codinginflow.rayanhamrah.model.data.User

class UserAdapter : RecyclerView.Adapter<UserAdapter.MyViewHolder>() {

    private var users : List<User> = listOf()

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){

        val userName : TextView = itemView.findViewById(R.id.userName)
        val companyName : TextView = itemView.findViewById(R.id.companyName)
        val companyImage : ImageView = itemView.findViewById(R.id.companyImage)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.log_in_item, parent,false)
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentUser = users[position]
        holder.userName.text = currentUser.user
        holder.companyName.text = currentUser.company
        holder.companyImage.setImageResource(currentUser.image)
    }

    override fun getItemCount(): Int {
        return users.size
    }

    fun setData(data: List<User>) {
        users = data
    }

}