package com.gmail.mxwild.newhabit.model

data class Movie(
    val name: String,
    val age: Int,
    val category: String,
    val rating: Float,
    val countOfReviews: Int,
    val length: Int,
    val imageCover: Int,
    val actors: List<Actor>
)