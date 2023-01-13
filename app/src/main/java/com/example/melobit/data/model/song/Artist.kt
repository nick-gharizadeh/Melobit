package com.example.melobit.data.model.song

import java.io.Serializable

data class Artist(
    val followersCount: Int,
    val fullName: String,
    val id: String,
    val image: Image,
    val sumSongsDownloadsCount: String,
    val type: String
): Serializable