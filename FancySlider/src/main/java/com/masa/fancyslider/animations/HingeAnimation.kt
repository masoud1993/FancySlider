package com.masa.fancyslider.animations

import android.view.View
import androidx.viewpager.widget.ViewPager
import kotlin.math.abs

class HingeAnimation  : ViewPager.PageTransformer {

    override fun transformPage(page: View, position: Float) {

        page.translationX = -position*page.width
        page.pivotX = 0F
        page.pivotY = 0F

        when {
            position < -1 -> {
                page.alpha = 0F
            }
            position <= 0 -> {
                page.rotation = 90F * abs(position)
                page.alpha = 1F - abs(position)
            }
            position <= 1 -> {
                page.rotation = 0F
                page.alpha = 1F
            }
            else -> {
                page.alpha = 0F
            }
        }
    }
}