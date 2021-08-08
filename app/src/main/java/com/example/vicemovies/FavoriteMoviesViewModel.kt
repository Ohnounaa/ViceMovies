package com.example.vicemovies

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.vicemovies.Models.Movie
import com.example.vicemovies.Repository.MovieRepository
import kotlinx.coroutines.launch

class FavoriteMoviesViewModel: ViewModel() {
    private val repository = MovieRepository.retrieve()
    var favoriteMovies: ArrayList<Movie> = ArrayList()
    var favoriteMoviesLiveData : MutableLiveData<List<Movie>>? = MutableLiveData()

    fun addFavoriteMovie(favoriteMovie: Movie) {
            favoriteMovies.add(favoriteMovie)
            favoriteMoviesLiveData?.value = favoriteMovies
            updateFavoriteMoviesInDB(favoriteMovie)
    }

    fun removeFavoriteMovie(movieToRemove: Movie) {
        if(favoriteMovies.contains(movieToRemove)) {
            favoriteMovies.remove(movieToRemove)
            favoriteMoviesLiveData?.value = favoriteMovies
        }
    }

    private fun updateFavoriteMoviesInDB(movie: Movie) {
        viewModelScope.launch {
            repository.addFavoriteMoviesToDB(movie)
        }
    }
}