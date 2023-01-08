package com.example.melobit.data.model.search

data class Song(
    val album: Album,
    val artists: List<ArtistXX>,
    val audio: Audio,
    val copyrighted: Boolean,
    val downloadCount: String,
    val duration: Int,
    val hasVideo: Boolean,
    val id: String,
    val localized: Boolean,
    val releaseDate: String,
    val title: String
)