package com.catboy.timetomat.usecases.music

import android.content.Context
import android.media.RingtoneManager
import android.net.Uri
import com.catboy.timetomat.repository.IRepository
import com.catboy.timetomat.util.LiteMediaPlayer


class PlayMusic(private val context: Context, private val repository: IRepository) {

    private val defaultPath = "android.resource://${context.packageName}/raw/alarm"

    fun execute() {
        val path = repository.getMusic()
        var uri: Uri? = if (path.isEmpty()) null else Uri.parse(path)
        if (uri == null) {
            uri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_ALARM)
            if(uri == null){
                uri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_RINGTONE)
                if(uri == null) {
                    uri = Uri.parse(defaultPath)
                }

            }
        }
        val mp = LiteMediaPlayer.getInstance()
        uri?.let { mp.play(context, it) }
    }
}
