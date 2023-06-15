package com.catboy.timetomat.util

import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.provider.Settings
import com.catboy.timetomat.models.Alarm
import com.catboy.timetomat.screens.AlarmActivity
import com.catboy.timetomat.screens.MainActivity
import com.catboy.timetomat.screens.PermissionsActivity


class Intenter(private val context: Context) {

    companion object {
        private const val PACKAGE = "package:"
        const val NAME = "name"
        const val MINUTES = "minutes"
        const val SECONDS = "seconds"
        const val TIME = "time"
        private const val REQUEST_CODE_INFO = 0
        private const val REQUEST_CODE_ACTION = 1
    }

    fun permissionsActivity(): Intent {
        return Intent(context, PermissionsActivity::class.java)
            .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
            .addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
    }

    fun mainActivity(): Intent {
        return Intent(context, MainActivity::class.java)
            .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
            .addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
    }

    val overlayPermission: Intent
        get() = Intent(
            Settings.ACTION_MANAGE_OVERLAY_PERMISSION,
            Uri.parse(PACKAGE + context.packageName)
        )
            .addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY)
            .addFlags(Intent.FLAG_ACTIVITY_EXCLUDE_FROM_RECENTS)
            .addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)

    fun applicationDetailsSettings(): Intent {
        return Intent(
            Settings.ACTION_APPLICATION_DETAILS_SETTINGS,
            Uri.parse(PACKAGE + context.packageName)
        )
            .addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY)
            .addFlags(Intent.FLAG_ACTIVITY_EXCLUDE_FROM_RECENTS)
            .addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
    }

    fun timer(bcTimer: String, name: String, minutes: Long, seconds: Long): Intent {
        val intent = Intent()
        intent.action = bcTimer
        intent.putExtra(NAME, name)
        intent.putExtra(MINUTES, minutes)
        intent.putExtra(SECONDS, seconds)
        return intent

    }

    fun stopService(): Intent {
        return Intent(context, TimerService::class.java)
    }

    fun startService(alarm: Alarm, timeInMillis: Long): Intent {
        val intent = Intent(context, TimerService::class.java)
        intent.putExtra(NAME, alarm.toString())
        intent.putExtra(TIME, timeInMillis)
        return intent
    }

    fun getAlarmInfoPendingIntent(): PendingIntent? {
        val alarmInfoIntent = Intent(context, MainActivity::class.java)
        alarmInfoIntent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            PendingIntent.getActivity(
                context, REQUEST_CODE_INFO,
                alarmInfoIntent, PendingIntent.FLAG_IMMUTABLE
            )
        } else {
            PendingIntent.getActivity(
                context, REQUEST_CODE_INFO,
                alarmInfoIntent,
                PendingIntent.FLAG_IMMUTABLE or PendingIntent.FLAG_UPDATE_CURRENT
            )
        }
    }

    fun getAlarmActionPendingIntent(): PendingIntent? {
        val intent = Intent(context, AlarmActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            PendingIntent.getActivity(
                context, REQUEST_CODE_ACTION,
                intent, PendingIntent.FLAG_IMMUTABLE
            )
        } else {
            PendingIntent.getActivity(
                context, REQUEST_CODE_ACTION,
                intent, PendingIntent.FLAG_IMMUTABLE or PendingIntent.FLAG_UPDATE_CURRENT
            )
        }
    }

    fun getAlarmActionPendingIntent(name: String): PendingIntent? {
        val intent = Intent(context, AlarmActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK
        intent.putExtra(NAME, name)
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            PendingIntent.getActivity(
                context, REQUEST_CODE_ACTION,
                intent, PendingIntent.FLAG_IMMUTABLE
            )
        } else {
            PendingIntent.getActivity(
                context, REQUEST_CODE_ACTION,
                intent, PendingIntent.FLAG_IMMUTABLE or PendingIntent.FLAG_UPDATE_CURRENT
            )
        }
    }
}