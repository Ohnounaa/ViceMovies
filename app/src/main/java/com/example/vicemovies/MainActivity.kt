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

        val homePageViewModel: HomePageViewModel by lazy {
            ViewModelProvider(this).get(HomePageViewModel::class.java)
        }

        val detailViewModel: DetailViewModel by lazy {
            ViewModelProvider(this).get(DetailViewModel::class.java)
        }

        homePageViewModel.favoritesTab.observe(
            this, Observer {
                val favoritesFragment =
                    FavoritesFragment.newInstance()
                supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.fragment_container, favoritesFragment)
                    .addToBackStack("Add")
                    .commit()
            }
        )

        detailViewModel.selectedMovie.observe(
            this, Observer {
                val detailFragment =
            DetailFragment.newInstance()
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.fragment_container, detailFragment)
            .addToBackStack("Add")
            .commit()
            }
        )
    }
}