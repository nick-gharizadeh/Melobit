package com.example.melobit.data.repository

import com.example.melobit.data.model.Resource
import com.example.melobit.data.model.Song
import com.example.melobit.network.MelobitApiService
import javax.inject.Inject

class SongRepository @Inject constructor(private val songApiService: MelobitApiService) :
    BaseRepo() {

    suspend fun getNewSongs(): Resource<List<Song>> {
        return safeApiCall { songApiService.getNewSongs() }
    }

}