package com.codinginflow.rayanhamrah.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.codinginflow.rayanhamrah.R
import com.codinginflow.rayanhamrah.model.data.User

class ShakhesAdapter : RecyclerView.Adapter<ShakhesAdapter.MyViewHolder>() {

    private var users : List<User> = listOf()

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){

        val namad : TextView = itemView.findViewById(R.id.shNamad)
        val price : TextView = itemView.findViewById(R.id.shNumber)
        val percent : TextView = itemView.findViewById(R.id.shPercent)
        val change : TextView = itemView.findViewById(R.id.shChange)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.shakhes_stocks, parent,false)
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentUser = users[position]
//        holder.namad.text = currentUser.user
//        holder.price.text = currentUser.company
//        holder.percent.text =
//        holder.change.text =
    }

    override fun getItemCount(): Int {
        return users.size
    }

    fun setData(data: List<User>) {
        users = data
    }

}