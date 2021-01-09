package com.gmail.mxwild.newhabit.movieslist

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gmail.mxwild.newhabit.model.data.Movie
import com.gmail.mxwild.newhabit.model.data.loadMovies
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MoviesListViewModel : ViewModel() {

    private val _mutableMovieList = MutableLiveData<List<Movie>>()
    val moviesList: LiveData<List<Movie>> get() = _mutableMovieList

    fun loadMoviesList(context: Context) {
        viewModelScope.launch {
            try {
                delay(TIME_DELAY)
                val movies = loadMovies(context)
                _mutableMovieList.value = movies
            } catch (e: Exception) {
                Log.e(
                    MoviesListViewModel::class.java.simpleName,
                    "Error load movies data ${e.message}"
                )
            }
        }
    }

    companion object {
        const val TIME_DELAY: Long = 2_000
    }
}