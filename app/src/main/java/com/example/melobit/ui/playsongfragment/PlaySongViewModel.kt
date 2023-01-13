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
    var timeThatPaused = 0

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


}