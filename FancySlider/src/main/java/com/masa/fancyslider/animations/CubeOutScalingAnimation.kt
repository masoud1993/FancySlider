package com.masa.fancyslider.animations

import android.view.View
import androidx.viewpager.widget.ViewPager
import kotlin.math.abs
import kotlin.math.max

class CubeOutScalingAnimation : ViewPager.PageTransformer {
    override fun transformPage(page: View, position: Float) {
        if (position < -1){
            page.alpha = 0F
        }else if (position <= 0){
            page.alpha = 1F
            page.pivotX = page.width.toFloat()
            page.rotationY = -90* abs(position)
        }else if (position <= 1){
            page.alpha = 1F
            page.pivotX = 0F
            page.rotationY = 90* abs(position)
        }else{
            page.alpha = 0F
        }

        if (abs(position)<= 0.5){
            page.scaleY = max(0.4F,1- abs(position))
        }else if (abs(position)<= 1){
            page.scaleY = max(0.4F, abs(position))
        }
    }
}