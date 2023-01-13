package com.example.melobit.ui.homefragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.melobit.data.model.song.Resource
import com.example.melobit.data.model.song.Song
import com.example.melobit.databinding.FragmentHomeBinding
import com.example.melobit.ui.PlaySongActivity
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
        val adapter = VerticalSongItemAdapter { goToPlaySongFragment(it) }
        val adapterTopTenDaySongs = VerticalSongItemAdapter { goToPlaySongFragment(it) }
        val adapterTopTenWeekSongs = VerticalSongItemAdapter { goToPlaySongFragment(it) }
        val adapterTrendingArtist = ArtistAdapter()
        binding.newSongsRecyclerView.adapter = adapter
        binding.topTenDayRecyclerView.adapter = adapterTopTenDaySongs
        binding.topTenWeekRecyclerView.adapter = adapterTopTenWeekSongs
        binding.trendingArtistsRecyclerView.adapter = adapterTrendingArtist

        homeViewModel.loadedResponseCount.observe(viewLifecycleOwner) {
            //TODO: uncomment
//            Toast.makeText(requireContext(), it.toString(), Toast.LENGTH_SHORT).show()
//            if (it >= 4) {
                binding.groupLayout.visibility = View.VISIBLE
                binding.animationViewLoadingMain.visibility = View.INVISIBLE
//            }

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
                    it?.data?.results?.let { it1 -> MainViewPagerAdapter(requireContext(), it1) }
                binding.mainViewPager.adapter = mViewPagerAdapter
                //TODO: uncomment
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

    private fun goToPlaySongFragment(song: Song) {
        val intent = Intent(requireActivity(), PlaySongActivity::class.java)
        intent.putExtra("song", song)
        startActivity(intent)
    }
}