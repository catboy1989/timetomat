package com.catboy.timetomat.usecases.permissions

import android.content.Context
import android.content.pm.PackageManager
import com.catboy.timetomat.util.Intenter


class OnRequestPermissionsResult(private val context: Context) {
    fun execute(requestCode: Int, grantResults: IntArray, REQUEST_CODE: Int) {
        if (requestCode == REQUEST_CODE && grantResults.size == 1) {
            if (grantResults[0] == PackageManager.PERMISSION_DENIED) {
                context.startActivity(Intenter(context).applicationDetailsSettings())
            }
        }
    }
}