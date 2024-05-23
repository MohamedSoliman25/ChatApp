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
//       val message = ChatMessage(content = content, fromUserId = fromUserId, toUserId = toUserId, timestamp = getDateFormat())
//        viewModelScope.launch {
//            chatRepository.insertMessage(message)
//        }
        webSocketService.sendMessage(fromUserId, toUserId, content,timestamp)
    }

//    private fun getDateFormat():String{
//        val dateFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault())
//        val date = SimpleDateFormat("MMMM d, yyyy h:mm:ss a", Locale.ENGLISH)
//        return dateFormat.format(date)
//        val simpleDateFormat = SimpleDateFormat("yyyy/MM/dd HH:mm:ss",Locale.getDefault())
//       return SimpleDateFormat("yyyy/MM/dd", Locale.getDefault()).format(simpleDateFormat.parse("2022/02/01 14:23:05")!!)
//    }
}
