package com.example.melobit.ui.playsongfragment

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.melobit.data.model.song.Song
import com.example.melobit.data.repository.SongRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PlaySongViewModel @Inject constructor(
    private val songRepository: SongRepository
) : ViewModel() {

    val songDetails = MutableLiveData<Song?>()
    fun getSongById(songId: String) {
        viewModelScope.launch(Dispatchers.IO) {
            val response = songRepository.getSongById(songId)
            if (response.errorMessage == null) {
                songDetails.postValue(response.data)
            }
        }
    }

}