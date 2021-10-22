package com.example.webexchat.retrofiit

import com.example.webexchat.BuildConfig
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object NetManager {

    fun getRestApi(): AuthApi =
        Retrofit.Builder()
            .baseUrl(BuildConfig.SERVER_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(OkHttp.initClient())
            .build()
            .create(AuthApi::class.java)

}