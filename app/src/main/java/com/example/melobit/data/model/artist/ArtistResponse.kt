package com.example.melobit.data.model.artist

import com.example.melobit.data.model.song.ArtistX

data class ArtistResponse(
    val results: List<ArtistX>,
    val total: Int
)