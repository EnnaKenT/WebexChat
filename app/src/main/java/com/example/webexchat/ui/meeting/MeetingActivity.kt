package com.example.webexchat.ui.meeting

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isGone
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.example.webexchat.R
import com.example.webexchat.application.Application
import com.example.webexchat.retrofiit.response.MeetingResponse
import com.example.webexchat.ui.meeting.adapter.MeetingAdapter
import kotlinx.coroutines.*

class MeetingActivity : AppCompatActivity(R.layout.activity_meeting) {
    private lateinit var recyclerView: RecyclerView
    private lateinit var placeholder: TextView
    private val adapter = MeetingAdapter()

    @SuppressLint("SetJavaScriptEnabled")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        recyclerView = findViewById(R.id.recyclerView)
        placeholder = findViewById(R.id.placeholder)
        recyclerView.adapter = adapter
        getMeetings()
    }

    @OptIn(DelicateCoroutinesApi::class)
    private fun getMeetings() = GlobalScope.launch(Dispatchers.IO) {
        try {
            val meetingResponse: MeetingResponse = Application.getNetwork.getMeetings()
            if (meetingResponse.items.isNotEmpty()) {
                withContext(Dispatchers.Main) {
                    adapter.submitList(meetingResponse.items)
                    recyclerView.isVisible = true
                    placeholder.isGone = true
                }
            } else {
                withContext(Dispatchers.Main) {
                    placeholder.text = "No meetings found"
                }
            }
        } catch (e: Exception) {
            withContext(Dispatchers.Main) {
                placeholder.text = "An error has occured, $e"
            }
        }
    }

    companion object {
        fun startActivity(context: Context) =
            context.startActivity(Intent(context, MeetingActivity::class.java))
    }
}

