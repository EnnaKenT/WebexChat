package com.example.webexchat.retrofiit

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {

    private val retrofit = Retrofit.Builder()
        .baseUrl("https://webexapis.com/v1/")
        .addConverterFactory(GsonConverterFactory.create())
        .client(
            OkHttpClient.Builder().addInterceptor { chain ->
                val request =
                    chain.request().newBuilder()
                        //.addHeader("Authorization", "Bearer ${localRepo.token}")
                        .addHeader("Accept", "application/json")
                        .build()
                chain.proceed(request)
            }.build()
        )
        .build()

    var auth = retrofit.create(AuthApi::class.java)
}