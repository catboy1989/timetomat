package com.catboy.timetomat.presenters

import android.content.Context


interface IPermissionView {
    fun setFileButtonVisibility(visibility: Int)
    fun setOverlayButtonVisibility(visibility: Int)
    fun getApplicationContext(): Context
}