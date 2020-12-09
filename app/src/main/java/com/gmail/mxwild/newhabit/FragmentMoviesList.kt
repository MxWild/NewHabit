package com.gmail.mxwild.newhabit

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.RecyclerView
import com.gmail.mxwild.newhabit.FragmentMoviesDetails.Companion.MOVIE_OBJECT
import com.gmail.mxwild.newhabit.adapter.MoviesAdaptor
import com.gmail.mxwild.newhabit.adapter.OnItemClickListener
import com.gmail.mxwild.newhabit.data.Movie
import com.gmail.mxwild.newhabit.data.loadMovies
import kotlinx.coroutines.launch

class FragmentMoviesList : Fragment() {

    private lateinit var adapter: MoviesAdaptor
    //TODO оставил временно, для выбора что лучше
//    private val scope = CoroutineScope(Dispatchers.Main)

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
            context?.let { loadMovies(it) }?.let {
                adapter.bindMovies(it)
            }
        }

        //TODO оставил временно, для выбора что лучше
        /*scope.launch {
            context?.let { loadMovies(it) }?.let {
                adapter.bindMovies(it)
            }
        }*/
    }

    private val clickListener = object : OnItemClickListener {
        override fun onClick(movie: Movie) {
            doOnClick(movie)
        }
    }

    private fun doOnClick(movie: Movie) {
        val bundle = Bundle()
        bundle.putParcelable(MOVIE_OBJECT, movie)

        val movieDetails = FragmentMoviesDetails.newInstance()
        movieDetails.arguments = bundle

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