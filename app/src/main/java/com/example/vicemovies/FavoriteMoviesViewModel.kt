package com.example.vicemovies

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.vicemovies.Models.Movie

class FavoriteMoviesViewModel: ViewModel() {
    var favoriteMovies: ArrayList<Movie> = ArrayList()
    var favoriteMoviesLiveData : MutableLiveData<List<Movie>>? = MutableLiveData()

    fun addFavoriteMovie(favoriteMovie: Movie) {
        if(!favoriteMovies.contains(favoriteMovie)) {
            favoriteMovies.add(favoriteMovie)
            favoriteMoviesLiveData?.value = favoriteMovies
        }
    }

    fun removeFavoriteMovie(movieToRemove: Movie) {
        if(favoriteMovies.contains(movieToRemove)) {
            favoriteMovies.remove(movieToRemove)
            favoriteMoviesLiveData?.value = favoriteMovies
        }
    }
}