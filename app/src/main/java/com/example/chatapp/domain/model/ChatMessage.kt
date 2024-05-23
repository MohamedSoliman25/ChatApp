package com.example.chatapp.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "chat_messages")
data class ChatMessage(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val content: String,
    val fromUserId: String,
    val toUserId: String,
    val timestamp: Date
)