package com.example.melobit.data.repository

import com.example.melobit.data.model.artist.ArtistResponse
import com.example.melobit.data.model.song.Resource
import com.example.melobit.network.MelobitApiService
import javax.inject.Inject

class ArtistRepository @Inject constructor(private val songApiService: MelobitApiService) :
    BaseRepo() {

    suspend fun getTrendingArtists(): Resource<ArtistResponse> {
        return safeApiCall { songApiService.getTrendingArtists() }
    }
}