package com.gmail.mxwild.newhabit.domain

import com.gmail.mxwild.newhabit.R
import com.gmail.mxwild.newhabit.model.Actor

class MockActorData {
    fun getActors(): List<Actor> {
        return listOf(
            Actor(
                "Robert Downey Jr.",
                R.drawable.rdm1
            ),
            Actor(
                "Chris Evans",
                R.drawable.ce1
            ),
            Actor(
                "Mark Ruffalo",
                R.drawable.mr1
            ),
            Actor(
                "Chris Hemsworth",
                R.drawable.kh1
            )
        )
    }
}
