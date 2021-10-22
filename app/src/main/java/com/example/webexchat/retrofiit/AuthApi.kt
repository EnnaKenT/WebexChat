package com.example.webexchat.retrofiit

import com.example.webexchat.BuildConfig
import com.example.webexchat.retrofiit.response.AccessTokenResponse
import com.example.webexchat.retrofiit.response.MeetingResponse
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST


interface AuthApi {

    @FormUrlEncoded
    @POST("access_token")
    suspend fun accessToken(
        @Field("grant_type") grantType: String = "authorization_code",
        @Field("client_id") clientId: String = BuildConfig.CLIENT_ID,
        @Field("client_secret") clientSecret: String = BuildConfig.CLIENT_SECRET,
        @Field("code") authorizationCode: String,
        @Field("redirect_uri") redirectUri: String = BuildConfig.REDIRECT_URI,
    ): AccessTokenResponse

    @FormUrlEncoded
    @POST("access_token")
    suspend fun refreshToken(
        @Field("grant_type") grantType: String = "refresh_token",
        @Field("client_id") clientId: String = BuildConfig.CLIENT_ID,
        @Field("client_secret") clientSecret: String = BuildConfig.CLIENT_SECRET,
        @Field("refresh_token") refreshToken: String,
    ): AccessTokenResponse

    @GET("meetings")
    suspend fun getMeetings(): MeetingResponse

}