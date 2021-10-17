package com.masa.fancyslider

import android.graphics.BitmapFactory
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val slider  = findViewById<FancySlider>(R.id.slider)

        slider.setScrollDuration(1000)
        slider.setItems(mutableListOf(
            BitmapFactory.decodeResource(resources, R.drawable.slider1),
            BitmapFactory.decodeResource(resources, R.drawable.slider2),
            BitmapFactory.decodeResource(resources, R.drawable.slider4)
        ))
    }
}