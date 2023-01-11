package com.example.melobit.ui.homefragment

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.melobit.data.model.artist.ArtistResponse
import com.example.melobit.data.model.song.Resource
import com.example.melobit.data.model.song.SongResponse
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

    val newSongsLiveData = MutableLiveData<Resource<SongResponse>?>()
    val topTenDayLiveData = MutableLiveData<Resource<SongResponse>?>()
    val topTenWeekLiveData = MutableLiveData<Resource<SongResponse>?>()
    val trendingArtistsLiveData = MutableLiveData<Resource<ArtistResponse>?>()
    val slidersLiveData = MutableLiveData<Resource<SongResponse>?>()
    var loadedResponseCount = MutableLiveData(0)

    init {
        getNewSongs()
        getSliders()
        getTopTenDay()
        getTopTenWeek()
        getTrendingArtists()
    }

    private fun getNewSongs() {
        newSongsLiveData.postValue(Resource.Loading())
        viewModelScope.launch(Dispatchers.IO) {
            val response = songRepository.getNewSongs()
            if (response.errorMessage == null) {
                newSongsLiveData.postValue(response)
                loadedResponseCount.postValue( loadedResponseCount.value?.plus(1))
            }
        }
    }

    private fun getSliders() {
        slidersLiveData.postValue(Resource.Loading())
        viewModelScope.launch(Dispatchers.IO) {
            val response = songRepository.getSliders()
            if (response.errorMessage == null) {
                slidersLiveData.postValue(response)
                loadedResponseCount.postValue( loadedResponseCount.value?.plus(1))

            }
        }
    }

    private fun getTopTenDay() {
        topTenDayLiveData.postValue(Resource.Loading())
        viewModelScope.launch(Dispatchers.IO) {
            val response = songRepository.getTopTenDaySongs()
            if (response.errorMessage == null) {
                topTenDayLiveData.postValue(response)
                loadedResponseCount.postValue( loadedResponseCount.value?.plus(1))

            }
        }
    }

    private fun getTopTenWeek() {
        topTenWeekLiveData.postValue(Resource.Loading())
        viewModelScope.launch(Dispatchers.IO) {
            val response = songRepository.getTopTenWeekSongs()
            if (response.errorMessage == null) {
                topTenWeekLiveData.postValue(response)
                loadedResponseCount.postValue( loadedResponseCount.value?.plus(1))

            }
        }
    }

    private fun getTrendingArtists() {
        trendingArtistsLiveData.postValue(Resource.Loading())
        viewModelScope.launch(Dispatchers.IO) {
            val response = artistRepository.getTrendingArtists()
            if (response.errorMessage == null) {
                trendingArtistsLiveData.postValue(response)
                loadedResponseCount.postValue( loadedResponseCount.value?.plus(1))

            }
        }
    }


}