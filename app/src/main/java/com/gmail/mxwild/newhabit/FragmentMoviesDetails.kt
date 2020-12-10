package com.gmail.mxwild.newhabit

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.aids61517.easyratingview.EasyRatingView
import com.gmail.mxwild.newhabit.adapter.ActorAdapter
import com.gmail.mxwild.newhabit.data.Movie

class FragmentMoviesDetails : Fragment() {

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

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        movie = arguments?.getParcelable(MOVIE_OBJECT)
        bindMovieDetail(movie)

        val recycler: RecyclerView = view.findViewById(R.id.actor_list)
        adapter = ActorAdapter()
        recycler.adapter = adapter
    }

    private fun bindMovieDetail(movie: Movie?) {
        Log.d("Load movie detail := ", "$movie")

        if (movie != null && view != null) {
            view?.findViewById<ImageView>(R.id.top_background)?.load(movie.backdrop)
            view?.findViewById<TextView>(R.id.minimum_age_list)?.text =
                getString(R.string.minimum_age, movie.minimumAge)
            view?.findViewById<TextView>(R.id.title_movie_list)?.text = movie.title
            view?.findViewById<TextView>(R.id.movie_category)?.text =
                movie.genres.joinToString(separator = ", ") { genre -> genre.name }
            view?.findViewById<EasyRatingView>(R.id.movies_rating_bar)?.rating =
                movie.ratings * 5 / 10
            view?.findViewById<TextView>(R.id.count_reviewers)?.text =
                getString(R.string.count_reviews, movie.numberOfRatings)
            view?.findViewById<TextView>(R.id.movie_description)?.text = movie.overview
        } else {
            Toast.makeText(context, "Data coming soon", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onStart() {
        super.onStart()
        loadActors()
    }

    private fun loadActors() {
        movie?.let { adapter.bindActors(it.actors) }
    }

    companion object {
        fun newInstance() = FragmentMoviesDetails()
        const val MOVIE_OBJECT = "movie"
    }
}