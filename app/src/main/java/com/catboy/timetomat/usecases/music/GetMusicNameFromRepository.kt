package com.catboy.timetomat.usecases.music

import android.content.Context
import android.net.Uri
import com.catboy.timetomat.repository.Repository

class GetMusicNameFromRepository(val context: Context) {

    private val repository = Repository(context)
    fun execute(): String {
        val musicUri = Uri.parse(repository.getMusic())
        return GetMusicNameFromUri(context).execute(musicUri)
    }

}
