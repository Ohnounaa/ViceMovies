package com.example.vicemovies

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
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


        movieViewModel.selectedMovie.observe(
            this, {
                movie ->
                val detailFragment =
                    DetailFragment.newInstance(movie.title)
                supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.fragment_container, detailFragment)
                    .addToBackStack("Add")
                    .commit()
            }
        )
    }
}