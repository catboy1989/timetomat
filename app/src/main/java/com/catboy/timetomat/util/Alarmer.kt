package com.catboy.timetomat.util

import android.app.AlarmManager
import android.app.AlarmManager.AlarmClockInfo
import android.content.Context
import java.util.*

class Alarmer(context: Context) {

    private val intenter = Intenter(context)
    private val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager

    fun setAlarm(name: String, calendar: Calendar) {
        val alarmClockInfo = AlarmClockInfo(
            calendar.timeInMillis, intenter.getAlarmInfoPendingIntent()
        )
        alarmManager.setAlarmClock(alarmClockInfo, intenter.getAlarmActionPendingIntent(name))
    }

    fun cancelAlarm() {
        alarmManager.cancel(intenter.getAlarmActionPendingIntent())
    }
}