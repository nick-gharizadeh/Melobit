package com.example.melobit.network

import com.example.melobit.data.model.song.SongResponse
import retrofit2.Response
import retrofit2.http.GET

interface MelobitApiService {

    @GET("song/new/0/11")
    suspend fun getNewSongs(): Response<SongResponse>

    @GET("song/slider/latest")
    suspend fun getSliders(): Response<SongResponse>

}