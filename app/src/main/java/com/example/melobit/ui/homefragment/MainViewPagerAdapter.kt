package com.example.melobit.ui.homefragment

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.viewpager.widget.PagerAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.example.melobit.R
import com.example.melobit.data.model.song.Song
import java.util.*


class MainViewPagerAdapter(var context: Context, private var songsList: List<Song>) :
    PagerAdapter() {
    private var mLayoutInflater: LayoutInflater =
        context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

    override fun getCount(): Int {
        return songsList.size
    }

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view === `object` as ConstraintLayout
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val itemView: View =
            mLayoutInflater.inflate(R.layout.main_view_pager_item, container, false)
        val imageView: ImageView = itemView.findViewById(R.id.imageView_main_viewPager) as ImageView
        val textViewSongName: TextView =
            itemView.findViewById(R.id.textView_main_view_pager_song_name) as TextView
        val textViewSongArtist: TextView =
            itemView.findViewById(R.id.textView_main_view_pager_song_artist) as TextView
        val song = songsList[position]
        textViewSongArtist.text = song.artists[0].fullName
        textViewSongName.text = song.title
        Glide.with(itemView)
            .load(song.image.slider?.url.toString())
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .apply(RequestOptions.bitmapTransform(RoundedCorners(20)))
            .into(imageView)
        Objects.requireNonNull(container).addView(itemView)
        return itemView
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as ConstraintLayout)
    }
}