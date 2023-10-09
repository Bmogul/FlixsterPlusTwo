package com.example.flixsterplustwo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide

class MovieDetails : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_details)

        val movie = intent.getSerializableExtra("MOVIE_DETAILS") as? MovieModel


        if (movie != null) {
            // Now you have the movie data, and you can display it in your MovieDetailsActivity
            // Example: Update UI elements with movie details
            val movieName = findViewById<TextView>(R.id.movieName)
            val overview = findViewById<TextView>(R.id.overview)
            val poster = findViewById<ImageView>(R.id.moviePoster)
            val rating = findViewById<TextView>(R.id.rating)
//            val tagline = findViewById<TextView>(R.id.tagline)


            movieName.text = movie.title
            overview.text = movie.overview
            rating.text = movie.vote_average.toString()

            movie.poster_path?.let { Log.v("Details", it) }

            if (!movie.poster_path.isNullOrEmpty()) {
                Glide.with(this)
                    .load(movie.poster_path)
                    .placeholder(R.drawable.ic_launcher_background) // Replace with your placeholder image resource
                    .error(R.drawable.ic_launcher_foreground) // Replace with your error image resource
                    .into(poster)
            } else {
                // Handle the case when poster_path is null or empty
                // You can set a default image or show an error message here
            }
        }
    }
}