package com.example.melobit.data.model.search

data class SearchItem(
    val artist: Artist,
    val position: Int,
    val song: Song,
    val type: String
)