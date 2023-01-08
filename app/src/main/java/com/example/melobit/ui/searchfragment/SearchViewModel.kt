package com.example.melobit.ui.searchfragment

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.melobit.data.model.search.Song
import com.example.melobit.data.repository.SearchRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val searchRepository: SearchRepository,
) : ViewModel() {

    val searchLiveData = MutableLiveData<List<Song>?>()

    private fun searchSongs(query: String) {
        viewModelScope.launch(Dispatchers.IO) {
            val response = searchRepository.searchSong(query)
            val newList = mutableListOf<Song>()
            if (response.errorMessage == null)
                for (item in response.data!!.results)
                    if (item.type == "song")
                        newList.add(item.song)
            searchLiveData.postValue(newList)

        }
    }

}