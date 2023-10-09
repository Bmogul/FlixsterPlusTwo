package com.example.flixsterplustwo

import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView


const val MOVIE_DETAILS = "MOVIE_DETAILS"
class MovieListAdapter(private val movieMap: MutableMap<String, MutableList<MovieModel>>,
                       private val context: Context)
    : RecyclerView.Adapter<MovieListAdapter.ViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.horizontal_recycler, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return movieMap.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val keys = movieMap.keys.toList()
        val verticalItem = keys[position]

        holder.movieListTitle.text = verticalItem

        val horizontalAdapter = HorizontalAdapter(movieMap[verticalItem], context)
        horizontalAdapter.setOnItemClickListener(object : HorizontalAdapter.OnItemClickListener {
            override fun onItemClick(movie: MovieModel) {
                Toast.makeText(context, "${movie.toString()}", Toast.LENGTH_SHORT).show()
                Log.v("MovieToString", movie.toString())
                // Handle the item click here, e.g., show a detail screen for the selected movie
                val intent = Intent(context, MovieDetails::class.java)
                intent.putExtra(MOVIE_DETAILS, movie)
                context.startActivity(intent)
            }
        })
        holder.horizontalList.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        holder.horizontalList.adapter = horizontalAdapter


    }

    inner class ViewHolder(itemView: View):RecyclerView.ViewHolder(itemView) {
        val movieListTitle: TextView = itemView.findViewById(R.id.listTitle)
        val horizontalList: RecyclerView = itemView.findViewById(R.id.horizontalList)
    }

}
