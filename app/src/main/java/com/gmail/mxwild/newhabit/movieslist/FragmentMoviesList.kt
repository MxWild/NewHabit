package com.gmail.mxwild.newhabit.movieslist

import android.content.res.Configuration
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.Toast
import androidx.core.view.doOnPreDraw
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.gmail.mxwild.newhabit.R
import com.gmail.mxwild.newhabit.model.data.Movie
import com.gmail.mxwild.newhabit.moviedetail.FragmentMovieDetails
import com.google.android.material.transition.MaterialElevationScale

class FragmentMoviesList : Fragment() {

    private val viewModel: MoviesListViewModel by viewModels { MoviesListViewModelFactory() }

    private lateinit var adapter: MoviesAdaptor
    private lateinit var swipeRefreshLayout: SwipeRefreshLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        exitTransition = MaterialElevationScale(false).apply {
            duration = resources.getInteger(R.integer.reply_motion_duration_large).toLong()
        }
        reenterTransition = MaterialElevationScale(true).apply {
            duration = resources.getInteger(R.integer.reply_motion_duration_large).toLong()
        }
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_movies_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        postponeEnterTransition()
        view.doOnPreDraw { startPostponedEnterTransition() }

        val recycler: RecyclerView = view.findViewById(R.id.movie_list)
        adapter = MoviesAdaptor(clickListener)
        recycler.layoutManager = GridLayoutManager(context, getSpanCount())
        recycler.adapter = adapter

        observeMovies()

        viewModel.loadMoviesList(false)

        swipeRefreshLayout = view.findViewById(R.id.swipe_layout)
        swipeRefreshLayout.setOnRefreshListener {
            Toast
                .makeText(context, getString(R.string.reload_data), Toast.LENGTH_SHORT)
                .show()
            viewModel.loadMoviesList(true)
        }

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
            swipeRefreshLayout.isRefreshing = false
        })
    }

    private val clickListener = object : OnItemClickListener {
        override fun onClick(movie: Movie, view: View) {
            doOnClick(movie, view)
        }
    }

    private fun doOnClick(movie: Movie, view: View?) {
        val movieDetails = FragmentMovieDetails.newInstance(movie)

/*        exitTransition = MaterialElevationScale(false).apply {
            duration = resources.getInteger(R.integer.reply_motion_duration_large).toLong()
        }

        reenterTransition = MaterialElevationScale(true).apply {
            duration = resources.getInteger(R.integer.reply_motion_duration_large).toLong()
        }*/

        view?.findViewById<ImageView>(R.id.poster_img_movie_list)?.apply {
            parentFragmentManager.beginTransaction()
                .addSharedElement(view, getString(R.string.movie_details_shared))
                .addToBackStack(null)
                .replace(R.id.fragment_container, movieDetails)
                .commit()
        }
    }

    companion object {
        fun newInstance() = FragmentMoviesList()
    }
}