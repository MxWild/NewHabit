package com.gmail.mxwild.newhabit.moviedetail

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.aids61517.easyratingview.EasyRatingView
import com.gmail.mxwild.newhabit.R
import com.gmail.mxwild.newhabit.model.data.Movie

class FragmentMovieDetails : Fragment() {

    private lateinit var viewModel: MovieDetailsViewModel

    private lateinit var adapter: ActorAdapter
    private var movie: Movie? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_movies_details, container, false)

        view.findViewById<TextView>(R.id.back_narrow).setOnClickListener {
            parentFragmentManager.popBackStack()
        }

        movie = arguments?.getParcelable(MOVIE_OBJECT)
        val viewModelFactory = MovieDetailsViewModelFactory(movie)

        viewModel = ViewModelProvider(this, viewModelFactory)
            .get(MovieDetailsViewModel::class.java)

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        bindMovieDetail(movie)

        val recycler: RecyclerView = view.findViewById(R.id.actor_list)
        adapter = ActorAdapter()
        recycler.adapter = adapter
        recycler.hasFixedSize()

        viewModel.selectedMovie.observe(viewLifecycleOwner, {
            bindMovieDetail(movie)
        })

        viewModel.setMovie()

        loadActors()
    }

    private fun bindMovieDetail(movie: Movie?) {
        Log.d("Load movie detail := ", "$movie")

        val viewValue = view

        if (movie != null && viewValue != null) {

            viewValue.findViewById<ImageView>(R.id.top_background).load(movie.backdrop)
            viewValue.findViewById<TextView>(R.id.minimum_age_list).text =
                getString(R.string.minimum_age, movie.minimumAge)
            viewValue.findViewById<TextView>(R.id.title_movie_list).text = movie.title
            viewValue.findViewById<TextView>(R.id.movie_category).text =
                movie.genres.joinToString(separator = ", ") { genre -> genre.name }
            viewValue.findViewById<EasyRatingView>(R.id.movies_rating_bar).rating =
                movie.ratings * 5 / 10
            viewValue.findViewById<TextView>(R.id.count_reviewers).text =
                getString(R.string.count_reviews, movie.numberOfRatings)
            viewValue.findViewById<TextView>(R.id.movie_description).text = movie.overview
            viewValue.findViewById<TextView>(R.id.cast_text).isVisible = movie.actors.isNotEmpty()
        } else {
            Toast.makeText(context, "Data coming soon", Toast.LENGTH_SHORT).show()
        }
    }

    private fun loadActors() {
        movie?.let { adapter.bindActors(it.actors) }
    }

    companion object {
        const val MOVIE_OBJECT = "movie"

        fun newInstance(movie: Movie): FragmentMovieDetails {
            return FragmentMovieDetails().apply {
                arguments = Bundle().apply {
                    putParcelable(MOVIE_OBJECT, movie)
                }
            }
        }
    }
}