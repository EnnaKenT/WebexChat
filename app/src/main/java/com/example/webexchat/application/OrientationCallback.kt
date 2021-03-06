package com.example.webexchat.application

import android.annotation.SuppressLint
import android.app.Activity
import android.app.Application
import android.content.pm.ActivityInfo
import android.os.Bundle

class OrientationCallback : Application.ActivityLifecycleCallbacks {

    override fun onActivityPaused(activity: Activity) {
    }

    override fun onActivityStarted(activity: Activity) {
    }

    override fun onActivityDestroyed(activity: Activity) {
    }

    override fun onActivitySaveInstanceState(activity: Activity, outState: Bundle) {
    }

    override fun onActivityStopped(activity: Activity) {
    }

    @SuppressLint("SourceLockedOrientationActivity")
    override fun onActivityCreated(activity: Activity, savedInstanceState: Bundle?) {
        activity.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
    }

    override fun onActivityResumed(activity: Activity) {
    }

}