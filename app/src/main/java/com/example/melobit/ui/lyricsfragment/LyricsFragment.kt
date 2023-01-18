package com.example.melobit.ui.lyricsfragment

import android.R
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.fragment.app.DialogFragment
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.example.melobit.data.model.song.Song
import com.example.melobit.databinding.FragmentLyricsBinding


class LyricsFragment(val song: Song) : DialogFragment() {

    private lateinit var binding: FragmentLyricsBinding

    override fun onStart() {
        super.onStart()
        dialog?.getWindow()?.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.MATCH_PARENT);

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentLyricsBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Glide.with(requireContext())
            .load(song.image?.cover?.url)
            .apply(RequestOptions.bitmapTransform(RoundedCorners(30)))
            .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
            .into(binding.imageViewCover)
        binding.textViewSongArtistLyrics.text = song.artists?.get(0)?.fullName
        binding.textViewSongTitleLyrics.text = song.title
        binding.textViewLyricsText.text = song.lyrics.toString()

    }


}