package com.example.vicemovies

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.vicemovies.Models.Movie
import com.squareup.picasso.Picasso

class MovieViewModel: ViewModel() {
    private val mutableTitleLiveData = MutableLiveData<String>()
    private val mutableOverviewLiveData = MutableLiveData<String>()
    private val mutableReleaseDateLiveData = MutableLiveData<String>()
    private val mutableGenre_idsLiveData = MutableLiveData<List<Int>>()
    private val mutableMovieImageUrlLiveData = MutableLiveData<String>()
    private val mutableMovieRatingLiveData = MutableLiveData<String>()
    private val mutableSelectedItemLiveData = MutableLiveData<Movie>()

    val selectedMovie: LiveData<Movie> get() = mutableSelectedItemLiveData
    val title: LiveData<String> get() = mutableTitleLiveData
    val url: LiveData<String> get() = mutableMovieImageUrlLiveData
    val overview: LiveData<String> get() = mutableOverviewLiveData
    val rating: LiveData<String> get() = mutableMovieRatingLiveData
    val releaseDate: LiveData<String> get() = mutableReleaseDateLiveData

    fun selectMovie(movie: Movie) {
        mutableSelectedItemLiveData.value = movie
    }

    fun setTitle(name:String) {
        mutableTitleLiveData.value = name
    }

    fun setOverview(overview:String) {
        mutableOverviewLiveData.value = overview
    }

    fun setReleaseDate(date:String) {
        mutableReleaseDateLiveData.value = date
    }

    fun setMovieImageUrl(url:String) {
        mutableMovieImageUrlLiveData.value = url
    }

    fun setRating(rating:Double) {
        mutableMovieRatingLiveData.value = "$rating/10"
    }
}

@BindingAdapter("android:src")
fun loadImage(iv: ImageView, url: String) {
    Picasso
        .with(iv.context).load(url).resize(750,900).into(iv)
}

