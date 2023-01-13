package com.example.melobit.data.model.song

import java.io.Serializable

data class Album(
    val artists: List<Artist?>?,
    val image: ImageX?,
    val name: String?,
    val releaseDate: String?
): Serializable