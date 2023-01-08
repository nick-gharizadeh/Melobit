package com.example.melobit.data.repository

import com.example.melobit.data.model.search.SearchResponse
import com.example.melobit.data.model.song.Resource
import com.example.melobit.network.MelobitApiService
import javax.inject.Inject

class SearchRepository @Inject constructor(private val songApiService: MelobitApiService) :
    BaseRepo() {

    suspend fun searchSong(query: String): Resource<SearchResponse> {
        return safeApiCall { songApiService.search(query) }
    }


}