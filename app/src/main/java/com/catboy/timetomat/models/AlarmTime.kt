package com.catboy.timetomat.models

data class AlarmTime(val alarm: Alarm, val timeInMillis: Long) {
    val name: String = alarm.toString()
}