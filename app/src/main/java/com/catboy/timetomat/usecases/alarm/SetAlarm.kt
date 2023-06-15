package com.catboy.timetomat.usecases.alarm

import android.content.Context
import com.catboy.timetomat.repository.Repository
import com.catboy.timetomat.util.Alarmer
import java.util.*

class SetAlarm(private val context: Context) {

    val repository = Repository(context)

    fun execute() {
        val alarmTime = repository.getAlarm()
        val calendar = Calendar.getInstance()
        if (calendar.timeInMillis > alarmTime.timeInMillis) return
        calendar.timeInMillis = alarmTime.timeInMillis
        Alarmer(context).setAlarm(alarmTime.name, calendar)
    }
}