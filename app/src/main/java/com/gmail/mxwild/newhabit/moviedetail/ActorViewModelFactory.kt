package com.gmail.mxwild.newhabit.moviedetail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class ActorViewModelFactory : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T = when(modelClass) {
        ActorViewModel::class.java -> ActorViewModel()
        else -> throw IllegalArgumentException("$modelClass is not registered ViewModel")
    } as T
}