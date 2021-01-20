package com.gmail.mxwild.newhabit.movieslist

import android.content.res.Configuration
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ProgressBar
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.gmail.mxwild.newhabit.R
import com.gmail.mxwild.newhabit.model.data.Movie
import com.gmail.mxwild.newhabit.moviedetail.FragmentMovieDetails

class FragmentMoviesList : Fragment() {

    private val viewModel: MoviesListViewModel by viewModels { MoviesListViewModelFactory() }

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
        recycler.layoutManager = GridLayoutManager(context, getSpanCount())
        recycler.adapter = adapter

        observeMovies()

        viewModel.loadMoviesList()
    }

    private fun getSpanCount(): Int {
        return if (resources.configuration.orientation
            == Configuration.ORIENTATION_LANDSCAPE
        ) 3 else 2
    }

    private fun observeMovies() {

        val progressBar = view?.findViewById<ProgressBar>(R.id.progress_bar)

        if (progressBar != null) {
            progressBar.isVisible = true
        }

        viewModel.moviesList.observe(viewLifecycleOwner, { movieList ->
            adapter.bindMovies(movieList)
            progressBar?.isVisible = false
        })
    }

    private val clickListener = object : OnItemClickListener {
        override fun onClick(movie: Movie) {
            doOnClick(movie)
        }
    }

    private fun doOnClick(movie: Movie) {
        val movieDetails = FragmentMovieDetails.newInstance(movie)

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