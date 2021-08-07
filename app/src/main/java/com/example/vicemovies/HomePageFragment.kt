package com.example.vicemovies

import android.content.res.Resources
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.vicemovies.Models.Movie
import com.example.vicemovies.databinding.FragmentHomeBinding
import com.example.vicemovies.databinding.MovieViewHolderBinding

class HomePageFragment: Fragment() {

    private val imageUrlStem = "https://image.tmdb.org/t/p/w500/"
    lateinit var fragmentLayout: View
    private val movieViewModel:MovieViewModel by lazy {
        ViewModelProvider(requireActivity()).get(MovieViewModel::class.java)
    }
    private val homeViewModel:HomePageViewModel by lazy {
        ViewModelProvider(requireActivity()).get(HomePageViewModel::class.java)
    }

    private val favoriteMoviesViewModel: FavoriteMoviesViewModel by lazy {
        ViewModelProvider(requireActivity()).get(FavoriteMoviesViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding: FragmentHomeBinding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_home,
            container,
            false
        )
        fragmentLayout = binding.root
        homeViewModel.currentMovies?.observe(
            viewLifecycleOwner, { movies ->
                binding.nowPlayingMovieCollection.apply {
                    layoutManager = LinearLayoutManager(context,
                        RecyclerView.HORIZONTAL,
                        false)
                    adapter = MovieAdapter(movies)
                }
            }
        )
        homeViewModel.popularMovies?.observe(
            viewLifecycleOwner, { movies ->
                binding.popularMovieCollection.apply {
                    layoutManager = LinearLayoutManager(context, RecyclerView.HORIZONTAL, false)
                    adapter = MovieAdapter(movies)
                }
            }
        )

        binding.favoritesTab.setOnClickListener{
            homeViewModel.selectFavoritesTab()
        }
        return fragmentLayout
    }

    inner class MovieViewHolder(private val binding: MovieViewHolderBinding) : RecyclerView.ViewHolder(binding.root) {
      //  init { binding.movieViewModel = movieViewModel }
        fun bind(movie: Movie) {
            binding.apply {
            movieViewModel.setMovieImageUrl(imageUrlStem + movie.poster_path)
            loadImage(movieImage, movieViewModel.url.value?:"")
                movieImage.setOnClickListener{ movieViewModel.selectMovie(movie) }
                favoriteButton.setOnClickListener{
                    if(!favoriteMoviesViewModel.favoriteMovies.contains(movie)){
                        favoriteMoviesViewModel.addFavoriteMovie(movie)
                        favoriteButton.setBackgroundResource(com.example.vicemovies.R.drawable.ic_baseline_favorite_24)
                    } else {
                        favoriteMoviesViewModel.removeFavoriteMovie(movie)
                        favoriteButton.setBackgroundResource(R.drawable.ic_baseline_favorite_border_24)
                    }
                }
                executePendingBindings()
            }
        }
    }

    inner class MovieAdapter(private val movies: List<Movie>) : RecyclerView.Adapter<MovieViewHolder>() {

            override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
                val binding = DataBindingUtil.inflate<MovieViewHolderBinding>(
                    layoutInflater, R.layout.movie_view_holder, parent, false
                )
                return MovieViewHolder(binding)
            }

            override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
                holder.bind(movies[position])
            }

            override fun getItemCount(): Int {
                return movies.size
            }
        }
    }




