package com.example.melobit.ui.playsongfragment

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
import com.example.melobit.databinding.FragmentPlaySongBinding


class PlaySongFragment(var song: Song) : DialogFragment() {
    private lateinit var binding: FragmentPlaySongBinding


    override fun onResume() {
        super.onResume()
        val params: ViewGroup.LayoutParams = dialog!!.window!!.attributes
        params.width = WindowManager.LayoutParams.MATCH_PARENT
        params.height = WindowManager.LayoutParams.MATCH_PARENT
        dialog!!.window!!.attributes = params as WindowManager.LayoutParams
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentPlaySongBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.textViewPlaySongArtist.text = song.artists?.get(0)?.fullName
        binding.textViewPlaySongTitle.text = song.title
        binding.seekBar.max = song.duration!!
        Glide.with(requireContext())
            .load(song.image?.cover?.url)
            .apply(RequestOptions.bitmapTransform(RoundedCorners(30)))
            .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
            .into(binding.imageViewPlaySongCover)
    }
}