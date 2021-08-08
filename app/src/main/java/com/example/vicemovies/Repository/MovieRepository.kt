package com.example.vicemovies.Repository

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.vicemovies.Models.Configuration
import com.example.vicemovies.Models.Genres
import com.example.vicemovies.Models.Movie

class MovieRepository private constructor(context: Context){

   fun getNowPlayingMoviesFromAPI(pageNumber:String): MutableLiveData<List<Movie>> {
        return MovieDataRetriever().retrieveNowPlayingMoviesData(pageNumber);
    }

    fun getPopularMoviesFromAPI(pageNumber: String) : MutableLiveData<List<Movie>> {
        return MovieDataRetriever().retrieveMostPopularMoviesData(pageNumber)
    }

   fun getConfigurationDataFromAPI() : MutableLiveData<Configuration> {
       return MovieDataRetriever().retrieveConfigurationData()
   }
    fun getGenreMap() : MutableLiveData<Genres> {
        return MovieDataRetriever().retrieveGenreIdsAndNames()

    }

  private val database: MovieDatabase? = MovieDatabase.getInstance(context.applicationContext)

  private val movieDao = database?.movieDao()

  suspend fun addFavoriteMoviesToDB(movie:Movie) = movieDao?.addMovie(movie)

  suspend fun getFavoriteMovies(): LiveData<List<Movie>>? = movieDao?.fetchMovieData()


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