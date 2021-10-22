package com.example.webexchat.retrofiit

import android.util.Log
import com.example.webexchat.application.Application
import com.example.webexchat.shared.SharedManager
import kotlinx.coroutines.runBlocking
import okhttp3.Authenticator
import okhttp3.Request
import okhttp3.Response
import okhttp3.Route

class BearerTokenAuthenticator : Authenticator {
    private val TAG = BearerTokenAuthenticator::class.java.simpleName

    override fun authenticate(route: Route?, response: Response): Request =
        runBlocking {
            Log.d(TAG, "Need to update old token: ${SharedManager.accessToken}")
            val refreshCall = Application.getNetwork.refreshToken(refreshToken = SharedManager.refreshToken)
            SharedManager.accessToken = refreshCall.accessToken
            SharedManager.refreshToken = refreshCall.refreshToken
            Log.d(TAG, "Old token updated to: ${refreshCall.accessToken}")

            return@runBlocking response.request.newBuilder()
                .header("Authorization", "Bearer ${refreshCall.accessToken}")
                .build()
        }
}