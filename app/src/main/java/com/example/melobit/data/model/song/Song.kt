package com.example.melobit.data.model.song

data class Song(
    val album: Album?,
    val artists: List<ArtistX?>?,
    val audio: Audio?,
    val downloadCount: String?,
    val duration: Int?,
    val id: String,
    val image: ImageXXX?,
    val releaseDate: String?,
    val title: String?
)