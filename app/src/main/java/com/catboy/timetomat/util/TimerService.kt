package com.catboy.timetomat.util

import android.app.Service
import android.content.Intent
import android.os.CountDownTimer
import android.os.IBinder
import java.util.*
import kotlin.concurrent.thread

class TimerService : Service() {

    companion object {
        const val BC_TIMER = "com.catboy.timetomat.util.TimerService"
    }

    private var timer: CountDownTimer? = null



    override fun onStartCommand(intent: Intent, flags: Int, startId: Int): Int {
        val timeInMillis = intent.getLongExtra(Intenter.TIME, 0L)
        val alarm: String = intent.getStringExtra(Intenter.NAME)!!
        val timerTime = timeInMillis - Calendar.getInstance().timeInMillis
        val intenter = Intenter(this)
        timer = object: CountDownTimer(timerTime, 1000L) {
            override fun onTick(millisUntilFinished: Long) {
                val minutes = millisUntilFinished / 1000 / 60
                val seconds = millisUntilFinished / 1000 % 60
                sendBroadcast(intenter.timer(BC_TIMER, alarm, minutes, seconds))
            }

            override fun onFinish() {  }

        }
        thread{
            timer!!.start()
        }
        return START_NOT_STICKY
    }

    override fun onDestroy() {
        timer?.cancel()
        super.onDestroy()
    }

    override fun onBind(intent: Intent?): IBinder? {
        TODO("Not yet implemented")
    }
}