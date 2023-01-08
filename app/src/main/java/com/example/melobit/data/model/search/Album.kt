package com.example.melobit.data.model.search

data class Album(
    val artists: List<ArtistX>,
    val id: String,
    val name: String,
    val releaseDate: String
)