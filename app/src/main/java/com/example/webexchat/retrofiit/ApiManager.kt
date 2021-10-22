package com.example.webexchat.retrofiit

import com.example.webexchat.retrofiit.response.AccessTokenResponse
import com.example.webexchat.retrofiit.response.MeetingResponse

class ApiManager : AuthApi {

    private var appRetrofit: AuthApi = NetManager.getRestApi()

    override suspend fun accessToken(
        grantType: String,
        clientId: String,
        clientSecret: String,
        authorizationCode: String,
        redirectUri: String
    ): AccessTokenResponse = appRetrofit.accessToken(authorizationCode = authorizationCode)

    override suspend fun refreshToken(
        grantType: String,
        clientId: String,
        clientSecret: String,
        refreshToken: String,
    ): AccessTokenResponse = appRetrofit.refreshToken(refreshToken = refreshToken)

    override suspend fun getMeetings(): MeetingResponse = appRetrofit.getMeetings()
}
