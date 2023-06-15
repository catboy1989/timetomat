package com.catboy.timetomat.usecases.permissions

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.provider.Settings
import androidx.core.content.ContextCompat


class IsPermissionsDenied(private val context: Context) {
    fun execute(): Boolean {
        return (ContextCompat.checkSelfPermission(
            context, Manifest.permission.WRITE_EXTERNAL_STORAGE
        ) == PackageManager.PERMISSION_DENIED) or !Settings.canDrawOverlays(context)
    }
}