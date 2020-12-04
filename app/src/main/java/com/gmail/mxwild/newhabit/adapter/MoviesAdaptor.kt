package com.gmail.mxwild.newhabit.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.aids61517.easyratingview.EasyRatingView
import com.gmail.mxwild.newhabit.R
import com.gmail.mxwild.newhabit.model.Movie

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
    }

    override fun getItemCount(): Int = movies.size

    fun bindMovies(movies: List<Movie>) {
        this.movies = movies
        notifyDataSetChanged()
    }

    inner class ViewHolder(item: View) : RecyclerView.ViewHolder(item) {

        private val name = item.findViewById<TextView>(R.id.name_list)
        private val age = item.findViewById<TextView>(R.id.pg_list)
        private val category = item.findViewById<TextView>(R.id.movie_category_list)
        private val rating = item.findViewById<EasyRatingView>(R.id.movie_rating_bar)
        private val countOfReviews = item.findViewById<TextView>(R.id.count_reviewers)
        private val length = item.findViewById<TextView>(R.id.movie_length_list)
        private val imgCover = item.findViewById<ImageView>(R.id.back_img_movie_list)

        fun onBind(movie: Movie, listener: OnItemClickListener) {
            name.text = movie.name
            age.text = context.getString(R.string.pg, movie.age)
            category.text = movie.category
            rating.rating = movie.rating
            countOfReviews.text = context.getString(R.string.count_reviews, movie.countOfReviews)
            length.text = context.getString(R.string.movie_length, movie.length)
            imgCover.setImageResource(movie.imageCover)

            itemView.setOnClickListener {
                listener.onClick()
            }
        }
    }
}

private val RecyclerView.ViewHolder.context
    get() = this.itemView.context

interface OnItemClickListener {
    fun onClick()
}