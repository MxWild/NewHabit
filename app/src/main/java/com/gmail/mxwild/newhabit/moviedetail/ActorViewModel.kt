package com.gmail.mxwild.newhabit.moviedetail

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gmail.mxwild.newhabit.model.DtoMapper
import com.gmail.mxwild.newhabit.model.data.Actor
import com.gmail.mxwild.newhabit.services.NetworkService
import kotlinx.coroutines.launch

class ActorViewModel : ViewModel() {

    private val _mutableActorList = MutableLiveData<List<Actor>>()
    val actorList: LiveData<List<Actor>> get() = _mutableActorList

    fun loadActorsByMovieId(movieId: Int) {
        viewModelScope.launch {
            try {
                _mutableActorList.value = loadActors(movieId)
            } catch (e: Exception) {
                Log.e(
                    ActorViewModel::class.java.simpleName,
                    "Error load actors by movieId := $movieId ${e.message}"
                )
            }
        }
    }

    private suspend fun loadActors(movieId: Int): List<Actor> {
        return NetworkService.movieApiService
            .getActors(movieId)
            .cast
            .filter { it.profileImg != null }
            .map { actorDto -> DtoMapper.convertActorFromDto(actorDto) }
    }
}