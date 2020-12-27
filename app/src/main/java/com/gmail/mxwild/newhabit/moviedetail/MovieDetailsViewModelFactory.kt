package com.gmail.mxwild.newhabit.moviedetail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.gmail.mxwild.newhabit.model.data.Movie

class MovieDetailsViewModelFactory(private val movie: Movie?) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T = when (modelClass) {
        MovieDetailsViewModel::class.java -> movie?.let { MovieDetailsViewModel(it) }
        else -> throw IllegalArgumentException("$modelClass is not registered ViewModel")
    } as T
}