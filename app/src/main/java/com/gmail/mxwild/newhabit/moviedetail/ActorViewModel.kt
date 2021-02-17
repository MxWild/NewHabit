package com.gmail.mxwild.newhabit.moviedetail

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gmail.mxwild.newhabit.model.data.Actor
import com.gmail.mxwild.newhabit.repository.ActorRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ActorViewModel @Inject constructor(
    private val actorRepository: ActorRepository
) : ViewModel() {

    private val _mutableActorList = MutableLiveData<List<Actor>>()
    val actorList: LiveData<List<Actor>> get() = _mutableActorList

    fun loadActorsByMovieId(movieId: Int) {
        viewModelScope.launch {
            try {
                _mutableActorList.value = actorRepository.getActors(movieId)
            } catch (e: Exception) {
                Log.e(
                    ActorViewModel::class.java.simpleName,
                    "Error load actors by movieId := $movieId ${e.message}"
                )
            }
        }
    }
}