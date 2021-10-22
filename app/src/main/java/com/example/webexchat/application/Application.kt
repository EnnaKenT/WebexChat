package com.example.webexchat.application

import android.app.Application
import com.example.webexchat.retrofiit.ApiManager
import com.example.webexchat.retrofiit.AuthApi
import com.example.webexchat.shared.SharedManager

class Application : Application() {

    companion object {
        lateinit var getNetwork: AuthApi
    }

    override fun onCreate() {
        super.onCreate()

        getNetwork = ApiManager()
        SharedManager.init(this)
        registerActivityLifecycleCallbacks(OrientationCallback())
    }
}