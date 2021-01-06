package com.gmail.mxwild.newhabit.utils

import com.gmail.mxwild.newhabit.model.data.Movie

sealed class State {
    class Success(val moviesList: List<Movie>) : State()
}