package com.catboy.timetomat.repository

import com.catboy.timetomat.models.AlarmTime

interface IRepository {
    fun getMusic(): String
    fun setMusic(musicUri: String)
    fun setAlarm(alarmTime: AlarmTime)
    fun getAlarm(): AlarmTime
}