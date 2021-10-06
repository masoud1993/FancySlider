package com.masa.fancyslider

import android.content.Context
import android.graphics.Bitmap
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.VideoView
import androidx.annotation.NonNull
import androidx.viewpager.widget.PagerAdapter
import com.bumptech.glide.Glide
import java.util.*

class SliderAdapter (private val context: Context, private val items: MutableList<Any>) : PagerAdapter() {
    var mLayoutInflater: LayoutInflater? = null
    private val videos = mutableMapOf<Int, VideoView>()

    init {
        mLayoutInflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
    }

    override fun getCount(): Int {
        // return the number of images
        return items.size
    }

    override fun isViewFromObject(@NonNull view: View, @NonNull `object`: Any): Boolean {
        return view === `object` as FrameLayout
    }

    @NonNull
    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val item = items[position]
        val itemView: View = mLayoutInflater!!.inflate(R.layout.slider_item, container, false)

        if (item is Bitmap) {
            val imageView: ImageView = itemView.findViewById(R.id.image) as ImageView
            Glide.with(context).load(item).override(600, 400).into(imageView)
        } else {
            val videoView: VideoView = itemView.findViewById(R.id.video) as VideoView
            videoView.visibility = View.VISIBLE
            videoView.setVideoPath(item as String)
            videoView.setOnPreparedListener {
                it.setVolume(0F, 0F)
            }

            videos[position] = videoView
        }
        Objects.requireNonNull(container).addView(itemView)
        return itemView
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as FrameLayout)
    }

    fun startVideo(position: Int) {
        videos[position]?.start()
    }

}