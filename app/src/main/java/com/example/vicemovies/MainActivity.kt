package com.example.vicemovies

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportFragmentManager.beginTransaction()
            .add(R.id.fragment_container,
                HomePageFragment()).commit()

        val movieViewModel: MovieViewModel by lazy {
            ViewModelProvider(this).get(MovieViewModel::class.java)
        }


        movieViewModel.selectedMovie.observe(
            this, Observer { movie ->
                        val detailFragment =
            DetailFragment.newInstance(movie.title,
                movie.poster_path,
                movie.overview,
                movie.release_date,
            movie.vote_average)
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.fragment_container, detailFragment)
            .addToBackStack("Add")
            .commit()
            }
        )
    }
}