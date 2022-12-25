package com.example.melobit.ui.homefragment

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.Toast
import androidx.viewpager.widget.PagerAdapter
import com.bumptech.glide.Glide
import com.example.melobit.R
import com.example.melobit.data.model.song.Song
import com.squareup.picasso.Picasso
import java.util.*



class MainViewPagerAdapter(var context: Context, private var songsList: List<Song>) :
    PagerAdapter() {
    private var mLayoutInflater: LayoutInflater =
        context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

    override fun getCount(): Int {
        return songsList.size
    }

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view === `object` as LinearLayout
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val itemView: View = mLayoutInflater.inflate(R.layout.main_view_pager_item, container, false)
        val imageView: ImageView = itemView.findViewById(R.id.imageView_main_viewPager) as ImageView
        Glide.with(itemView)
            .load(songsList[position].image.slider?.url.toString())
            .placeholder(R.drawable.place_holder)
            .into(imageView)
        Toast.makeText(context,songsList[position].image.slider?.url.toString() , Toast.LENGTH_SHORT).show()
        Objects.requireNonNull(container).addView(itemView)
        return itemView
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as LinearLayout)
    }
}