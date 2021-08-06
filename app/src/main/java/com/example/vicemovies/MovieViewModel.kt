package com.example.vicemovies

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.vicemovies.Models.Movie
import com.squareup.picasso.Picasso

class MovieViewModel: ViewModel() {
    var movie: MutableLiveData<Movie>? = MutableLiveData()
    var title: MutableLiveData<String>? = MutableLiveData()
    var movieImageUrl: MutableLiveData<String>? = MutableLiveData()
}
@BindingAdapter("android:src")
fun loadImage(iv: ImageView, url: String) {
    Picasso
        .with(iv.context)
        .load(url).centerCrop().resize(500, 500).into(iv)
}

