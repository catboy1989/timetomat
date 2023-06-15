package com.catboy.timetomat.util

import android.content.Context
import android.media.MediaPlayer
import android.net.Uri
import java.io.IOException

class LiteMediaPlayer private constructor() {

    private var mp: MediaPlayer = MediaPlayer()

    companion object {
        private var instance: LiteMediaPlayer? = null

        fun  getInstance(): LiteMediaPlayer {
            if (instance == null)  // NOT thread safe!
                instance = LiteMediaPlayer()

            return instance as LiteMediaPlayer
        }
    }

    fun play(context: Context, uri: Uri) {
        if (mp.isPlaying) mp.stop()
        mp = MediaPlayer()
        try {
            mp.setDataSource(context, uri)
        } catch (e: IOException) {
            throw RuntimeException(e)
        }
        mp.prepareAsync()
        mp.setOnPreparedListener { obj: MediaPlayer -> obj.start() }
    }

    fun play(context: Context, path: String) { play(context, Uri.parse(path)) }

    fun stop() {
        if (mp.isPlaying) mp.stop()
    }
}