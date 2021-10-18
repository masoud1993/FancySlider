package com.masa.fancyslider.animations

import android.view.View
import androidx.viewpager.widget.ViewPager
import kotlin.math.abs

class GateAnimation : ViewPager.PageTransformer {

    override fun transformPage(page: View, position: Float) {

        page.translationX = -position*page.width

        when {
            position < -1 -> {
                page.alpha = 0F
            }
            position <= 0 -> {
                page.rotationY = 90F * abs(position)
                page.alpha = 1F
                page.pivotX = 0F
            }
            position <= 1 -> {
                page.rotationY = -90F * abs(position)
                page.alpha = 1F
                page.pivotX = page.width.toFloat()
            }
            else -> {
                page.alpha = 0F
            }
        }
    }
}