package com.gmail.mxwild.newhabit.moviedetail

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.aids61517.easyratingview.EasyRatingView
import com.gmail.mxwild.newhabit.R
import com.gmail.mxwild.newhabit.model.data.Movie
import com.google.android.material.transition.MaterialContainerTransform

class FragmentMovieDetails : Fragment() {

    private val viewModel: ActorViewModel = ActorViewModel()
    private var actorRecycler: RecyclerView? = null
    private var adapter: ActorAdapter = ActorAdapter()
    private var movie: Movie? = null

    override fun onCreate(savedInstanceState: Bundle?) {

        sharedElementEnterTransition = MaterialContainerTransform().apply {
            drawingViewId = R.id.fragment_container
            duration = resources.getInteger(R.integer.reply_motion_duration_large).toLong()
            scrimColor = Color.TRANSPARENT
        }

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

        movie?.let { viewMovieDetail(it, view) }

        observeActors()

        return view
    }

    private fun observeActors() {
        viewModel.actorList.observe(viewLifecycleOwner, { actorsList ->
            actorRecycler?.adapter = adapter
            actorRecycler?.hasFixedSize()
            adapter.bindActors(actorsList)
        })
    }

    private fun viewMovieDetail(movie: Movie, view: View) {

        val topBackground = view.findViewById<ImageView>(R.id.top_background)
        val minimumAge = view.findViewById<TextView>(R.id.minimum_age_list)
        val titleMovieList = view.findViewById<TextView>(R.id.title_movie_list)
        val movieCategory = view.findViewById<TextView>(R.id.movie_category)
        val moviesRatingBar = view.findViewById<EasyRatingView>(R.id.movies_rating_bar)
        val countReviewers = view.findViewById<TextView>(R.id.count_reviewers)
        val movieDescription = view.findViewById<TextView>(R.id.movie_description)
        val castText = view.findViewById<TextView>(R.id.cast_text)
        actorRecycler = view.findViewById(R.id.actor_list)

        topBackground.load(movie.backdrop) {
            crossfade(true)
        }
        minimumAge.text = getString(R.string.minimum_age, movie.minimumAge)
        titleMovieList.text = movie.title
        movieCategory.text = movie.genres?.joinToString() { genre -> genre.name }
        moviesRatingBar.rating = movie.ratings * 5 / 10
        countReviewers.text = getString(R.string.count_reviews, movie.numberOfRatings)
        movieDescription.text = movie.overview
        castText.isVisible = movie.actors.isNotEmpty()
        viewModel.loadActorsByMovieId(movieId = movie.id)
    }

    override fun onDetach() {
        super.onDetach()
        actorRecycler = null
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