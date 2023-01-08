package com.example.melobit.ui.searchfragment


import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.example.melobit.R
import com.example.melobit.data.model.song.Song
import com.example.melobit.databinding.HorizontalSongItemBinding

typealias ClickHandlerSongSearch = (Song) -> Unit

class HorizontalSongItemAdapter() :
    ListAdapter<Song, HorizontalSongItemAdapter.ItemHolder>(SongDiffCallback) {

    object SongDiffCallback : DiffUtil.ItemCallback<Song>() {

        override fun areItemsTheSame(oldItem: Song, newItem: Song): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: Song, newItem: Song): Boolean {
            return oldItem.id == newItem.id
        }
    }

    class ItemHolder(val binding: HorizontalSongItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        private val imageViewCover: ImageView =
            itemView.findViewById(R.id.imageView_song_cover_horizontal)

        fun bind(song: Song) {
            if (song.image?.cover?.url?.isNotEmpty() == true) {
                Glide.with(itemView)
                    .load(song.image.cover.url)
                    .apply(RequestOptions.bitmapTransform(RoundedCorners(30)))
                    .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
                    .into(imageViewCover)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemHolder {
        val binding: HorizontalSongItemBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.horizontal_song_item,
            parent, false
        )
        return ItemHolder(binding)

    }

    override fun onBindViewHolder(holder: ItemHolder, position: Int) {
        val song = getItem(position)
        holder.binding.song = song
        holder.bind(song)
//        holder.binding.constraintLayout.setOnClickListener {
//            clickHandlerSong.invoke(song)
//        }

    }
}