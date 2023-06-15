package com.catboy.timetomat.usecases.music

import com.catboy.timetomat.util.LiteMediaPlayer

class StopMusic {

    fun execute() {
        val mp = LiteMediaPlayer.getInstance()
        mp.stop()
    }

}
