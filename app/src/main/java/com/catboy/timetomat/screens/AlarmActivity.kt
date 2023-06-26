package com.catboy.timetomat.screens

import android.app.KeyguardManager
import android.content.Context
import android.os.Build
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.catboy.timetomat.R
import com.catboy.timetomat.presenters.AlarmPresenter
import com.catboy.timetomat.presenters.AlarmView


class AlarmActivity : AppCompatActivity(), AlarmView {

    companion object {
        private const val DEPRECATION = "DEPRECATION"
    }

    private var workEndText: TextView? = null
    private var restEndText: TextView? = null
    private var shortBreakButton: Button? = null
    private var longBreakButton: Button? = null
    private var startWorkButton: Button? = null
    private var turnOffAlarmButton: Button? = null
    private var presenter: AlarmPresenter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_alarm)

        presenter = AlarmPresenter(this)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O_MR1) {
            setShowWhenLocked(true)
            setTurnScreenOn(true)
            val keyguardManager = getSystemService(Context.KEYGUARD_SERVICE) as KeyguardManager
            keyguardManager.requestDismissKeyguard(this, null)
        } else {
            @Suppress(DEPRECATION)
            this.window.addFlags(WindowManager.LayoutParams.FLAG_DISMISS_KEYGUARD or
                    WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED or
                    WindowManager.LayoutParams.FLAG_TURN_SCREEN_ON)
        }

        workEndText = findViewById(R.id.work_end_text)
        restEndText = findViewById(R.id.rest_end_text)
        shortBreakButton = findViewById(R.id.short_break_button)
        longBreakButton = findViewById(R.id.long_break_button)
        startWorkButton = findViewById(R.id.start_work_button)
        turnOffAlarmButton = findViewById(R.id.turn_off_alarm_button)

        shortBreakButton!!.setOnClickListener { presenter!!.shortBreakAction() }
        longBreakButton!!.setOnClickListener { presenter!!.longBreakAction() }
        startWorkButton!!.setOnClickListener { presenter!!.startWorkAction() }
        turnOffAlarmButton!!.setOnClickListener { presenter!!.turnOffAction() }

        presenter!!.setView(intent)
        presenter!!.startMediaPlayer()
    }

    override fun setEndRestView() {
        workEndText!!.visibility = View.GONE
        restEndText!!.visibility = View.VISIBLE
        shortBreakButton!!.visibility = View.GONE
        longBreakButton!!.visibility = View.GONE
        startWorkButton!!.visibility = View.VISIBLE
    }

    override fun setEndWorkView() {
        workEndText!!.visibility = View.VISIBLE
        restEndText!!.visibility = View.GONE
        shortBreakButton!!.visibility = View.VISIBLE
        longBreakButton!!.visibility = View.VISIBLE
        startWorkButton!!.visibility = View.GONE
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter!!.stopMediaPlayer()
    }
}