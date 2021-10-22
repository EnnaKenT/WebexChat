package com.example.webexchat.retrofiit

import com.example.webexchat.BuildConfig
import com.example.webexchat.shared.SharedManager
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import java.util.concurrent.TimeUnit

object OkHttp {

    private val TIMEOUT = TimeUnit.SECONDS.toMillis(30)

    fun initClient(): OkHttpClient {

        return OkHttpClient.Builder()
            .authenticator(BearerTokenAuthenticator())
            .addNetworkInterceptor(provideHttpLoggingInterceptor())
            .readTimeout(TIMEOUT, TimeUnit.MILLISECONDS)
            .connectTimeout(TIMEOUT, TimeUnit.MILLISECONDS)
            .writeTimeout(TIMEOUT, TimeUnit.MILLISECONDS)
            .addInterceptor(::bearerTokenInterceptor)
            .build()
    }

    private fun provideHttpLoggingInterceptor(): HttpLoggingInterceptor =
        HttpLoggingInterceptor().apply {
            level =
                if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY
                else HttpLoggingInterceptor.Level.NONE
        }

    private fun bearerTokenInterceptor(chain: Interceptor.Chain): Response {
        val originalBuilder = chain.request().newBuilder()
        originalBuilder.addHeader("Content-Type", "application/json")
        if (SharedManager.accessToken.isNotEmpty()) {
            originalBuilder.addHeader("Authorization", "Bearer ${SharedManager.accessToken}")
        }
        val request = originalBuilder.build()
        return chain.proceed(request)
    }

}