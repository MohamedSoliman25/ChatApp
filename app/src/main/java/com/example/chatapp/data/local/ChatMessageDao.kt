package com.example.chatapp.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.chatapp.domain.model.ChatMessage
import kotlinx.coroutines.flow.Flow

@Dao
interface ChatMessageDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMessage(chatMessage: ChatMessage)

    @Query("SELECT * FROM chat_messages WHERE (fromUserId LIKE:fromUserId AND toUserId LIKE:toUserId) OR  (fromUserId LIKE:toUserId AND toUserId LIKE:fromUserId) ORDER BY timestamp Desc")
    fun getAllMessages(fromUserId:String,toUserId:String): Flow<List<ChatMessage>>
}
