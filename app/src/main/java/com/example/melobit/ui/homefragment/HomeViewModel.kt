package com.example.melobit.ui.homefragment

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.melobit.data.model.song.ArtistX
import com.example.melobit.data.model.song.Song
import com.example.melobit.data.repository.ArtistRepository
import com.example.melobit.data.repository.SongRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class HomeViewModel @Inject constructor(
    private val songRepository: SongRepository,
    private val artistRepository: ArtistRepository
) :
    ViewModel() {

    val newSongsLiveData = MutableLiveData<List<Song>?>()
    val topTenDayLiveData = MutableLiveData<List<Song>?>()
    val topTenWeekLiveData = MutableLiveData<List<Song>?>()
    val trendingArtistsLiveData = MutableLiveData<List<ArtistX>?>()
    val slidersLiveData = MutableLiveData<List<Song>?>()

    init {
        getNewSongs()
        getSliders()
        getTopTenDay()
        getTopTenWeek()
        getTrendingArtists()
    }

    private fun getNewSongs() {
        viewModelScope.launch(Dispatchers.IO) {
            val response = songRepository.getNewSongs()
            if (response.errorMessage == null)
                newSongsLiveData.postValue(response.data?.results)
        }
    }

    private fun getSliders() {
        viewModelScope.launch(Dispatchers.IO) {
            val response = songRepository.getSliders()
            if (response.errorMessage == null)
                slidersLiveData.postValue(response.data?.results)
        }
    }

    private fun getTopTenDay() {
        viewModelScope.launch(Dispatchers.IO) {
            val response = songRepository.getTopTenDaySongs()
            if (response.errorMessage == null)
                topTenDayLiveData.postValue(response.data?.results)
        }
    }

    private fun getTopTenWeek() {
        viewModelScope.launch(Dispatchers.IO) {
            val response = songRepository.getTopTenWeekSongs()
            if (response.errorMessage == null)
                topTenWeekLiveData.postValue(response.data?.results)
        }
    }

    private fun getTrendingArtists() {
        viewModelScope.launch(Dispatchers.IO) {
            val response = artistRepository.getTrendingArtists()
            if (response.errorMessage == null)
                trendingArtistsLiveData.postValue(response.data?.results)
        }
    }


}