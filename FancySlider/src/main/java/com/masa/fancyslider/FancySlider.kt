package com.masa.fancyslider

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Bitmap
import android.util.AttributeSet
import android.util.Log
import androidx.lifecycle.Lifecycle
import androidx.viewpager.widget.PagerAdapter
import androidx.viewpager.widget.ViewPager
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

@SuppressLint("ClickableViewAccessibility")
class FancySlider(context: Context, attrs: AttributeSet) : ViewPager(context, attrs), CoroutineScope{

    private lateinit var items: MutableList<Any>
    private lateinit var  adapter: SliderAdapter

    private var job: Job = Job()

    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main + job

    init {
        setOnTouchListener { _, _ -> true }
        addOnPageChangeListener(object : OnPageChangeListener {

            override fun onPageScrollStateChanged(state: Int) {
            }

            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {

            }

            override fun onPageSelected(position: Int) {
                adapter.startVideo(position)
            }

        })
    }

    fun setItems(items: MutableList<Any>){
        this.items = items
        adapter = SliderAdapter(context, items)
        setAdapter(adapter)
        animateSlider()
    }

    private fun animateSlider() {
        launch {
            while (true) {
                var delay: Long = 5000
                val item = items[currentItem]
                if (item !is Bitmap) {
                    try {
                        delay = Utils.getVideoLength(context, item as String)
                    }catch (e: Exception){}
                }

                delay(delay)
                if (currentItem == items.size - 1) setCurrentItem(
                    0,
                    true
                )
                else setCurrentItem(++currentItem, true)
            }
        }
    }

    override fun onDetachedFromWindow() {
        super.onDetachedFromWindow()
        job.cancel()
    }
}