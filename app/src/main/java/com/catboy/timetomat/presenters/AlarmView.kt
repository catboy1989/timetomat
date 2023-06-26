package com.catboy.timetomat.presenters

import android.content.Context

interface AlarmView {

    fun getApplicationContext(): Context
    fun setEndRestView()
    fun setEndWorkView()
}
