package com.example.melobit.data.repository

import com.example.melobit.data.model.song.Resource
import com.example.melobit.data.model.song.SongResponse
import com.example.melobit.network.MelobitApiService
import javax.inject.Inject

class SongRepository @Inject constructor(private val songApiService: MelobitApiService) :
    BaseRepo() {

    suspend fun getNewSongs(): Resource<SongResponse> {
        return safeApiCall { songApiService.getNewSongs() }
    }

    suspend fun getSliders(): Resource<SongResponse> {
        return safeApiCall { songApiService.getSliders() }
    }

    suspend fun getTopTenDaySongs(): Resource<SongResponse> {
        return safeApiCall { songApiService.getTopTenDaySongs() }
    }

    suspend fun getTopTenWeekSongs(): Resource<SongResponse> {
        return safeApiCall { songApiService.getTopTenWeekSongs() }
    }
}