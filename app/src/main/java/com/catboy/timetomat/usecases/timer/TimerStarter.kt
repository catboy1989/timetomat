package com.catboy.timetomat.usecases.timer

import android.content.Context
import com.catboy.timetomat.repository.Repository
import com.catboy.timetomat.util.Intenter
import java.util.*

class TimerStarter(private val context: Context) {
    private val repository = Repository(context)

    fun execute() {
        val alarmTime = repository.getAlarm()
        val calendar = Calendar.getInstance()
        if (calendar.timeInMillis > alarmTime.timeInMillis) return
        context.stopService(Intenter(context).stopService())
        context.startService(Intenter(context).startService(
            alarmTime.alarm, alarmTime.timeInMillis))
    }
}