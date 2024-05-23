package com.example.chatapp.domain.repository

import com.example.chatapp.domain.model.ChatMessage
import kotlinx.coroutines.flow.Flow

interface ChatRepository {
    suspend fun insertMessage(chatMessage: ChatMessage)
    fun getAllMessages(fromUserId:String,toUserId:String): Flow<List<ChatMessage>>
}