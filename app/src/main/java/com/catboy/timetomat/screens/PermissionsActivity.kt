package com.catboy.timetomat.screens

import android.Manifest
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import com.catboy.timetomat.R
import com.catboy.timetomat.presenters.IPermissionView
import com.catboy.timetomat.presenters.PermissionsActivityPresenter


class PermissionsActivity : AppCompatActivity(), IPermissionView {

    companion object {
        private const val REQUEST_CODE = 100
    }

    private var presenter: PermissionsActivityPresenter? = null
    private var overlayButton: Button? = null
    private var fileButton: Button? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_permissions)

        presenter = PermissionsActivityPresenter(this)

        overlayButton = findViewById(R.id.permission_overlay_button)
        fileButton = findViewById(R.id.permission_file_button)

        overlayButton!!.setOnClickListener { presenter!!.getOverlayPermission() }
        fileButton!!.setOnClickListener { filePermission }
    }

    private val filePermission: Unit
        get() {
            ActivityCompat.requestPermissions(
                this,
                arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE),
                REQUEST_CODE
            )
        }

    override fun onResume() {
        super.onResume()
        presenter!!.checkPermissionsOnResume()
    }

    override fun onRequestPermissionsResult(
        requestCode: Int, permissions: Array<String>,
        grantResults: IntArray
    ) {
        presenter!!.onRequestPermissionsResult(requestCode, grantResults, requestCode)
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
    }

    override fun setFileButtonVisibility(visibility: Int) {
        fileButton!!.visibility = visibility
    }

    override fun setOverlayButtonVisibility(visibility: Int) {
        overlayButton!!.visibility = visibility
    }
}