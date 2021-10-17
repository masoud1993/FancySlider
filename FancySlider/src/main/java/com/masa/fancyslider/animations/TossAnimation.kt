package com.masa.fancyslider.animations

import android.view.View
import androidx.viewpager.widget.ViewPager
import kotlin.math.abs
import kotlin.math.max

class TossAnimation  : ViewPager.PageTransformer {

    override fun transformPage(page: View, position: Float) {

        page.translationX = -position * page.width
        page.cameraDistance = 20000F

        if (position < 0.5 && position > -0.5)
            page.visibility = View.VISIBLE
        else
            page.visibility = View.INVISIBLE
        if (position < -1)
            page.alpha = 0F
        else if (position <= 0){
            page.alpha = 1F
            page.scaleX = max(0.4F,(1 - abs(position)))
            page.scaleY = max(0.4F,(1 - abs(position)))
            page.rotationY = 1080 * (1 - abs(position) + 1)
            page.translationY = -1000 * abs(position)
        }else if (position <= 1){
            page.alpha = 1F
            page.scaleX = max(0.4F,(1 - abs(position)))
            page.scaleY = max(0.4F,(1 - abs(position)))
            page.rotationX = -1080 * (1 - abs(position) + 1)
            page.translationY = -1000 * abs(position)
        }else
            page.alpha = 0F
    }
}