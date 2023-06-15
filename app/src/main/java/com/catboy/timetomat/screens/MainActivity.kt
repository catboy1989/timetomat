package com.catboy.timetomat.screens

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.catboy.timetomat.R
import com.catboy.timetomat.models.Alarm
import com.catboy.timetomat.util.MusicContract
import com.catboy.timetomat.util.TimerService
import com.catboy.timetomat.viewmodels.MainActivityViewModel

class MainActivity : AppCompatActivity() {

    private var musicText: TextView? = null
    private var vm: MainActivityViewModel? = null
    private val activityResultLauncher = registerForActivityResult(MusicContract()) { result ->
        vm!!.onActivityResult(result)
    }
    private val receiver: BroadcastReceiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context, intent: Intent) {
            vm!!.timerIntent(intent)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        vm = ViewModelProvider(this)[MainActivityViewModel::class.java]

        val selectMusic: TextView = findViewById(R.id.select_music)
        musicText = findViewById(R.id.music_text)
        val workText: TextView = findViewById(R.id.work_text)
        val shortText: TextView = findViewById(R.id.short_text)
        val longText: TextView = findViewById(R.id.long_text)
        val workButton = findViewById<Button>(R.id.work_button)
        val shortButton = findViewById<Button>(R.id.short_button)
        val longButton = findViewById<Button>(R.id.long_button)
        val turnOff: TextView = findViewById(R.id.turn_off)

        musicText!!.text = vm!!.getMusicName()

        musicText!!.setOnClickListener { activityResultLauncher.launch(null) }
        selectMusic.setOnClickListener { activityResultLauncher.launch(null) }
        workButton.setOnClickListener { vm!!.startTimer(Alarm.WORK) }
        shortButton.setOnClickListener { vm!!.startTimer(Alarm.SHORT) }
        longButton.setOnClickListener { vm!!.startTimer(Alarm.LONG) }
        turnOff.setOnClickListener { vm!!.turnOff() }

        vm!!.musicNameRead.observe(this) { musicText!!.text = it }
        vm!!.workTimeRead.observe(this) { workText.text = it }
        vm!!.shortTimeRead.observe(this) { shortText.text = it }
        vm!!.longTimeRead.observe(this) { longText.text = it }

    }

    override fun onResume() {
        super.onResume()
        registerReceiver(receiver, IntentFilter(TimerService.BC_TIMER))
        vm!!.actionsOnResume()

    }

    override fun onPause() {
        super.onPause()
        unregisterReceiver(receiver)
    }

    override fun onStop() {
        super.onStop()
        vm!!.stopTimerService()
    }
}