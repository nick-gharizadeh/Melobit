package com.example.melobit.data.model.song

import java.io.Serializable

data class SongResponse(
    val results: List<Song>,
    val total: Int
): Serializable