package com.gmail.mxwild.newhabit

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.RecyclerView
import com.gmail.mxwild.newhabit.adapter.MoviesAdaptor
import com.gmail.mxwild.newhabit.adapter.OnItemClickListener
import com.gmail.mxwild.newhabit.data.Movie
import com.gmail.mxwild.newhabit.data.loadMovies
import kotlinx.coroutines.launch

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

        viewLifecycleOwner.lifecycleScope.launch {
            val contextValue = context
            if (contextValue != null) {
                val movies = loadMovies(contextValue)
                adapter.bindMovies(movies)
            }
        }
    }

    private val clickListener = object : OnItemClickListener {
        override fun onClick(movie: Movie) {
            doOnClick(movie)
        }
    }

    private fun doOnClick(movie: Movie) {
        val movieDetails = FragmentMoviesDetails.newInstance(movie)

        view?.findViewById<ImageView>(R.id.poster_img_movie_list)?.apply {
            parentFragmentManager.beginTransaction()
                .addToBackStack(null)
                .replace(R.id.fragment_container, movieDetails)
                .commit()
        }
    }

    companion object {
        fun newInstance() = FragmentMoviesList()
    }
}