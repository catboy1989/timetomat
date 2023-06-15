package com.catboy.timetomat.presenters

import com.catboy.timetomat.usecases.IsVisible
import com.catboy.timetomat.usecases.permissions.*
import com.catboy.timetomat.usecases.startactivity.StartMainActivity


class PermissionsActivityPresenter(private val view: IPermissionView) {
    private val isPermissionsDenied = IsPermissionsDenied(view.getApplicationContext())
    private val startMainActivity = StartMainActivity(view.getApplicationContext())
    private val isOverlayPermission = IsOverlayPermission(view.getApplicationContext())
    private val isFilePermission = IsFilePermission(view.getApplicationContext())
    private val isVisible = IsVisible()
    private val getOverlayPermission = GetOverlayPermission(view.getApplicationContext())
    private val onRequestPermissionsResult =
        OnRequestPermissionsResult(view.getApplicationContext())

    fun checkPermissionsOnResume() {
        if (!isPermissionsDenied.execute()) {
            startMainActivity.execute(!isPermissionsDenied.execute())
        } else {
            view.setOverlayButtonVisibility(isVisible.execute(!isOverlayPermission.execute()))
            view.setFileButtonVisibility(isVisible.execute(!isFilePermission.execute()))
        }
    }

    fun getOverlayPermission() {
        getOverlayPermission.execute()
    }

    fun onRequestPermissionsResult(requestCode: Int, grantResults: IntArray?, REQUEST_CODE: Int) {
        onRequestPermissionsResult.execute(requestCode, grantResults!!, REQUEST_CODE)
    }
}