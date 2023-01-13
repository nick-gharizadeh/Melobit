package com.example.melobit.ui.searchfragment

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.melobit.data.model.song.Song
import com.example.melobit.databinding.FragmentSearchBinding
import com.example.melobit.ui.PlaySongActivity
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class SearchFragment : Fragment() {

    private lateinit var binding: FragmentSearchBinding
    val searchViewModel: SearchViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSearchBinding.inflate(layoutInflater)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val adapter = HorizontalSongItemAdapter{goToPlaySongFragment(it)}
        binding.searchRecyclerView.adapter = adapter
        searchViewModel.searchLiveData.observe(viewLifecycleOwner) {
            adapter.submitList(it)

        }

        binding.editTextSearch.editText?.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(charSequence: CharSequence, i: Int, i2: Int, i3: Int) {}
            override fun onTextChanged(charSequence: CharSequence, i: Int, i2: Int, i3: Int) {}
            override fun afterTextChanged(edit: Editable) {
                if (edit.isNotEmpty()) {
                    searchViewModel.searchSongs(edit.toString())
                }
            }
        })
    }


    private fun goToPlaySongFragment(song: Song) {
        val intent = Intent(requireActivity(), PlaySongActivity::class.java)
        intent.putExtra("song", song)
        startActivity(intent)
    }

}