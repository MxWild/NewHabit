package com.gmail.mxwild.newhabit.movieslist

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gmail.mxwild.newhabit.model.DtoMapper
import com.gmail.mxwild.newhabit.model.data.Movie
import com.gmail.mxwild.newhabit.services.NetworkService
import kotlinx.coroutines.launch

class MoviesListViewModel : ViewModel() {

    private val _mutableMovieList = MutableLiveData<List<Movie>>()
    val moviesList: LiveData<List<Movie>> get() = _mutableMovieList

    fun loadMoviesList() {
        viewModelScope.launch {
            try {
                _mutableMovieList.value = loadMoviesFromNetwork()
            } catch (e: Exception) {
                Log.e(
                    MoviesListViewModel::class.java.simpleName,
                    "Error load movies data ${e.message}"
                )
            }
        }
    }

    private suspend fun loadMoviesFromNetwork(): List<Movie> {
        val topRatedMovies = NetworkService.movieApiService
            .getTopRated()
            .results
            .map { it.id }

        return topRatedMovies.distinct().map {
            DtoMapper.convertMovieFromDto(
                NetworkService.movieApiService.getMovieById(it)
            )
        }
    }

}