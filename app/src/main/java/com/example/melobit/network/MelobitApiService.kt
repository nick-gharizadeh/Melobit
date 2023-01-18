package com.example.melobit.network

import com.example.melobit.data.model.artist.ArtistResponse
import com.example.melobit.data.model.search.SearchResponse
import com.example.melobit.data.model.song.Song
import com.example.melobit.data.model.song.SongResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path


interface MelobitApiService {

    @GET("song/new/0/11")
    suspend fun getNewSongs(): Response<SongResponse>

    @GET("song/slider/latest")
    suspend fun getSliders(): Response<SongResponse>

    @GET("artist/trending/0/4")
    suspend fun getTrendingArtists(): Response<ArtistResponse>

    @GET("song/top/day/0/10")
    suspend fun getTopTenDaySongs(): Response<SongResponse>

    @GET("song/top/week/0/15")
    suspend fun getTopTenWeekSongs(): Response<SongResponse>

    @GET("search/query/{query}/0/15")
    suspend fun search(@Path("query") query: String): Response<SearchResponse>

    @GET("song/{id}")
    suspend fun getSongById(@Path("id") songId: String?): Response<Song>
}