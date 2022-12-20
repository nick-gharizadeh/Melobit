package com.example.melobit.data.model

data class SongResponse(
    val results: List<Song>,
    val total: Int
)