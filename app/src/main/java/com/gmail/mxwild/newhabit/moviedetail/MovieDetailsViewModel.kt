package com.gmail.mxwild.newhabit.moviedetail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.gmail.mxwild.newhabit.model.data.Movie

class MovieDetailsViewModel(private val movie: Movie) : ViewModel() {

    private val _selectedMovie = MutableLiveData<Movie>()
    val selectedMovie: LiveData<Movie> get() = _selectedMovie

    fun setMovie() {
        _selectedMovie.value = movie
    }
}