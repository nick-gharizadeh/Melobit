package com.example.melobit.ui.homefragment

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
import com.example.melobit.data.model.song.ArtistX
import com.example.melobit.databinding.ArtistItemBinding


class ArtistAdapter() :
    ListAdapter<ArtistX, ArtistAdapter.ItemHolder>(ArtistXDiffCallback) {

    object ArtistXDiffCallback : DiffUtil.ItemCallback<ArtistX>() {

        override fun areItemsTheSame(oldItem: ArtistX, newItem: ArtistX): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: ArtistX, newItem: ArtistX): Boolean {
            return oldItem.id == newItem.id
        }
    }

    class ItemHolder(val binding: ArtistItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        private val imageViewCover: ImageView = itemView.findViewById(R.id.imageView_artist_cover)
        fun bind(artist: ArtistX) {
            if (artist.image.cover.url.isNotEmpty()) {
                Glide.with(itemView)
                    .load(artist.image.cover.url)
                    .apply(RequestOptions.bitmapTransform(RoundedCorners(30)))
                    .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
                    .into(imageViewCover)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemHolder {
        val binding: ArtistItemBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.artist_item,
            parent, false
        )
        return ItemHolder(binding)

    }

    override fun onBindViewHolder(holder: ItemHolder, position: Int) {
        val artist = getItem(position)
        holder.binding.artist = artist
        holder.bind(artist)

    }
}