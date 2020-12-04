package com.gmail.mxwild.newhabit.domain

import com.gmail.mxwild.newhabit.R
import com.gmail.mxwild.newhabit.model.Movie

class MoviesDataSource {
    fun getMovies(): List<Movie> {
        return listOf(
            Movie(
                "Avengers: End Game",
                13,
                "Action, Adventure, Fantasy",
                4F,
                125,
                137,
                R.drawable.img_avengers_end_game
            ),
            Movie(
                "Tenet",
                16,
                "Action, Sci-Fi, Thriller",
                5F,
                98,
                97,
                R.drawable.img_tenet
            ),
            Movie(
                "Black Widow",
                13,
                "Action, Adventure, Sci-Fi",
                4F,
                38,
                102,
                R.drawable.img_black_widow
            ),
            Movie(
                "Wonder Woman 1984",
                13,
                "Action, Adventure, Fantasy",
                5F,
                74,
                102,
                R.drawable.img_wonder_woman
            )
        )
    }
}