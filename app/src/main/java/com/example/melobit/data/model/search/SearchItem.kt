package com.example.melobit.data.model.search

import com.example.melobit.data.model.song.Song

data class SearchItem(
    val artist: Artist?,
    val position: Int?,
    val song: Song?,
    val type: String?
)