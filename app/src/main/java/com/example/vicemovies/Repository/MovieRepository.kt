package com.example.vicemovies.Repository

import Genre
import Genres
import android.content.Context
import androidx.lifecycle.MutableLiveData
import com.example.vicemovies.Models.Configuration
import com.example.vicemovies.Models.Movie

class MovieRepository private constructor(context: Context){

   suspend fun getNowPlayingMoviesFromAPI(pageNumber:String): MutableLiveData<List<Movie>> {
        return MovieDataRetriever().retrieveNowPlayingMoviesData(pageNumber);
    }

    suspend fun getPopularMoviesFromAPI(pageNumber: String) : MutableLiveData<List<Movie>> {
        return MovieDataRetriever().retrieveMostPopularMoviesData(pageNumber)
    }

   suspend fun getConfigurationDataFromAPI() : MutableLiveData<Configuration> {
       return MovieDataRetriever().retrieveConfigurationData()
   }
    suspend fun getGenreMap() : MutableLiveData<Genres> {
        return MovieDataRetriever().retrieveGenreIdsAndNames()

    }

   // private val database: MovieDatabase? = MovieDatabase.getInstance(context.applicationContext)

 //   private val movieDao = database?.movieDAO

//TODO REMOVE DOUBLE BANG
  //  fun getNowPlayingMovies(): MutableState<List<Movie>> = movieDao!!.getMovies()

  //  fun insertWeatherInfo(dwi:DailyWeatherInfo) = weatherDao.insertWeatherDay(dwi)


    companion object{
        private var INSTANCE: MovieRepository? = null

        fun initialize(context: Context) {
            if(INSTANCE == null) {
                INSTANCE = MovieRepository(context)
            }
        }

        fun retrieve(): MovieRepository {
            return INSTANCE ?: throw IllegalStateException("Repository has not been initialized.")
        }

    }
}