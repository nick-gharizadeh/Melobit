package com.example.melobit.data.model.song

data class Album(
    val artists: List<Artist>,
    val image: ImageX,
    val name: String,
    val releaseDate: String
)