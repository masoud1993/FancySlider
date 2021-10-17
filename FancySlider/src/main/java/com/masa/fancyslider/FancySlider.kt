package com.masa.fancyslider

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Bitmap
import android.util.AttributeSet
import androidx.viewpager.widget.ViewPager
import com.masa.fancyslider.adapter.SliderAdapter
import com.masa.fancyslider.animations.CubeInScalingAnimation
import com.masa.fancyslider.animations.CubeOutScalingAnimation
import com.masa.fancyslider.utils.Utils
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext
import android.view.animation.DecelerateInterpolator

import android.widget.Scroller
import com.masa.fancyslider.animations.HingeAnimation
import com.masa.fancyslider.animations.TossAnimation


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
        setPageTransformer(true, TossAnimation())
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

    fun setScrollDuration(millis: Int) {
        try {
            val viewpager: Class<*> = ViewPager::class.java
            val scroller = viewpager.getDeclaredField("mScroller")
            scroller.isAccessible = true
            scroller.set(this, CustomScroller(context, millis))
        } catch (e: java.lang.Exception) {
            e.printStackTrace()
        }
    }

    class CustomScroller(context: Context?, durationScroll: Int) :
        Scroller(context, DecelerateInterpolator()) {
        private var durationScrollMillis = 1
        override fun startScroll(startX: Int, startY: Int, dx: Int, dy: Int, duration: Int) {
            super.startScroll(startX, startY, dx, dy, durationScrollMillis)
        }

        init {
            durationScrollMillis = durationScroll
        }
    }

    override fun onDetachedFromWindow() {
        super.onDetachedFromWindow()
        job.cancel()
    }
}