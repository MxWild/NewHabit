package com.gmail.mxwild.newhabit.moviedetail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.aids61517.easyratingview.EasyRatingView
import com.gmail.mxwild.newhabit.R
import com.gmail.mxwild.newhabit.model.data.Movie

class FragmentMovieDetails : Fragment() {

    private val movieDetailsViewModel: MovieDetailsViewModel by viewModels { MovieDetailsViewModelFactory() }

    private var actorRecycler: RecyclerView? = null
    private var adapter: ActorAdapter = ActorAdapter()
    private var movie: Movie? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            movie = it.getParcelable(MOVIE_OBJECT)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_movies_details, container, false)

        view.findViewById<TextView>(R.id.back_narrow).setOnClickListener {
            parentFragmentManager.popBackStack()
        }

        val topBackground = view.findViewById<ImageView>(R.id.top_background)
        val minimumAge = view.findViewById<TextView>(R.id.minimum_age_list)
        val titleMovieList = view.findViewById<TextView>(R.id.title_movie_list)
        val movieCategory = view.findViewById<TextView>(R.id.movie_category)
        val moviesRatingBar = view.findViewById<EasyRatingView>(R.id.movies_rating_bar)
        val countReviewers = view.findViewById<TextView>(R.id.count_reviewers)
        val movieDescription = view.findViewById<TextView>(R.id.movie_description)
        val castText = view.findViewById<TextView>(R.id.cast_text)
        actorRecycler = view.findViewById(R.id.actor_list)

        movie?.let { movieDetailsViewModel.loadMovie(it) }

        movieDetailsViewModel.selectedMovie.observe(this.viewLifecycleOwner) { movie ->
            topBackground.load(movie.backdrop) {
                crossfade(true)
            }
            minimumAge.text = getString(R.string.minimum_age, movie.minimumAge)
            titleMovieList.text = movie.title
            movieCategory.text = movie.genres.joinToString(separator = ", ") { genre -> genre.name }
            moviesRatingBar.rating = movie.ratings * 5 / 10
            countReviewers.text = getString(R.string.count_reviews, movie.numberOfRatings)
            movieDescription.text = movie.overview
            castText.isVisible = movie.actors.isNotEmpty()
            loadActors()
        }

        return view
    }

    override fun onDetach() {
        super.onDetach()
        actorRecycler = null
    }

    private fun loadActors() {
        actorRecycler?.adapter = adapter
        actorRecycler?.hasFixedSize()
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