package com.example.melobit.ui.homefragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.melobit.databinding.FragmentHomeBinding
import dagger.hilt.android.AndroidEntryPoint

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
        val adapter = VerticalSongItemAdapter()
        binding.newSongsRecyclerView.adapter = adapter
        homeViewModel.newSongsLiveData.observe(viewLifecycleOwner) {
            adapter.submitList(it)
        }
        homeViewModel.slidersLiveData.observe(viewLifecycleOwner) {
            if (it != null) {
                val mViewPagerAdapter =
                    MainViewPagerAdapter(requireContext(), it)
                binding.mainViewPager.adapter = mViewPagerAdapter
            }
        }
    }
}