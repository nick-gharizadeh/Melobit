package com.example.melobit.ui

import android.media.AudioAttributes
import android.media.MediaPlayer
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

object SongPlayer {
    var mMediaPlayer: MediaPlayer = MediaPlayer()
    var isPaused = false
    var timeThatPaused = 0


    fun playMusic(url: String) {
        makeMediaPlayerReadyForPlaying(url)
    }


    @OptIn(DelicateCoroutinesApi::class)
    fun makeMediaPlayerReadyForPlaying(url: String) {
        GlobalScope.launch(Dispatchers.IO) {
            stopPlaying()
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


}