package com.example.webexchat.ui.login

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.webkit.WebView
import androidx.appcompat.app.AppCompatActivity
import com.example.webexchat.BuildConfig
import com.example.webexchat.R
import com.example.webexchat.application.Application
import com.example.webexchat.retrofiit.response.AccessTokenResponse
import com.example.webexchat.shared.SharedManager
import com.example.webexchat.showToast
import com.example.webexchat.ui.meeting.MeetingActivity
import kotlinx.coroutines.*

class LoginActivity : AppCompatActivity(R.layout.activity_login) {
    private lateinit var webView: WebView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initViews()
    }

    @SuppressLint("SetJavaScriptEnabled")
    private fun initViews() {
        webView = findViewById<View>(R.id.webView) as WebView
        with(webView) {
            webViewClient = CustomWebViewClient(::authorizationCodeReceived)
            loadUrl(
                "https://webexapis.com/v1/authorize?client_id=${BuildConfig.CLIENT_ID}&response_type=code&redirect_uri=https%3A%2F%2Fwebexdemoapp.com&scope=spark-admin%3Abroadworks_subscribers_write%20meeting%3Aadmin_preferences_write%20spark%3Aall%20meeting%3Aadmin_preferences_read%20analytics%3Aread_all%20meeting%3Aadmin_participants_read%20spark-admin%3Apeople_write%20spark-admin%3Aworkspace_metrics_read%20spark-admin%3Aplaces_read%20spark-compliance%3Ateam_memberships_write%20spark-compliance%3Amessages_read%20spark-admin%3Adevices_write%20spark-admin%3Aworkspaces_write%20spark-compliance%3Ameetings_write%20meeting%3Aadmin_schedule_write%20identity%3Aplaceonetimepassword_create%20spark-admin%3Aorganizations_write%20spark-admin%3Aworkspace_locations_read%20spark-compliance%3Awebhooks_read%20spark-admin%3Acall_qualities_read%20spark-compliance%3Amessages_write%20spark%3Akms%20meeting%3Aparticipants_write%20meeting%3Aadmin_transcripts_read%20spark-admin%3Apeople_read%20spark-compliance%3Amemberships_read%20spark-admin%3Aresource_groups_read%20meeting%3Arecordings_read%20meeting%3Aparticipants_read%20meeting%3Apreferences_write%20meeting%3Aadmin_recordings_read%20spark-admin%3Aorganizations_read%20spark-compliance%3Awebhooks_write%20meeting%3Atranscripts_read%20meeting%3Aschedules_write%20spark-compliance%3Ateam_memberships_read%20spark-admin%3Adevices_read%20meeting%3Acontrols_read%20spark-admin%3Ahybrid_clusters_read%20spark-admin%3Aworkspace_locations_write%20spark-admin%3Abroadworks_enterprises_write%20meeting%3Aadmin_schedule_read%20meeting%3Aschedules_read%20spark-compliance%3Amemberships_write%20spark-admin%3Abroadworks_enterprises_read%20spark-admin%3Aroles_read%20meeting%3Arecordings_write%20meeting%3Apreferences_read%20spark-admin%3Aworkspaces_read%20spark-admin%3Aresource_group_memberships_read%20spark-compliance%3Aevents_read%20spark-admin%3Aresource_group_memberships_write%20spark-compliance%3Arooms_read%20spark-admin%3Abroadworks_subscribers_read%20meeting%3Acontrols_write%20meeting%3Aadmin_recordings_write%20spark-admin%3Ahybrid_connectors_read%20audit%3Aevents_read%20spark-compliance%3Ateams_read%20spark-admin%3Aplaces_write%20spark-admin%3Alicenses_read%20spark-compliance%3Arooms_write&state=set_state_here"
            )
            settings.javaScriptEnabled = true
        }
    }

    @OptIn(DelicateCoroutinesApi::class)
    private fun authorizationCodeReceived(authorizationCode: String) =
        GlobalScope.launch(Dispatchers.IO) {
            try {
                val accessTokenResponse: AccessTokenResponse =
                    Application.getNetwork.accessToken(authorizationCode = authorizationCode)
                SharedManager.accessToken = accessTokenResponse.accessToken
                SharedManager.refreshToken = accessTokenResponse.refreshToken
                Log.d(TAG, "onResponse: access_token: $accessTokenResponse")
                MeetingActivity.startActivity(this@LoginActivity)
            } catch (e: Exception) {
                withContext(Dispatchers.Main) {
                    showToast("An error has occured, $e")
                }
            }
        }

    override fun onBackPressed() {
        if (webView.canGoBack()) {
            webView.goBack()
        } else {
            super.onBackPressed()
        }
    }

    companion object {
        private val TAG = LoginActivity::class.java.simpleName

        fun startActivity(context: Context) =
            context.startActivity(Intent(context, LoginActivity::class.java))
    }
}

