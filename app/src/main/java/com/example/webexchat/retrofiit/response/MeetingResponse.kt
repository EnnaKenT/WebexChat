package com.example.webexchat.retrofiit.response

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class MeetingResponse(val items: List<Meeting>) : Parcelable

@Parcelize
data class Meeting(val id: String, val title: String) : Parcelable
