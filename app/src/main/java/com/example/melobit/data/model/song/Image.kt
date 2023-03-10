package com.example.melobit.data.model.song

import java.io.Serializable

data class Image(
    val cover: Cover?,
    val cover_small: CoverSmall?,
    val thumbnail: Thumbnail?,
    val thumbnail_small: ThumbnailSmall?
): Serializable