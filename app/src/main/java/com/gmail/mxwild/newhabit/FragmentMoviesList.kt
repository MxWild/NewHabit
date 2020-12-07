package com.gmail.mxwild.newhabit

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.gmail.mxwild.newhabit.adapter.MoviesAdaptor
import com.gmail.mxwild.newhabit.adapter.OnItemClickListener
import com.gmail.mxwild.newhabit.domain.MockMoviesData

class FragmentMoviesList : Fragment() {

    private lateinit var adapter: MoviesAdaptor

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_movies_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val recycler: RecyclerView = view.findViewById(R.id.movie_list)
        adapter = MoviesAdaptor(clickListener)
        recycler.adapter = adapter
    }

    override fun onStart() {
        super.onStart()
        loadMovies()
    }

    private fun loadMovies() {
        adapter.bindMovies(MockMoviesData().getMovies())
    }

    private val clickListener = object : OnItemClickListener {
        override fun onClick() {
            doOnClick()
        }
    }

    private fun doOnClick() {
        view?.findViewById<ImageView>(R.id.back_img_movie_list)?.apply {
            parentFragmentManager.beginTransaction()
                .addToBackStack(null)
                .replace(R.id.fragment_container, FragmentMoviesDetails.newInstance())
                .commit()
        }
    }

    companion object {
        fun newInstance() = FragmentMoviesList()
    }
}