package com.example.webexchat

import android.content.Context
import android.widget.Toast
import androidx.annotation.StringRes

fun Context.showToast(@StringRes textRes: Int, duration: Int = Toast.LENGTH_SHORT) =
    showToast(getString(textRes), duration)

fun Context.showToast(text: String, duration: Int = Toast.LENGTH_SHORT) =
    Toast.makeText(this, text, duration).show()