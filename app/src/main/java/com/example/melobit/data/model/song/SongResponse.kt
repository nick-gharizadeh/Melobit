package com.example.melobit.data.model.song

data class SongResponse(
    val results: List<Song>,
    val total: Int
)