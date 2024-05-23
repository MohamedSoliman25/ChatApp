package com.example.chatapp.presentation.di

import android.content.Context
import androidx.room.Room
import com.example.chatapp.data.local.ChatDatabase
import com.example.chatapp.data.local.ChatMessageDao
import com.example.chatapp.data.remote.WebSocketService
import com.example.chatapp.data.repository.ChatRepositoryImp
import com.example.chatapp.domain.repository.ChatRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ChatModule {
    @Provides
    @Singleton
    fun provideChatDatabase(@ApplicationContext context: Context): ChatDatabase {
        return Room.databaseBuilder(
            context,
            ChatDatabase::class.java,
            "chat_database"
        ).build()
    }

    @Provides
    @Singleton
    fun provideChatMessageDao(chatDatabase: ChatDatabase): ChatMessageDao {
        return chatDatabase.chatMessageDao()
    }

    @Provides
    @Singleton
    fun provideWebSocketService(chatRepository: ChatRepository): WebSocketService {
        return WebSocketService(chatRepository)
    }

    @Provides
    @Singleton
    fun provideChatRepository(chatMessageDao: ChatMessageDao): ChatRepository {
        return ChatRepositoryImp(chatMessageDao)
    }

}