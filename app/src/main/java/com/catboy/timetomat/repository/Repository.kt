package com.catboy.timetomat.repository

import android.content.Context
import com.catboy.timetomat.models.Alarm
import com.catboy.timetomat.models.AlarmTime

class Repository(val context: Context) : IRepository {

    companion object {
        private const val BASE_NAME = "pomidor_db"
        private const val DEFAULT_STRING: String = ""
        private const val DEFAULT_LONG = 0L
        private const val MUSIC = "music"
        private const val LONG = "long"
        private const val SHORT = "short"
        private const val WORK = "work"
        private const val NAME = "name"
        private const val TIME = "time"

    }

    private val sharedPreference = SharedPreferencesStorage(context, BASE_NAME)

    override fun getMusic(): String {
        return sharedPreference.getString(MUSIC, DEFAULT_STRING)!!
    }

    override fun setMusic(musicUri: String) {
        sharedPreference.save(MUSIC, musicUri)
    }

    private fun stopAlarm() { sharedPreference.save(TIME, DEFAULT_LONG) }

    override fun setAlarm(alarmTime: AlarmTime) {
        stopAlarm()
        sharedPreference.save(NAME, alarmTime.name)
        sharedPreference.save(TIME, alarmTime.timeInMillis)
    }

    override fun getAlarm(): AlarmTime {
        val name = sharedPreference.getString(NAME, DEFAULT_STRING)
        val time = sharedPreference.getLong(TIME, DEFAULT_LONG)
        return when(name) {
            WORK -> AlarmTime(Alarm.WORK, time)
            SHORT -> AlarmTime(Alarm.SHORT, time)
            LONG -> AlarmTime(Alarm.LONG, time)
            else -> AlarmTime(Alarm.WORK, DEFAULT_LONG)
        }

    }
}