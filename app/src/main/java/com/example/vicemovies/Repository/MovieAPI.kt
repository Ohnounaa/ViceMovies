package com.example.vicemovies.Repository

import com.example.vicemovies.Models.Configuration
import com.example.vicemovies.Models.Genres
import com.example.vicemovies.Models.Movies
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieAPI {

    @GET("movie/now_playing?api_key=51c73e162cf5c4b1265e9d3e4b8f0128&language=en-US&page=1")
     fun retrieveNowPlayingMovies(): Call<Movies>

    @GET("movie/popular?api_key=51c73e162cf5c4b1265e9d3e4b8f0128&language=en-US&page=1")
     fun retrieveMostPopularMovies(@Query("page")page: String): Call<Movies>

    @GET("configuration?api_key=51c73e162cf5c4b1265e9d3e4b8f0128")
     fun retrieveConfiguration(): Call<Configuration>

     @GET("genre/movie/list?api_key=51c73e162cf5c4b1265e9d3e4b8f0128&language=en-US")
     fun getGenreIdsToNamesMap() : Call<Genres>

}