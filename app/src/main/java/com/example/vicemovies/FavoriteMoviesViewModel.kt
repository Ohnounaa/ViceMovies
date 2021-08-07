package com.example.vicemovies

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.vicemovies.Models.Movie

class FavoriteMoviesViewModel: ViewModel() {
    var favoriteMovies : MutableLiveData<List<Movie>>? = null

    fun addFavoriteMovie(favoriteMovie: Movie) {
        val movieList: MutableList<Movie> = mutableListOf()
        movieList.add(favoriteMovie)
        favoriteMovies?.value = movieList
    }

}