package com.gmail.mxwild.newhabit.movieslist

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.transform.RoundedCornersTransformation
import com.aids61517.easyratingview.EasyRatingView
import com.gmail.mxwild.newhabit.R
import com.gmail.mxwild.newhabit.model.data.Movie

class MoviesAdaptor(private val listener: OnItemClickListener) :
    RecyclerView.Adapter<MoviesAdaptor.ViewHolder>() {

    private var movies: List<Movie> = listOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view: View =
            LayoutInflater.from(parent.context)
                .inflate(R.layout.view_holder_movie, parent, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.onBind(movies[position], listener)

/*        holder.itemView.animation = AnimationUtils.loadAnimation(
            holder.itemView.context,
            R.anim.list_animation
        )*/
    }

    override fun getItemCount(): Int = movies.size

    fun bindMovies(movies: List<Movie>) {
        this.movies = movies
        notifyDataSetChanged()
    }

    inner class ViewHolder(item: View) : RecyclerView.ViewHolder(item) {

        private val title = item.findViewById<TextView>(R.id.title_movie_list)
        private val minimumAge = item.findViewById<TextView>(R.id.minimum_age_list)
        private val category = item.findViewById<TextView>(R.id.movie_category_list)
        private val rating = item.findViewById<EasyRatingView>(R.id.movie_rating_bar)
        private val countOfReviews = item.findViewById<TextView>(R.id.count_reviewers)
        private val length = item.findViewById<TextView>(R.id.movie_length_list)
        private val posterImage = item.findViewById<ImageView>(R.id.poster_img_movie_list)

        fun onBind(movie: Movie, listener: OnItemClickListener) {
            itemView.transitionName = context.getString(R.string.movie_details_shared_with_id, movie.id.toString())
            Log.d("Load Movie ", " :=  $movie")
            title.text = movie.title
            minimumAge.text = context.getString(R.string.minimum_age, movie.minimumAge)
            length.text = context.getString(R.string.movie_length, movie.runtime)
            category.text = movie.genres?.joinToString() { genre -> genre.name }
            countOfReviews.text = context.getString(R.string.count_reviews, movie.numberOfRatings)
            posterImage.load(movie.poster) {
                crossfade(true)
                transformations(RoundedCornersTransformation(10f, 10f, 8f, 8f))
            }
            rating.rating = movie.ratings * 5 / 10

            itemView.setOnClickListener {
                Log.d("Click on movie: ", "${movie.id}")
                listener.onClick(movie, view = itemView)
            }
        }
    }
}

private val RecyclerView.ViewHolder.context
    get() = this.itemView.context

interface OnItemClickListener {
    fun onClick(movie: Movie, view: View)
}