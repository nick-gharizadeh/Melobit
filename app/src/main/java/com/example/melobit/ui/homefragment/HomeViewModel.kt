package com.example.melobit.ui.homefragment

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.melobit.data.model.Song
import com.example.melobit.data.repository.SongRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class HomeViewModel @Inject constructor(private val songRepository: SongRepository) :
    ViewModel() {

    val newSongsLiveData = MutableLiveData<List<Song>?>()

    init {
        getNewSongs()
    }

    fun getNewSongs() {
        viewModelScope.launch {
            val response = songRepository.getNewSongs()
            if (response.errorMessage == null)
                newSongsLiveData.postValue(response.data?.results)
        }
    }
}