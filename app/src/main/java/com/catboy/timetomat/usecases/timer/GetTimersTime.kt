package com.catboy.timetomat.usecases.timer

import android.content.Intent
import com.catboy.timetomat.models.TimersTime
import com.catboy.timetomat.models.Alarm
import com.catboy.timetomat.util.Intenter

class GetTimersTime {
    fun execute(intent: Intent): TimersTime {
        val name = intent.getStringExtra(Intenter.NAME)
        val minutes = intent.getLongExtra(Intenter.MINUTES, 0L)
        val seconds = intent.getLongExtra(Intenter.SECONDS, 0L)
        if (name == Alarm.WORK.toString()) return TimersTime(Alarm.WORK, minutes, seconds)
        if (name == Alarm.SHORT.toString()) return TimersTime(Alarm.SHORT, minutes, seconds)
        return TimersTime(Alarm.LONG, minutes, seconds)
    }

}
