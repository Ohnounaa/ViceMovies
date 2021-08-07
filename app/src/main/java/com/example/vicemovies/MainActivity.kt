package com.example.vicemovies

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportFragmentManager.beginTransaction()
            .add(R.id.fragment_container,
                HomePageFragment()).commit()

        val homePageViewModel: HomePageViewModel by lazy {
            ViewModelProvider(this).get(HomePageViewModel::class.java)
        }

        val movieViewModel: MovieViewModel by lazy {
            ViewModelProvider(this).get(MovieViewModel::class.java)
        }

        homePageViewModel.favoritesTab.observe(
            this, Observer { movie ->
                val favoritesFragment =
                    FavoritesFragment.newInstance()
                supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.fragment_container, favoritesFragment)
                    .addToBackStack("Add")
                    .commit()
            }
        )

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