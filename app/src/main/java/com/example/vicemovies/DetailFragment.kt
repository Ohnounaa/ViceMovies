package com.example.vicemovies

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment

class DetailFragment: Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return super.onCreateView(inflater, container, savedInstanceState)
    }







    companion object {
        fun newInstance(movie: String?) : DetailFragment{
            val args = Bundle().apply{ putString("Title", movie) }
            return DetailFragment().apply {  arguments = args }
        }
    }
}