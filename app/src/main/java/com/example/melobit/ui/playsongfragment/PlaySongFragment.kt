package com.example.melobit.ui.playsongfragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SeekBar
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.example.melobit.data.model.song.Song
import com.example.melobit.databinding.FragmentPlaySongBinding
import com.example.melobit.ui.BaseViewModel
import com.example.melobit.ui.lyricsfragment.LyricsFragment
import dagger.hilt.android.AndroidEntryPoint
import java.util.*


@AndroidEntryPoint
class PlaySongFragment : Fragment() {
    var song: Song? = null
    val timer = Timer()
    private lateinit var binding: FragmentPlaySongBinding
    val playSongViewModel: PlaySongViewModel by viewModels()
    val baseViewModel: BaseViewModel by activityViewModels()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentPlaySongBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        song = activity?.intent?.extras!!.getSerializable("song") as Song
        song?.audio?.medium?.url?.let { baseViewModel.playMusic(it) }
        playSongViewModel.getSongById(song!!.id)
        binding.textViewPlaySongArtist.text = song?.artists?.get(0)?.fullName
        binding.textViewPlaySongTitle.text = song?.title
        Glide.with(requireContext()).load(song?.image?.cover?.url)
            .transform(GlideBlurTransformation(context)).into(binding.imageViewBackground)
        Glide.with(requireContext())
            .load(song?.image?.cover?.url)
            .apply(RequestOptions.bitmapTransform(RoundedCorners(30)))
            .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
            .into(binding.imageViewPlaySongCover)
        binding.seekBar.max = song?.duration!!
        timer.scheduleAtFixedRate(object : TimerTask() {
            override fun run() {
                binding.seekBar.progress = baseViewModel.mMediaPlayer.currentPosition / 1000
            }
        }, 0, 1000)
        binding.seekBar.setOnSeekBarChangeListener(seekBarListener)


        playSongViewModel.songDetails.observe(viewLifecycleOwner) {
            if (!it?.lyrics.isNullOrEmpty())
                binding.textViewLyrics.visibility = View.VISIBLE
        }

        binding.textViewLyrics.setOnClickListener {
            val songItem = playSongViewModel.songDetails.value
            if (songItem != null) {
                val dialogFragment = LyricsFragment(songItem)
                activity?.let {
                    dialogFragment.show(it.supportFragmentManager, "My  Fragment")
                }
            }
        }

        binding.imageViewPause.setOnClickListener {
            it.visibility = View.INVISIBLE
            binding.imageViewPlay.visibility = View.VISIBLE
            baseViewModel.pausePlaying()
        }

        binding.imageViewPlay.setOnClickListener {
            it.visibility = View.INVISIBLE
            binding.imageViewPause.visibility = View.VISIBLE
            baseViewModel.startPlaying()

        }
    }


    val seekBarListener = object : SeekBar.OnSeekBarChangeListener {
        override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
            if (fromUser) {
                baseViewModel.mMediaPlayer.seekTo(progress * 1000)
            }
        }

        override fun onStartTrackingTouch(seekBar: SeekBar?) {}
        override fun onStopTrackingTouch(seekBar: SeekBar?) {}


    }


    override fun onDestroy() {
        super.onDestroy()
        timer.cancel()
    }
}