package com.example.vicemovies

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.vicemovies.Models.Configuration
import com.example.vicemovies.Models.Movie
import com.example.vicemovies.Repository.MovieRepository
import kotlinx.coroutines.launch

class HomePageViewModel: ViewModel() {

    private val repository = MovieRepository.retrieve()
    var currentMovies : MutableLiveData<List<Movie>>? = null
    var popularMovies: MutableLiveData<List<Movie>>? = null
    var configurationData: MutableLiveData<Configuration>? = null

    init {
        getConfigurationData()
        getNowPlayingMovies("1")
    }
    private fun getConfigurationData() {
        viewModelScope.launch{
            val configDataFromAPI = repository.getConfigurationDataFromAPI()
            configurationData = configDataFromAPI
        }
    }

    private fun getNowPlayingMovies(pageNumber: String)  {
        viewModelScope.launch {
            val currentMoviesAPIData = repository.getNowPlayingMoviesFromAPI(pageNumber)
            val popularMoviesAPIData = repository.getPopularMoviesFromAPI(pageNumber)
            currentMovies = currentMoviesAPIData
            popularMovies = popularMoviesAPIData
        }
    }


}