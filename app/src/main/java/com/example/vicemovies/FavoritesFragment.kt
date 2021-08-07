package com.example.vicemovies

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.vicemovies.databinding.FragmentFavoritesBinding
import com.example.vicemovies.databinding.FragmentHomeBinding


class FavoritesFragment: Fragment() {

    lateinit var fragmentLayout: View
    private val movieViewModel:MovieViewModel by lazy {
        ViewModelProvider(requireActivity()).get(MovieViewModel::class.java)
    }

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
        return fragmentLayout
    }

    companion object {
        fun newInstance(
//            movieTitle: String?,
//            posterPath: String,
//            overview: String,
//            releaseDate: String,
//            voteAverage: Double
        ) : FavoritesFragment{
            val args = Bundle().apply{
//                putString("Title", movieTitle)
//                putString("Poster_Path", posterPath )
//                putString("Overview", overview)
//                putString("Release_Date", releaseDate)
//                putDouble("Vote_Average", voteAverage)
            }
            return FavoritesFragment().apply {  arguments = args }
        }
    }

}