package com.example.melobit.ui.homefragment

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.melobit.R
import com.example.melobit.data.model.Song
import com.example.melobit.databinding.VerticalSongItemBinding


class VerticalSongItemAdapter() :
    ListAdapter<Song, VerticalSongItemAdapter.ItemHolder>(SongDiffCallback) {

    object SongDiffCallback : DiffUtil.ItemCallback<Song>() {

        override fun areItemsTheSame(oldItem: Song, newItem: Song): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: Song, newItem: Song): Boolean {
            return oldItem.id == newItem.id
        }
    }
    class ItemHolder(val binding: VerticalSongItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        private val imageViewCover: ImageView = itemView.findViewById(R.id.imageView_song_cover_vertical)
        fun bind(song: Song) {
            if (song.image.cover.url.isNotEmpty()) {
                Glide.with(itemView)
                    .load(song.image.cover.url)
                    .into(imageViewCover)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemHolder {
        val binding: VerticalSongItemBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.vertical_song_item,
            parent, false
        )
        return ItemHolder(binding)

    }

    override fun onBindViewHolder(holder: ItemHolder, position: Int) {
        val song = getItem(position)
        holder.binding.song = song
        holder.bind(song)

    }
}