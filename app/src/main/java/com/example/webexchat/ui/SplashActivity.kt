package com.example.webexchat.ui

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.webexchat.R
import com.example.webexchat.shared.SharedManager
import com.example.webexchat.ui.login.LoginActivity
import com.example.webexchat.ui.meeting.MeetingActivity

@SuppressLint("CustomSplashScreen")
class SplashActivity : AppCompatActivity(R.layout.activity_splash) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        goNextScreen()
    }

    private fun goNextScreen() {
        if (SharedManager.accessToken.isNotEmpty()) {
            MeetingActivity.startActivity(this)
        } else {
            LoginActivity.startActivity(this)
        }
    }

}

