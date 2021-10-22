package com.example.webexchat.shared

import android.content.Context
import android.content.SharedPreferences

object SharedManager {
    private lateinit var sharedPreferences: SharedPreferences

    private const val PREFERENCES_NAME = "WEBEX_CHAT"
    private const val TOKEN_ACCESS = "TOKEN_ACCESS"
    private const val TOKEN_REFRESH = "TOKEN_REFRESH"
    private const val USER_PIN = "USER_PIN"
    private const val USER_NAME = "USER_NAME"
    private const val USER_LOGIN = "USER_LOGIN"

    fun init(context: Context) {
        sharedPreferences = context.getSharedPreferences(PREFERENCES_NAME, Context.MODE_PRIVATE)
    }

    fun clearSharedPreferences() {
        sharedPreferences.edit().clear().apply()
    }

    var accessToken: String
        get() = sharedPreferences.getString(TOKEN_ACCESS, "")!!
        set(value) = sharedPreferences.putString(TOKEN_ACCESS, value)

    var refreshToken: String
        get() = sharedPreferences.getString(TOKEN_REFRESH, "")!!
        set(value) = sharedPreferences.putString(TOKEN_REFRESH, value)

    var userPin: String
        get() = sharedPreferences.getString(USER_PIN, "")!!
        set(value) = sharedPreferences.putString(USER_PIN, value)

    var userName: String
        get() = sharedPreferences.getString(USER_NAME, "")!!
        set(value) = sharedPreferences.putString(USER_NAME, value)

    var userLogin: String
        get() = sharedPreferences.getString(USER_LOGIN, "")!!
        set(value) = sharedPreferences.putString(USER_LOGIN, value)

}

private fun SharedPreferences.putString(key: String, value: String) =
    this.edit().putString(key, value).apply()

private fun SharedPreferences.putBoolean(key: String, value: Boolean) =
    this.edit().putBoolean(key, value).apply()