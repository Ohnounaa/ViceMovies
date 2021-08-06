package com.example.vicemovies.Models


import androidx.room.Entity
import com.example.vicemovies.Models.Movie
@Entity
data class Movies(
    val page: Int,
    val results: List<Movie>,
    val total_pages: Int,
    val total_results: Int
)