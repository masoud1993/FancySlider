package com.masa.fancyslider.animations

import android.view.View
import androidx.viewpager.widget.ViewPager
import kotlin.math.abs

class FidgetSpinnerAnimation : ViewPager.PageTransformer {
    override fun transformPage(page: View, position: Float) {

        if (abs(position) < 0.5){
            page.visibility = View.VISIBLE
            page.scaleX = 1 - abs(position)
            page.scaleY = 1 - abs(position)
            page.alpha = 0F
        }else if (abs(position) > 0.5){
            page.visibility = View.GONE
        }

        if (position < -1)
            page.alpha = 0F
        else if (position <= 0) {
            page.alpha = 1F

            page.rotation =
                36000 * (abs(position) * abs(position) * abs(position) * abs(position) * abs(position
                ) * abs(position) * abs(position))
        }else if(position <= 1){
            page.alpha = 1F
            page.rotation = -36000 * (abs(position) * abs(position) * abs(position) * abs(position) * abs(position
            ) * abs(position) * abs(position))
        }else
            page.alpha = 0F
    }
}