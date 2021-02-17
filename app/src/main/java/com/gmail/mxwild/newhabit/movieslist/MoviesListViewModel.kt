package com.gmail.mxwild.newhabit.movieslist

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gmail.mxwild.newhabit.model.data.Movie
import com.gmail.mxwild.newhabit.repository.MovieRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import javax.inject.Inject

@HiltViewModel
class MoviesListViewModel @Inject constructor(
    private var movieRepository: MovieRepository
) : ViewModel() {

    private val _mutableMovieList = MutableLiveData<List<Movie>>()
    val moviesList: LiveData<List<Movie>> get() = _mutableMovieList
    private val mutex = Mutex()

    fun loadMoviesList(isReloadData: Boolean) {
        viewModelScope.launch {
            try {
                mutex.withLock {
                    _mutableMovieList.value = movieRepository.getMovies(isReloadData, false)
                }
            } catch (e: Exception) {
                Log.e(
                    MoviesListViewModel::class.java.simpleName,
                    "Error load movies data ${e.message}"
                )
            }
        }
    }
}