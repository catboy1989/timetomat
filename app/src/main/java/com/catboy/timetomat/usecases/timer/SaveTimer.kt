package com.catboy.timetomat.usecases.timer

import android.content.Context
import com.catboy.timetomat.models.Alarm
import com.catboy.timetomat.models.AlarmTime
import com.catboy.timetomat.repository.Repository
import java.util.Calendar

class SaveTimer(context: Context) {

    private val repository = Repository(context)

    fun execute(alarm: Alarm) {
        val currentTime = Calendar.getInstance().timeInMillis
        val timeInMillis = when (alarm) {
            Alarm.WORK -> currentTime + 25 * 60 * 1000L
            Alarm.SHORT -> currentTime + 5 * 60 * 1000L
            Alarm.LONG -> currentTime + 15 * 60 * 1000L
        }
        repository.setAlarm(AlarmTime(alarm, timeInMillis))
    }

}
