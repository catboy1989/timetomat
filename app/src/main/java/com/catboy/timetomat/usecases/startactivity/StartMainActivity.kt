package com.catboy.timetomat.usecases.startactivity

import android.content.Context
import com.catboy.timetomat.util.Intenter


class StartMainActivity(private val context: Context) {

    fun execute(needGo: Boolean) {
        if (needGo) context.startActivity(Intenter(context).mainActivity())
    }

    fun execute() {
        context.startActivity(Intenter(context).mainActivity())
    }
}