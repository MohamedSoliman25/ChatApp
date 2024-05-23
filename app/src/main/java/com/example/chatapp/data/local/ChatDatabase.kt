package com.example.chatapp.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.chatapp.domain.model.ChatMessage

@Database(entities = [ChatMessage::class], version = 1)
@TypeConverters(Converters::class)
abstract class ChatDatabase : RoomDatabase() {
    abstract fun chatMessageDao(): ChatMessageDao
}
