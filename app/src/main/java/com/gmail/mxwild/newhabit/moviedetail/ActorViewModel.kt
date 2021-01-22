package com.gmail.mxwild.newhabit.moviedetail

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gmail.mxwild.newhabit.model.data.Actor
import com.gmail.mxwild.newhabit.repository.ActorRepository
import kotlinx.coroutines.launch

class ActorViewModel : ViewModel() {

    private val actorRepository = ActorRepository()

    private val _mutableActorList = MutableLiveData<List<Actor>>()
    val actorList: LiveData<List<Actor>> get() = _mutableActorList

    fun loadActorsByMovieId(movieId: Int) {
        viewModelScope.launch {
            try {
                val actorsFromDB = actorRepository.getActorFromDB(movieId)

                if (actorsFromDB.isNotEmpty()) {
                    _mutableActorList.value = actorsFromDB
                } else {
                    _mutableActorList.value = actorRepository.getActors(movieId)
                    actorRepository.saveActors(actorList.value, movieId)
                }
            } catch (e: Exception) {
                Log.e(
                    ActorViewModel::class.java.simpleName,
                    "Error load actors by movieId := $movieId ${e.message}"
                )
            }
        }
    }
}