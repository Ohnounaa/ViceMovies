package com.example.vicemovies

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.vicemovies.databinding.FragmentDetailBinding

class DetailFragment: Fragment() {

    private val imageUrlStem = "https://image.tmdb.org/t/p/w500/"
    private lateinit var homeViewModel: HomePageViewModel
    lateinit var fragmentLayout: View


    private val movieViewModel:MovieViewModel by lazy {
        ViewModelProvider(requireActivity()).get(MovieViewModel::class.java)
    }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding: FragmentDetailBinding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_detail,
            container,
            false
        )
        fragmentLayout = binding.root
        val mMovieTitle = arguments?.getString("Title") as String
        val mPosterPath = arguments?.getString("Poster_Path") as String
        val mOverview = arguments?.getString("Overview") as String
        val mReleaseDate = arguments?.getString("Release_Date") as String
        val voteAverage = arguments?.getDouble("Vote_Average") as Double

   //     binding.movieViewModel = movieViewModel
        binding.apply {
            movieTitle.text = mMovieTitle
            loadImage(movieImage, imageUrlStem + mPosterPath)
            movieOverview.text =  mOverview
            releaseDate.text = mReleaseDate
            rating.text = "$voteAverage/10"
        }

        return fragmentLayout
    }



    companion object {
        fun newInstance(
            movieTitle: String?,
            posterPath: String,
            overview: String,
            releaseDate: String,
            voteAverage: Double
        ) : DetailFragment{
            val args = Bundle().apply{
                putString("Title", movieTitle)
                putString("Poster_Path", posterPath )
                putString("Overview", overview)
                putString("Release_Date", releaseDate)
                putDouble("Vote_Average", voteAverage)
            }
            return DetailFragment().apply {  arguments = args }
        }
    }
}