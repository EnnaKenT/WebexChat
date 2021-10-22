package com.example.webexchat.ui.meeting.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.webexchat.R
import com.example.webexchat.retrofiit.response.Meeting
import com.example.webexchat.showToast

class MeetingAdapter :
    ListAdapter<Meeting, MeetingAdapter.ListenersViewHolder>(ListenersItemCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListenersViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_meeting, parent, false)
        return ListenersViewHolder(view)
    }

    override fun onBindViewHolder(holder: ListenersViewHolder, position: Int) =
        holder.bind(getItem(position))

    inner class ListenersViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private var title: TextView = itemView.findViewById(R.id.title)

        init {
            itemView.setOnClickListener { itemView.context.showToast("title clicked") }
        }

        fun bind(meeting: Meeting) {
            title.text = meeting.title
        }
    }

}

internal class ListenersItemCallback : DiffUtil.ItemCallback<Meeting>() {
    override fun areItemsTheSame(model1: Meeting, model2: Meeting) = model1 == model2
    override fun areContentsTheSame(model1: Meeting, model2: Meeting) = model1 == model2
}