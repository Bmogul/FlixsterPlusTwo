package com.example.flixsterplustwo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.google.gson.Gson
import org.json.JSONObject

private const val TAG = "MainActivity/"
private val API_KEY = BuildConfig.API_KEY

class MainActivity : AppCompatActivity() {
    private val movieMap = mutableMapOf<String, MutableList<MovieModel>>()
    private lateinit var mainAdapter: MovieListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recyclerViewMain = findViewById<RecyclerView>(R.id.mainAdapter)
        val layoutManagerMain = LinearLayoutManager(this)
        recyclerViewMain.layoutManager = layoutManagerMain

        mainAdapter = MovieListAdapter(movieMap, this)
        recyclerViewMain.adapter = mainAdapter

        val apiEndPoints = mutableMapOf<String,String>()
        apiEndPoints["Popular"] = "https://api.themoviedb.org/3/movie/popular?language=en-US&page=1"
//        apiEndPoints[]

        fetchMovieData(apiEndPoints)
    }

    private fun fetchMovieData(urls : MutableMap<String, String>){
        Log.v("MainActivityF", "Fetching")

//        val url = "https://api.themoviedb.org/3/movie/now_playing?language=en-US&page=1"
//        val apiKey = "Bearer eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiIyMzdmNGU3NzExYjEwNTVlMjgxNDk4ZGIwYWY0OWI5ZCIsInN1YiI6IjY1MTVlOGYwOTNiZDY5MDExYjhjOWY4ZSIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.Jd9ufjTgo4HQzieveddP82KcC88NkFsvO_N3OCPm8bA"
//        val apiKey = "237f4e7711b1055e281498db0af49b9d"
        val requestQueue: RequestQueue = Volley.newRequestQueue(this)
        for((key, url) in urls){
            val jsonObjectRequest = JsonObjectRequest(
                Request.Method.GET, "$url&api_key=$API_KEY", null,
                { response ->
                    // Parse the JSON response and populate the movieList
                    Log.v("MainActivityFL", "Rsponse Good")
                    parseApiResponse(response, key)
                    // Notify the adapter that the data has changed
                    mainAdapter.notifyDataSetChanged()
                },
                { error ->
                    // Handle API request error
                    // You can add error handling logic here
                    Log.v("MainActivityFL", error.toString())
                })

            requestQueue.add(jsonObjectRequest)
        }

    }
    private fun parseApiResponse(response: JSONObject?, key: String) {
        Log.v("MainActivityFL", key+": "+response.toString())
        val gson = Gson()
        val jsonArray = response?.getJSONArray("results")

        if (jsonArray != null) {
            movieMap[key] = movieMap[key] ?: mutableListOf()
            for (i in 0 until jsonArray.length()){
                val jsonObject = jsonArray.getJSONObject(i)
                val movieData = gson.fromJson(jsonObject.toString(), MovieModel::class.java)

                movieData.poster_path = "https://image.tmdb.org/t/p/w500/${movieData.poster_path}"
                movieMap[key]?.add(movieData)
            }
        }
    }
}