package com.example.vicemovies.Repository

import android.app.Application
import com.example.vicemovies.Repository.MovieRepository

class MovieRepositoryInitializer: Application() {
    override fun onCreate() {
        super.onCreate()
        MovieRepository.initialize(this)
    }
}