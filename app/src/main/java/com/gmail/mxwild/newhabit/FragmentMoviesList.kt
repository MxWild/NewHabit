package com.gmail.mxwild.newhabit

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment

class FragmentMoviesList : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_movies_list, container, false)


        view.findViewById<ImageView>(R.id.back_img_movie_list).apply {
            setOnClickListener {
                fragmentManager?.beginTransaction()
                    ?.addToBackStack(null)
                    ?.replace(R.id.fragment_container, FragmentMoviesDetails())
                    ?.commit()
            }
        }

        return view
    }
}