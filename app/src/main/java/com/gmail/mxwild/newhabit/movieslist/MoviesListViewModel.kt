package com.gmail.mxwild.newhabit.movieslist

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gmail.mxwild.newhabit.model.data.Movie
import com.gmail.mxwild.newhabit.repository.MovieRepository
import kotlinx.coroutines.launch

class MoviesListViewModel : ViewModel() {

    private val movieRepository = MovieRepository()

    private val _mutableMovieList = MutableLiveData<List<Movie>>()
    val moviesList: LiveData<List<Movie>> get() = _mutableMovieList

    fun loadMoviesList() {
        viewModelScope.launch {
            try {
                _mutableMovieList.value = movieRepository.getMovies()
            } catch (e: Exception) {
                Log.e(
                    MoviesListViewModel::class.java.simpleName,
                    "Error load movies data ${e.message}"
                )
            }
        }
    }

}