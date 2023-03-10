package com.example.melobit.ui.homefragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.example.melobit.data.model.song.Resource
import com.example.melobit.data.model.song.Song
import com.example.melobit.databinding.FragmentHomeBinding
import com.example.melobit.ui.PlaySongActivity
import com.example.melobit.ui.SongPlayer
import dagger.hilt.android.AndroidEntryPoint
import java.util.*

@AndroidEntryPoint
class HomeFragment : Fragment() {
    val homeViewModel: HomeViewModel by viewModels()
    private lateinit var binding: FragmentHomeBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val adapter = VerticalSongItemAdapter { goToPlaySongFragment(it) }
        val adapterTopTenDaySongs = VerticalSongItemAdapter { goToPlaySongFragment(it) }
        val adapterTopTenWeekSongs = VerticalSongItemAdapter { goToPlaySongFragment(it) }
        val adapterTrendingArtist = ArtistAdapter()
        binding.newSongsRecyclerView.adapter = adapter
        binding.topTenDayRecyclerView.adapter = adapterTopTenDaySongs
        binding.topTenWeekRecyclerView.adapter = adapterTopTenWeekSongs
        binding.trendingArtistsRecyclerView.adapter = adapterTrendingArtist

        binding.cardViewNowPlaying.setOnClickListener {
            val intent = Intent(requireActivity(), PlaySongActivity::class.java)
            intent.putExtra("song", SongPlayer.song)
            startActivity(intent)
        }

        SongPlayer.songPlayed.observe(viewLifecycleOwner) {
            if (it == true) {
                binding.cardViewNowPlaying.visibility = View.VISIBLE
                binding.textViewNowPlayingSongTitle.text = SongPlayer.song?.title.toString()
                binding.textViewNowPlayingSongArtist.text =
                    SongPlayer.song?.artists?.get(0)?.fullName

                Glide.with(requireContext())
                    .load(SongPlayer.song?.image?.cover?.url)
                    .apply(RequestOptions.bitmapTransform(RoundedCorners(30)))
                    .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
                    .into(binding.imageViewNowPlayingCover)

            } else {
                binding.cardViewNowPlaying.visibility = View.GONE

            }
        }

        homeViewModel.loadedResponseCount.observe(viewLifecycleOwner) {
                binding.groupLayout.visibility = View.VISIBLE
                binding.animationViewLoadingMain.visibility = View.GONE
        }
        homeViewModel.newSongsLiveData.observe(viewLifecycleOwner) {
            if (it !is Resource.Loading) {
                adapter.submitList(it?.data?.results)
            }
        }
        homeViewModel.trendingArtistsLiveData.observe(viewLifecycleOwner) {
            if (it !is Resource.Loading) {
                adapterTrendingArtist.submitList(it?.data?.results)
            }
        }
        homeViewModel.topTenDayLiveData.observe(viewLifecycleOwner) {
            if (it !is Resource.Loading || it.data?.results?.size == 0) {
                adapterTopTenDaySongs.submitList(it?.data?.results)
            }
        }
        homeViewModel.topTenWeekLiveData.observe(viewLifecycleOwner) {
            if (it !is Resource.Loading) {
                adapterTopTenWeekSongs.submitList(it?.data?.results)
            }
        }
        homeViewModel.slidersLiveData.observe(viewLifecycleOwner) {
            if (it !is Resource.Loading) {
                val mViewPagerAdapter =
                    it?.data?.results?.let { it1 ->
                        MainViewPagerAdapter(
                            requireContext(),
                            it1
                        )
                    }
                binding.mainViewPager.adapter = mViewPagerAdapter
            }
        }
    }

    private fun goToPlaySongFragment(song: Song) {
        val intent = Intent(requireActivity(), PlaySongActivity::class.java)
        intent.putExtra("song", song)
        startActivity(intent)
    }
}