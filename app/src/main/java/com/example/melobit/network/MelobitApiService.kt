package com.example.melobit.network

import com.example.melobit.data.model.SongResponse
import retrofit2.Response
import retrofit2.http.GET

interface MelobitApiService {

    @GET("song/new/0/11")
    suspend fun getNewSongs(): Response<SongResponse>

}