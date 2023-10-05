package com.example.flixsterplustwo

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class MovieListAdapter(private val movieMap: MutableMap<String, MutableList<MovieModel>>,private val context: Context)
    : RecyclerView.Adapter<MovieListAdapter.ViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.horizontal_recycler, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return movieMap.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        TODO("Not yet implemented")
    }

    inner class ViewHolder(itemView: View):RecyclerView.ViewHolder(itemView) {

    }

}
