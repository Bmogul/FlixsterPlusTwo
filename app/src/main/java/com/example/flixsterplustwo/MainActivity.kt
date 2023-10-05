package com.example.flixsterplustwo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

private const val TAG = "MainActivity/"
private val SEARCH_API_KEY = BuildConfig.API_KEY
private val ARTICLE_SEARCH_URL =
    "https://api.nytimes.com/svc/search/v2/articlesearch.json?api-key=${SEARCH_API_KEY}"

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
    }


}