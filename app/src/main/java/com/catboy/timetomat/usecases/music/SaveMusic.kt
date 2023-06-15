package com.catboy.timetomat.usecases.music

import android.content.Context
import android.content.Intent
import android.net.Uri
import com.catboy.timetomat.repository.Repository

class SaveMusic(val context: Context) {

    private val repository = Repository(context)

    fun execute(audioUri: Uri) {
        val takeFlags = Intent.FLAG_GRANT_READ_URI_PERMISSION or
                 Intent.FLAG_GRANT_WRITE_URI_PERMISSION
        context.contentResolver.takePersistableUriPermission(audioUri, takeFlags)
        repository.setMusic(audioUri.toString())
    }

}
