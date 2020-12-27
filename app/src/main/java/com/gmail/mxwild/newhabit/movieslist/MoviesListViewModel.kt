package com.gmail.mxwild.newhabit.movieslist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.gmail.mxwild.newhabit.model.data.Movie

class MoviesListViewModel : ViewModel() {

    private val _mutableMovieList = MutableLiveData<List<Movie>>(emptyList())
    val moviesList: LiveData<List<Movie>> get() = _mutableMovieList
}