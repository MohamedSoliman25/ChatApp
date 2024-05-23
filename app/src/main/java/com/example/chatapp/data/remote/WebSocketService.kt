package com.example.chatapp.data.remote

import android.util.Log
import com.example.chatapp.domain.model.ChatMessage
import com.example.chatapp.domain.repository.ChatRepository
import com.google.gson.Gson
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import okhttp3.*
import okio.ByteString
import java.util.Date


class WebSocketService constructor(
    private val chatRepository: ChatRepository
) {
    private var webSocket: WebSocket? = null
    private val gson = Gson()

    fun connectWebSocket() {
        val localHost = "192.168.1.12"
        val client = OkHttpClient()
        val request = Request.Builder().url("ws://${localHost}:3000").build()
        val listener = ChatWebSocketListener()
        webSocket = client.newWebSocket(request, listener)
    }

    fun sendMessage(fromUserId: String, toUserId: String, content: String,timestamp:Date) {
        val message = mapOf(
            "fromUserId" to fromUserId,
            "toUserId" to toUserId,
            "content" to content,
            "timestamp" to timestamp)
        webSocket?.send(gson.toJson(message))
    }

    private inner class ChatWebSocketListener : WebSocketListener() {
        override fun onOpen(webSocket: WebSocket, response: Response) {
            Log.d("WebSocket", "Connected to server")
        }

        override fun onMessage(webSocket: WebSocket, text: String) {
            Log.d("WebSocket", "Received message: $text")
            if (text.contains("New Incoming Message")){
                val jsonString = text.substringAfter("From ")
                val chatMessage = gson.fromJson(jsonString, ChatMessage::class.java)
                CoroutineScope(Dispatchers.IO).launch {
                chatRepository.insertMessage(chatMessage)
            }
            }


        }

        override fun onMessage(webSocket: WebSocket, bytes: ByteString) {
            Log.d("WebSocket", "Received message byte: ${bytes.hex()}")
        }

        override fun onClosing(webSocket: WebSocket, code: Int, reason: String) {
            webSocket.close(1000, null)
            Log.d("WebSocket", "Connection closed: $reason")
        }

        override fun onFailure(webSocket: WebSocket, t: Throwable, response: Response?) {
            Log.d("WebSocket", "Connection failed: ${t.message}")
        }
    }
}
