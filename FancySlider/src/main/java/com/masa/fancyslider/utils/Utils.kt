package com.masa.fancyslider.utils

import android.content.Context
import android.media.MediaPlayer
import android.net.Uri

object Utils {

    fun getVideoLength(context: Context, path: String): Long {
        val length: Long
        val mp = MediaPlayer.create(context, Uri.parse(path))
        length = mp.duration.toLong()
        mp.release()
        return length
    }
}