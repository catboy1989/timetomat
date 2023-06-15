package com.catboy.timetomat.usecases.alarm

import android.content.Context
import com.catboy.timetomat.util.Alarmer

class StopAlarm(private val context: Context) {

    fun execute() {
        Alarmer(context).cancelAlarm()
    }
}