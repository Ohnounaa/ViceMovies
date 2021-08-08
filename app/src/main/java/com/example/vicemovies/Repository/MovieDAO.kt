package com.example.vicemovies.Repository

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.vicemovies.Models.Movie

@Dao
interface MovieDAO {

@Insert(onConflict = OnConflictStrategy.REPLACE)
suspend fun addMovie(movie: Movie)

@Query("SELECT * FROM movie_table")
fun fetchMovieData() : LiveData<List<Movie>>

}

