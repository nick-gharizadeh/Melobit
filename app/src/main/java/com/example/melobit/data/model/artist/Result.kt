package com.example.melobit.data.model.artist

data class Result(
    val followersCount: Int,
    val fullName: String,
    val id: String,
    val image: Image,
    val sumSongsDownloadsCount: String,
    val type: String
)