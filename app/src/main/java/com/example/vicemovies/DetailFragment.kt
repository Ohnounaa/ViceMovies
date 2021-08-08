package com.example.vicemovies

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.vicemovies.Models.Movie
import com.example.vicemovies.databinding.FragmentDetailBinding

class DetailFragment: Fragment() {

    private val imageUrlStem = "https://image.tmdb.org/t/p/w500/"
    lateinit var fragmentLayout: View
    private val movieViewModel:MovieViewModel by lazy {
        ViewModelProvider(requireActivity()).get(MovieViewModel::class.java)
    }
    private val favoriteMoviesViewModel: FavoriteMoviesViewModel by lazy {
        ViewModelProvider(requireActivity()).get(FavoriteMoviesViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding: FragmentDetailBinding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_detail,
            container,
            false
        )
        fragmentLayout = binding.root
        movieViewModel.selectedMovie.observe( viewLifecycleOwner, { movie -> createDetailView(movie, binding) } )
        return fragmentLayout
    }

    private fun createDetailView(movie: Movie, binding: FragmentDetailBinding) {
        binding.movieViewModel = movieViewModel
        binding.apply {
            movieViewModel?.setTitle(movie.title)
            movieViewModel?.setOverview(movie.overview)
            movieViewModel?.setReleaseDate(movie.release_date)
            movieViewModel?.setRating(movie.vote_average)
            loadImage(movieImage, imageUrlStem + movie.poster_path)
            if(favoriteMoviesViewModel.favoriteMovies.contains(movie)) {
                favoriteButton.setBackgroundResource(R.drawable.ic_baseline_favorite_24)
            }
            favoriteButton.setOnClickListener {
                if(!favoriteMoviesViewModel.favoriteMovies.contains(movie)) {
                    favoriteMoviesViewModel.addFavoriteMovie(movie)
                    favoriteButton.setBackgroundResource(R.drawable.ic_baseline_favorite_24)
                } else {
                    favoriteMoviesViewModel.removeFavoriteMovie(movie)
                    favoriteButton.setBackgroundResource(R.drawable.ic_baseline_favorite_border_24)
                }
            }
        }
    }

    companion object {
        fun newInstance(
        ) : DetailFragment{
            val args = Bundle().apply{}
            return DetailFragment().apply {  arguments = args }
        }
    }
}