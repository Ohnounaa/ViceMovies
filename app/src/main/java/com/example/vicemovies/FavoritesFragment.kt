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
import com.example.vicemovies.databinding.FavoriteMovieViewHolderBinding
import com.example.vicemovies.databinding.FragmentFavoritesBinding
import com.example.vicemovies.databinding.MovieViewHolderBinding


class FavoritesFragment: Fragment() {

    val favoriteMoviesViewModel: FavoriteMoviesViewModel by lazy {
        ViewModelProvider(requireActivity()).get(FavoriteMoviesViewModel::class.java)
    }


    private val imageUrlStem = "https://image.tmdb.org/t/p/w500/"
    lateinit var fragmentLayout: View

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding: FragmentFavoritesBinding  = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_favorites,
            container,
            false
        )
        fragmentLayout = binding.root


        favoriteMoviesViewModel.favoriteMoviesLiveData?.observe(
            viewLifecycleOwner, {movies ->
                binding.favoriteMoviesCollection.apply{
                    layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
                    adapter = FavoriteMovieAdapter(movies)
                }

            }
        )
        return fragmentLayout
    }


    inner class FavoriteMovieAdapter(private val movies: List<Movie>) : RecyclerView.Adapter<FavoriteMovieViewHolder>() {

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteMovieViewHolder {
            val binding = DataBindingUtil.inflate<FavoriteMovieViewHolderBinding>(
                layoutInflater, R.layout.favorite_movie_view_holder, parent, false
            )
            return FavoriteMovieViewHolder(binding)
        }

        override fun onBindViewHolder(holder: FavoriteMovieViewHolder, position: Int) {
            holder.bind(movies[position])
        }

        override fun getItemCount(): Int {
            return movies.size
        }
    }

    inner class FavoriteMovieViewHolder(private val binding: FavoriteMovieViewHolderBinding) : RecyclerView.ViewHolder(binding.root) {
        //  init { binding.movieViewModel = movieViewModel }
        fun bind(movie: Movie) {
            binding.apply {
                binding.favoriteMovieTitle.text = movie.title
                loadImage(binding.movieImage, imageUrlStem + movie.poster_path)
                removeButton.setOnClickListener {
                    favoriteMoviesViewModel.removeFavoriteMovie(movie)
                }
                executePendingBindings()
            }
        }
    }

    companion object {
        fun newInstance(
        ) : FavoritesFragment{
            val args = Bundle().apply{}
            return FavoritesFragment().apply {  arguments = args }
        }
    }

}