package com.example.webexchat

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.View
import android.webkit.WebView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.webexchat.retrofiit.AccessTokenResponse
import com.example.webexchat.retrofiit.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    private lateinit var webView: WebView

    @SuppressLint("SetJavaScriptEnabled")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        webView = findViewById<View>(R.id.webView) as WebView
        val customWebView = CustomWebView()
        customWebView.setOnLoadCode {
            Log.d(TAG, "onCreate $it")
            // to do something with code

            val grantType = "authorization_code"
            val clientId = BuildConfig.CLIENT_ID
            val clientSecret = BuildConfig.CLIENT_SECRET
            val code = it
            val redirectUri = BuildConfig.REDIRECT_URI

            val call: Call<AccessTokenResponse> = RetrofitClient.auth.accessToken(
                grantType,
                clientId,
                clientSecret,
                code,
                redirectUri
            )!!

            call.enqueue(object : Callback<AccessTokenResponse> {
                override fun onResponse(
                    call: Call<AccessTokenResponse>,
                    response: Response<AccessTokenResponse>
                ) {
                    val accessTokenResponse: AccessTokenResponse = response.body()!!
                    if (response.code() == 200) {
                        Log.d(TAG, "onResponse: access_token: ${accessTokenResponse}")
                    } else {
                        Log.d(TAG, "onResponse: code not 200 is: ${response.code()}")
                    }
                }

                override fun onFailure(call: Call<AccessTokenResponse>, t: Throwable) {
                    Toast.makeText(applicationContext, "An error has occured", Toast.LENGTH_LONG)
                        .show()
                }
            })
        }
        webView.webViewClient = customWebView

        val clientId = BuildConfig.CLIENT_ID
        webView.loadUrl(
            "https://webexapis.com/v1/authorize?client_id=$clientId&response_type=code&redirect_uri=https%3A%2F%2Fwebexdemoapp.com&scope=spark-admin%3Abroadworks_subscribers_write%20meeting%3Aadmin_preferences_write%20spark%3Aall%20meeting%3Aadmin_preferences_read%20analytics%3Aread_all%20meeting%3Aadmin_participants_read%20spark-admin%3Apeople_write%20spark-admin%3Aworkspace_metrics_read%20spark-admin%3Aplaces_read%20spark-compliance%3Ateam_memberships_write%20spark-compliance%3Amessages_read%20spark-admin%3Adevices_write%20spark-admin%3Aworkspaces_write%20spark-compliance%3Ameetings_write%20meeting%3Aadmin_schedule_write%20identity%3Aplaceonetimepassword_create%20spark-admin%3Aorganizations_write%20spark-admin%3Aworkspace_locations_read%20spark-compliance%3Awebhooks_read%20spark-admin%3Acall_qualities_read%20spark-compliance%3Amessages_write%20spark%3Akms%20meeting%3Aparticipants_write%20meeting%3Aadmin_transcripts_read%20spark-admin%3Apeople_read%20spark-compliance%3Amemberships_read%20spark-admin%3Aresource_groups_read%20meeting%3Arecordings_read%20meeting%3Aparticipants_read%20meeting%3Apreferences_write%20meeting%3Aadmin_recordings_read%20spark-admin%3Aorganizations_read%20spark-compliance%3Awebhooks_write%20meeting%3Atranscripts_read%20meeting%3Aschedules_write%20spark-compliance%3Ateam_memberships_read%20spark-admin%3Adevices_read%20meeting%3Acontrols_read%20spark-admin%3Ahybrid_clusters_read%20spark-admin%3Aworkspace_locations_write%20spark-admin%3Abroadworks_enterprises_write%20meeting%3Aadmin_schedule_read%20meeting%3Aschedules_read%20spark-compliance%3Amemberships_write%20spark-admin%3Abroadworks_enterprises_read%20spark-admin%3Aroles_read%20meeting%3Arecordings_write%20meeting%3Apreferences_read%20spark-admin%3Aworkspaces_read%20spark-admin%3Aresource_group_memberships_read%20spark-compliance%3Aevents_read%20spark-admin%3Aresource_group_memberships_write%20spark-compliance%3Arooms_read%20spark-admin%3Abroadworks_subscribers_read%20meeting%3Acontrols_write%20meeting%3Aadmin_recordings_write%20spark-admin%3Ahybrid_connectors_read%20audit%3Aevents_read%20spark-compliance%3Ateams_read%20spark-admin%3Aplaces_write%20spark-admin%3Alicenses_read%20spark-compliance%3Arooms_write&state=set_state_here"
        )
        webView.webViewClient
        webView.settings.javaScriptEnabled = true
    }

    override fun onBackPressed() {
        if (webView.canGoBack()) {
            webView.goBack()
        } else {
            super.onBackPressed()
        }
    }

    companion object {
        private const val TAG = "MainActivity"
    }
}

