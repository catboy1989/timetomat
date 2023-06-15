package com.catboy.timetomat.usecases.music

import android.content.Context
import android.media.MediaMetadataRetriever
import android.net.Uri

class GetMusicNameFromUri(val context: Context) {

    companion object {
        private const val DEFAULT_NAME: String = "музыка"
    }

    fun execute(uri: Uri): String {
        val retriever = MediaMetadataRetriever()
        val musicName = try {
            retriever.setDataSource(context, uri)
            retriever.extractMetadata(MediaMetadataRetriever.METADATA_KEY_TITLE)
        } catch (e: Exception) {
            DEFAULT_NAME
        }
        retriever.close()
        return musicName!!
    }

}
