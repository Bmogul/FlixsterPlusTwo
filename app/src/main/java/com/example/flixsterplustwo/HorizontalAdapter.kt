package com.example.flixsterplustwo

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide



class HorizontalAdapter(private val movieList: MutableList<MovieModel>?, private val context: Context) : RecyclerView.Adapter<HorizontalAdapter.ViewHolder>() {

    interface OnItemClickListener {
        fun onItemClick(movie: MovieModel)
    }

    private var onItemClickListener: OnItemClickListener? = null

    fun setOnItemClickListener(listener: OnItemClickListener) {
        onItemClickListener = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HorizontalAdapter.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.movie_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val movie = movieList?.get(position)

        if (movie != null) {
            holder.title.text = movie.title
            Glide.with(context).load(movie.poster_path).into(holder.poster)

            holder.itemView.setOnClickListener {
                onItemClickListener?.onItemClick(movie)
            }
        }
    }

    override fun getItemCount(): Int {
        return movieList?.size ?: 0
    }

    inner class ViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {
        val poster : ImageView = itemView.findViewById(R.id.movie_poster)
        val title : TextView = itemView.findViewById(R.id.movieTitle)
    }

}
