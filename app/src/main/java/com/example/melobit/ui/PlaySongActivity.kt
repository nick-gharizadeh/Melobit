package com.example.melobit.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.melobit.R
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class PlaySongActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_play_song)
        supportActionBar?.hide()
    }
}