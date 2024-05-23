package com.example.chatapp.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.chatapp.data.remote.WebSocketService
import com.example.chatapp.domain.model.ChatMessage
import com.example.chatapp.domain.repository.ChatRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

@HiltViewModel
class ChatViewModel @Inject constructor(
    private val chatRepository: ChatRepository,
    private val webSocketService: WebSocketService
) : ViewModel() {

    private val _messages = MutableStateFlow<List<ChatMessage>>(emptyList())
    val messages: StateFlow<List<ChatMessage>> = _messages.asStateFlow()

    init {
        webSocketService.connectWebSocket()
    }
    fun getAllMessages(fromUserId:String,toUserId:String){
        viewModelScope.launch {
            chatRepository.getAllMessages(fromUserId, toUserId).collect { messages ->
                _messages.value = messages
            }
        }
    }

    fun sendMessage(fromUserId: String, toUserId: String, content: String) {
        val timestamp = Date()
        webSocketService.sendMessage(fromUserId, toUserId, content,timestamp)
    }

}
