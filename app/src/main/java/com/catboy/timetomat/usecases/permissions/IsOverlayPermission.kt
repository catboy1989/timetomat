package com.catboy.timetomat.usecases.permissions

import android.content.Context
import android.provider.Settings


class IsOverlayPermission(private val context: Context) {
    fun execute(): Boolean {
        return Settings.canDrawOverlays(context)
    }
}