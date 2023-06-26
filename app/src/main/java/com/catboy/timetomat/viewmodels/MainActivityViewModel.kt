package com.catboy.timetomat.viewmodels

import android.app.Application
import android.content.Intent
import android.net.Uri
import androidx.activity.result.ActivityResultCallback
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.catboy.timetomat.models.Alarm
import com.catboy.timetomat.models.TimersTime
import com.catboy.timetomat.usecases.alarm.SetAlarm
import com.catboy.timetomat.usecases.alarm.StopAlarm
import com.catboy.timetomat.usecases.music.GetMusicNameFromRepository
import com.catboy.timetomat.usecases.music.GetMusicNameFromUri
import com.catboy.timetomat.usecases.music.SaveMusic
import com.catboy.timetomat.usecases.permissions.IsPermissionsDenied
import com.catboy.timetomat.usecases.startactivity.StartPermissionsActivity
import com.catboy.timetomat.usecases.timer.GetTimersTime
import com.catboy.timetomat.usecases.timer.SaveTimer
import com.catboy.timetomat.usecases.timer.StopTimerService
import com.catboy.timetomat.usecases.timer.TimerStarter

class MainActivityViewModel(application: Application?) :
    AndroidViewModel(application!!), ActivityResultCallback<Uri?> {

    companion object {
        private const val DEFAULT_WORK_TIME = "25 : 00"
        private const val DEFAULT_SHORT_TIME = "5 : 00"
        private const val DEFAULT_LONG_TIME = "15 : 00"
    }

    private val musicName = MutableLiveData<String>()
    val musicNameRead: LiveData<String> = musicName
    private val workTime = MutableLiveData<String>()
    val workTimeRead: LiveData<String> = workTime
    private val shortTime = MutableLiveData<String>()
    val shortTimeRead: LiveData<String> = shortTime
    private val longTime = MutableLiveData<String>()
    val longTimeRead: LiveData<String> = longTime

    private val checkIsPermissionsDenied = IsPermissionsDenied(getApplication())
    private val startPermissionsActivity = StartPermissionsActivity(getApplication())

    private fun checkIsPermissionsDenied() {
        startPermissionsActivity.execute(checkIsPermissionsDenied.execute())
    }

    fun actionsOnResume() {
        checkIsPermissionsDenied()
        TimerStarter(getApplication()).execute()
    }

    override fun onActivityResult(result: Uri?) {
        if (result == null) return
        SaveMusic(getApplication()).execute(result)
        musicName.value = GetMusicNameFromUri(getApplication()).execute(result)
    }

    fun getMusicName(): String {
        return GetMusicNameFromRepository(getApplication()).execute()
    }

    fun timerIntent(intent: Intent) {
        val timersTime: TimersTime = GetTimersTime().execute(intent)
        val timerText = "${timersTime.minutes} : ${timersTime.seconds}"
        if (timersTime.alarm == Alarm.WORK) {
            workTime.value = timerText
            if (!shortTime.value.equals(DEFAULT_SHORT_TIME)) shortTime.value = DEFAULT_SHORT_TIME
            if (!longTime.value.equals(DEFAULT_LONG_TIME)) longTime.value = DEFAULT_LONG_TIME
        }
        if (timersTime.alarm == Alarm.SHORT) {
            shortTime.value = timerText
            if (!workTime.value.equals(DEFAULT_WORK_TIME)) workTime.value = DEFAULT_WORK_TIME
            if (!longTime.value.equals(DEFAULT_LONG_TIME)) longTime.value = DEFAULT_LONG_TIME
        }
        if (timersTime.alarm == Alarm.LONG) {
            longTime.value = timerText
            if (!workTime.value.equals(DEFAULT_WORK_TIME)) workTime.value = DEFAULT_WORK_TIME
            if (!shortTime.value.equals(DEFAULT_SHORT_TIME)) shortTime.value = DEFAULT_SHORT_TIME
        }
    }

    fun startTimer(alarm: Alarm) {
        setDefaultData()
        SaveTimer(getApplication()).execute(alarm)
        SetAlarm(getApplication()).execute()
        TimerStarter(getApplication()).execute()
    }

    private fun setDefaultData() {
        workTime.value = DEFAULT_WORK_TIME
        shortTime.value = DEFAULT_SHORT_TIME
        longTime.value = DEFAULT_LONG_TIME
    }

    fun turnOff() {
        StopTimerService(getApplication()).execute()
        StopAlarm(getApplication()).execute()
        setDefaultData()
    }

    fun stopTimerService() {
        StopTimerService(getApplication()).execute()
    }
}