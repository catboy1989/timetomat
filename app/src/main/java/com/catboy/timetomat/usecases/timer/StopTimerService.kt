package com.catboy.timetomat.usecases.timer

import android.content.Context
import com.catboy.timetomat.util.Intenter

class StopTimerService(private val context: Context) {
    fun execute() {
        context.stopService(Intenter(context).stopService())
    }

}
