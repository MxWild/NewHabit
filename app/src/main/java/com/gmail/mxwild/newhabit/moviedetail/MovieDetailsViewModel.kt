package com.gmail.mxwild.newhabit.moviedetail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.gmail.mxwild.newhabit.model.data.Movie

class MovieDetailsViewModel : ViewModel() {

    private val _selectedMovie = MutableLiveData<Movie>()
    val selectedMovie: LiveData<Movie> get() = _selectedMovie

    fun loadMovie(movieSelect: Movie) {
        _selectedMovie.value = movieSelect
    }
}