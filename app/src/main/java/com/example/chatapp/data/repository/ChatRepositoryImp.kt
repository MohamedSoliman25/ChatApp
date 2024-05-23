package com.example.chatapp.data.repository

import com.example.chatapp.data.local.ChatMessageDao
import com.example.chatapp.domain.model.ChatMessage
import com.example.chatapp.domain.repository.ChatRepository
import kotlinx.coroutines.flow.Flow

class ChatRepositoryImp  (
    private val chatMessageDao: ChatMessageDao
) : ChatRepository {
    override suspend fun insertMessage(chatMessage: ChatMessage) {
        chatMessageDao.insertMessage(chatMessage)
    }

    override fun getAllMessages(fromUserId:String,toUserId:String): Flow<List<ChatMessage>> {
        return chatMessageDao.getAllMessages(fromUserId, toUserId)
    }
}