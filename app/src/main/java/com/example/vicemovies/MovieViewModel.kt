package com.example.vicemovies

import android.util.Log
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.vicemovies.Models.Movie
import com.squareup.picasso.Picasso

class MovieViewModel: ViewModel() {
    var title: MutableLiveData<String>? = MutableLiveData()
    var movieImageUrl: MutableLiveData<String>? = MutableLiveData()

    var film: Movie? = null
    set(film) {
        field = film
        title?.postValue(film?.title)
        movieImageUrl?.postValue(film?.poster_path)
    }


    private val mutableSelectedItem = MutableLiveData<Movie>()
    val selectedMovie: LiveData<Movie> get() = mutableSelectedItem

    fun selectMovie(movie: Movie) {
        mutableSelectedItem.value = movie
    }
}

@BindingAdapter("android:src")
fun loadImage(iv: ImageView, url: String) {
    Picasso
        .with(iv.context).load(url).resize(850,1000).into(iv)
}

