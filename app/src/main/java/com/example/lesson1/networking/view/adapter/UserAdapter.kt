package com.example.lesson1.networking.view.adapter

import com.example.lesson1.networking.model.UserNet

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.lesson1.R
import com.example.lesson1.networking.model.Address

class UserAdapter(private var userList: ArrayList<UserNet>?, val context: Context) :
    RecyclerView.Adapter<UserAdapter.ViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val view: View
        view = if (viewType == 0) {
            LayoutInflater.from(parent.context).inflate(R.layout.item_net, parent, false)
        } else {
            LayoutInflater.from(parent.context).inflate(R.layout.item_net_2, parent, false)
        }
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(
            userList!![position].name, userList!![position].username, userList!![position].email,
            userList!![position].address
        )
    }

    override fun getItemViewType(position: Int): Int {
        return position % 2
    }

    override fun getItemCount(): Int {
        return userList!!.size
    }

    class ViewHolder internal constructor(itemView: View) :
        RecyclerView.ViewHolder(itemView) {
        var firstLine = itemView.findViewById<TextView>(R.id.first_line)
        var secondLine = itemView.findViewById<TextView>(R.id.second_line)
        var thirdLine = itemView.findViewById<TextView>(R.id.third_line)

        @SuppressLint("SetTextI18n")
        fun bind(name: String, username: String, email: String, address: Address) {
            firstLine.text = "$name, $username"
            secondLine.text = email
            thirdLine.text = "${address.street}, ${address.suite}, ${address.city}"
        }
    }
}