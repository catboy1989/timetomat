package com.catboy.timetomat.usecases.permissions

import android.content.Context
import com.catboy.timetomat.util.Intenter


class GetOverlayPermission(private val context: Context) {
    fun execute() {
        context.startActivity(Intenter(context).overlayPermission)
    }
}