package com.example.vicemovies

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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
    private lateinit var homeViewModel: HomePageViewModel
    lateinit var fragmentLayout: View

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding: FragmentHomeBinding = DataBindingUtil.inflate(inflater,
            R.layout.fragment_home,
            container,
            false)
        fragmentLayout = binding.root

        homeViewModel = ViewModelProvider(this).get(HomePageViewModel::class.java)

        homeViewModel.currentMovies?.observe(
            viewLifecycleOwner, {
                    movies -> binding.nowPlayingMovieCollection.apply {
                layoutManager = LinearLayoutManager(context, RecyclerView.HORIZONTAL, false)
                adapter = MovieAdapter(movies)
                }
            }
        )

        homeViewModel.popularMovies?.observe(
            viewLifecycleOwner, {
                movies -> binding.popularMovieCollection.apply{
                layoutManager = LinearLayoutManager(context, RecyclerView.HORIZONTAL, false)
                adapter = MovieAdapter(movies)
            }
            }
        )

        return fragmentLayout
    }

    inner class MovieViewHolder(private val binding: MovieViewHolderBinding) :RecyclerView.ViewHolder(binding.root) {
        init {
            binding.movieViewModel = MovieViewModel()
        }
        fun bind(movie: Movie) {
            binding.apply {
                movieViewModel?.movie?.value = movie
                movieViewModel?.title?.value = movie.title
                movieViewModel?.movieImageUrl?.value = imageUrlStem + movie.poster_path
                executePendingBindings()
            }
        }
    }

    inner class MovieAdapter(private val movies: List<Movie>?)
        : RecyclerView.Adapter<MovieViewHolder>() {
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
            val binding = DataBindingUtil.inflate<MovieViewHolderBinding> (
                layoutInflater, R.layout.movie_view_holder, parent, false)
         //   binding.lifecycleOwner = this@HomePageFragment
            return MovieViewHolder(binding)
        }

        override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
            movies?.get(position)?.let { holder.bind(it) }
        }

        override fun getItemCount(): Int {
          return movies?.size?:0
        }
    }
}


