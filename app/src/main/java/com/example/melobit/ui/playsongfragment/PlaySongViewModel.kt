package com.example.melobit.ui.playsongfragment

import android.media.AudioAttributes
import android.media.MediaPlayer
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.melobit.data.model.song.Resource
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
    var mMediaPlayer: MediaPlayer = MediaPlayer()
    var isPaused = false
    var timeThatPaused = 0
    val songDetails = MutableLiveData<Song?>()

    fun playMusic(url: String) {
        makeMediaPlayerReadyForPlaying(url)
    }


    fun makeMediaPlayerReadyForPlaying(url: String) {
        viewModelScope.launch(Dispatchers.IO) {
            if (mMediaPlayer.isPlaying or isPaused) {
                stopPlaying()
            }
            try {
                mMediaPlayer.setAudioAttributes(
                    AudioAttributes.Builder()
                        .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
                        .build()
                )
                mMediaPlayer.setDataSource(url)
                mMediaPlayer.isLooping = true
                mMediaPlayer.prepare()
                mMediaPlayer.start()
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }


    fun pausePlaying() {
        mMediaPlayer.pause()
        isPaused = true
        timeThatPaused = mMediaPlayer.currentPosition
    }

    fun startPlaying(): Boolean {
        if (isPaused) {
            mMediaPlayer.seekTo(timeThatPaused)
            mMediaPlayer.start()
            return true
        }
        return false
    }


    fun stopPlaying() {
        isPaused = false
        mMediaPlayer.stop()
        mMediaPlayer.reset()
    }


     fun getSongById(songId: String) {
        viewModelScope.launch(Dispatchers.IO) {
            val response = songRepository.getSongById(songId)
            if (response.errorMessage == null) {
                songDetails.postValue(response.data)
            }
        }
    }

}