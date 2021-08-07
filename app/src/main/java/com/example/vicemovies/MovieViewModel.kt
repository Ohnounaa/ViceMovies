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
    var overview: MutableLiveData<String>? = MutableLiveData()
    var release_date: MutableLiveData<String> = MutableLiveData()
    var genre_ids: MutableLiveData<List<Int>> = MutableLiveData()
    var movieImageUrl: MutableLiveData<String>? = MutableLiveData()

    private val mutableFavoritedItem = MutableLiveData<Movie>()
    private val mutableSelectedItem = MutableLiveData<Movie>()
    val selectedMovie: LiveData<Movie> get() = mutableSelectedItem


    fun selectMovie(movie: Movie) {
        mutableSelectedItem.value = movie
    }

    fun favorite(movie:Movie) {
        mutableFavoritedItem.value = movie
    }


}

@BindingAdapter("android:src")
fun loadImage(iv: ImageView, url: String) {
    Picasso
        .with(iv.context).load(url).resize(800,950).into(iv)
}

