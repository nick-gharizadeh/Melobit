package com.example.melobit.ui.homefragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.melobit.data.model.song.Song
import com.example.melobit.databinding.FragmentHomeBinding
import dagger.hilt.android.AndroidEntryPoint
import java.util.*

@AndroidEntryPoint
class HomeFragment : Fragment() {
    val homeViewModel: HomeViewModel by viewModels()
    private lateinit var binding: FragmentHomeBinding
    private var timer: Timer? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val adapter = VerticalSongItemAdapter{goToPlaySongFragment(it)}
        val adapterTopTenDaySongs = VerticalSongItemAdapter{goToPlaySongFragment(it)}
        val adapterTopTenWeekSongs = VerticalSongItemAdapter{goToPlaySongFragment(it)}
        val adapterTrendingArtist = ArtistAdapter()
        binding.newSongsRecyclerView.adapter = adapter
        binding.topTenDayRecyclerView.adapter = adapterTopTenDaySongs
        binding.topTenWeekRecyclerView.adapter = adapterTopTenWeekSongs
        binding.trendingArtistsRecyclerView.adapter = adapterTrendingArtist

        homeViewModel.newSongsLiveData.observe(viewLifecycleOwner) {
            adapter.submitList(it)
        }
        homeViewModel.trendingArtistsLiveData.observe(viewLifecycleOwner) {
            adapterTrendingArtist.submitList(it)
        }
        homeViewModel.topTenDayLiveData.observe(viewLifecycleOwner) {
            adapterTopTenDaySongs.submitList(it)
            if (it != null)
                Toast.makeText(requireContext(), it[0].id, Toast.LENGTH_LONG).show()
            else
                Toast.makeText(requireContext(), "null", Toast.LENGTH_LONG).show()

        }
        homeViewModel.topTenWeekLiveData.observe(viewLifecycleOwner) {
            adapterTopTenWeekSongs.submitList(it)
        }
        homeViewModel.slidersLiveData.observe(viewLifecycleOwner) {

            if (it != null) {
                val mViewPagerAdapter =
                    MainViewPagerAdapter(requireContext(), it)
                binding.mainViewPager.adapter = mViewPagerAdapter
//
//                val timerTask: TimerTask = object : TimerTask() {
//                    override fun run() {
//                        binding.mainViewPager.post {
//                            binding.mainViewPager.currentItem =
//                                (binding.mainViewPager.currentItem + 1) % it.size
//                        }
//                    }
//                }
//                timer = Timer()
//                timer!!.schedule(timerTask, 5000, 5000)
            }
        }
    }

    private fun goToPlaySongFragment(song:Song){

    }

}