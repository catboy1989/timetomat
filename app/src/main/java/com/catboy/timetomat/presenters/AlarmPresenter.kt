package com.catboy.timetomat.presenters

import android.content.Intent
import com.catboy.timetomat.models.Alarm
import com.catboy.timetomat.repository.Repository
import com.catboy.timetomat.usecases.alarm.SetAlarm
import com.catboy.timetomat.usecases.timer.SaveTimer
import com.catboy.timetomat.usecases.music.PlayMusic
import com.catboy.timetomat.usecases.music.StopMusic
import com.catboy.timetomat.usecases.startactivity.StartMainActivity
import com.catboy.timetomat.util.Intenter

class AlarmPresenter(private val view: AlarmView) {

    val context = view.getApplicationContext()

    fun setView(intent: Intent) {
        val name = intent.getStringExtra(Intenter.NAME)
        if (name == Alarm.WORK.toString()) {
            view.setEndWorkView()
        }else {
            view.setEndRestView()
        }
    }

    fun shortBreakAction() {
        SaveTimer(context).execute(Alarm.SHORT)
        SetAlarm(context).execute()
        StartMainActivity(context).execute()
    }

    fun longBreakAction() {
        SaveTimer(context).execute(Alarm.LONG)
        SetAlarm(context).execute()
        StartMainActivity(context).execute()
    }

    fun startWorkAction() {
        SaveTimer(context).execute(Alarm.WORK)
        SetAlarm(context).execute()
        StartMainActivity(context).execute()
    }

    fun turnOffAction() {
        StartMainActivity(context).execute()
    }

    fun startMediaPlayer() {
        PlayMusic(context, Repository(context)).execute()
    }

    fun stopMediaPlayer() {
        StopMusic().execute()
    }


}
