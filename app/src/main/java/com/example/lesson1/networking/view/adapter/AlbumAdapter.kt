package com.example.lesson1.networking.view.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.lesson1.R
import com.example.lesson1.networking.model.Album

class AlbumAdapter(private var albumsList: ArrayList<Album>?, val context: Context) :
    RecyclerView.Adapter<AlbumAdapter.ViewHolder>() {

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

    override fun onBindViewHolder(
        holder: ViewHolder,
        position: Int
    ) {
        holder.bind(
            albumsList!![position].userId, albumsList!![position].id, albumsList!![position].title
        )
    }

    override fun getItemViewType(position: Int): Int {
        return position % 2
    }

    override fun getItemCount(): Int {
        return albumsList!!.size
    }

    class ViewHolder internal constructor(itemView: View) :
        RecyclerView.ViewHolder(itemView) {
        var firstLine = itemView.findViewById<TextView>(R.id.first_line)
        var secondLine = itemView.findViewById<TextView>(R.id.second_line)
        var thirdLine = itemView.findViewById<TextView>(R.id.third_line)

        @SuppressLint("SetTextI18n")
        fun bind(userId: Int, id: Int, title: String?) {
            firstLine.text = "UserID: $userId"
            secondLine.text = "ID: $id"
            thirdLine.text = "Title: $title"
        }
    }
}