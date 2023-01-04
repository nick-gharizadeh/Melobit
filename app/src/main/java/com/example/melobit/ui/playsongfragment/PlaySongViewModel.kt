package com.example.melobit.ui.playsongfragment

import android.media.AudioAttributes
import android.media.MediaPlayer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class PlaySongViewModel : ViewModel() {
    var mMediaPlayer: MediaPlayer = MediaPlayer()
    var isPaused = false

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
                mMediaPlayer.prepare()
                mMediaPlayer.start()
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }


    fun stopPlaying() {
        isPaused = false
        mMediaPlayer.stop()
        mMediaPlayer.reset()
    }

    override fun onCleared() {
        super.onCleared()
        stopPlaying()
    }
}