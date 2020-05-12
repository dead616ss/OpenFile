package com.myapplication

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.os.Bundle
//import android.support.v4.app.ActivityCompat
import com.myapplication.camera.ActivityQR
//import com.myapplication.camera.ActivityTakePhoto

@SuppressLint("Registered")
class scanFunctionality(var activity: Activity, var layoutId: Int, var toolbarId: Int, var webviewId: Int){


    fun turnCameraOn(): Boolean {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    fun scan() {
        var intent = Intent(activity, ActivityQR::class.java)

        intent.putExtra("LAYOUT_ID", layoutId)
        intent.putExtra("TOOLBAR_ID", toolbarId)
        intent.putExtra("WEBVIEW_ID", webviewId)

        activity.startActivity(intent)
    }


    }