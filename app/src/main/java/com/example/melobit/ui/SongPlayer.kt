package com.example.melobit.ui

import android.media.AudioAttributes
import android.media.MediaPlayer
import androidx.lifecycle.MutableLiveData
import com.example.melobit.data.model.song.Song
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

object SongPlayer {
    var mMediaPlayer: MediaPlayer = MediaPlayer()
    var isPaused = false
    var timeThatPaused = 0
    var songPlayed = MutableLiveData(false)
    var song: Song? = null


    fun playMusic(song: Song) {
        // is it a new song to playing or is it the last one?
        if (this.song?.title != song.title) {
            this.song = song
            song.audio?.medium?.url?.let { makeMediaPlayerReadyForPlaying(it) }
        }
    }


    @OptIn(DelicateCoroutinesApi::class)
    fun makeMediaPlayerReadyForPlaying(url: String) {
        GlobalScope.launch(Dispatchers.IO) {
            stopPlaying()
            songPlayed.postValue(true)
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
        songPlayed.postValue(false)
        mMediaPlayer.stop()
        mMediaPlayer.reset()
    }


}