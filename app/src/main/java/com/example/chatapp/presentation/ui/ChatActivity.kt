package com.example.chatapp.presentation.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.chatapp.domain.model.ChatMessage
import com.example.chatapp.presentation.adapter.MessageAdapter
import com.example.chatapp.presentation.utils.EXTRA_FROM_USER_ID
import com.example.chatapp.presentation.utils.EXTRA_To_USER_ID
import com.example.chatapp.presentation.utils.ID
import com.example.chatapp.presentation.viewmodel.ChatViewModel
import com.exmaple.chatapp.databinding.ActivityChatBinding
import dagger.hilt.android.AndroidEntryPoint
import java.util.*

@AndroidEntryPoint
class ChatActivity : AppCompatActivity() {

    private lateinit var binding:ActivityChatBinding
    private val chatViewModel by viewModels<ChatViewModel>()
    private val adapterMessage by lazy { MessageAdapter(arrayListOf()) }
    private var isDataShow = true
    var fromUserId = ""
    var toUserId = ""


    private val TAG = "ChatActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityChatBinding.inflate(layoutInflater)
        setContentView(binding.root)
        receiveUserData()
        initRecyclerView()
        handleSendMessage()
        chatViewModel.getAllMessages(fromUserId, toUserId)
        observeAllMessages()
    }


    private fun receiveUserData(){
        fromUserId = intent.getStringExtra(EXTRA_FROM_USER_ID)!!
        toUserId = intent.getStringExtra(EXTRA_To_USER_ID)!!
        ID = toUserId
        binding.apply { tvTitle.text = toUserId }
    }
    private fun initRecyclerView(){
        binding.recyclerViewMessage.apply {
            adapter = adapterMessage
        }
    }

    private fun handleSendMessage() {
        binding.apply {
            btnSend.setOnClickListener {
            sendMessage()

            }
        }

    }

    private fun sendMessage(){
        val message: String = binding.msgText.text.toString().trim { it <= ' ' }
        if (TextUtils.isEmpty(message)) {
            return
        }
        chatViewModel.sendMessage(fromUserId,toUserId,message)
        adapterMessage.apply {
            this.data.add(
                0,
                ChatMessage(0,message,fromUserId,toUserId, Date())
            )
            binding.recyclerViewMessage.scrollTo(0, adapterMessage.itemCount - 1)
            notifyDataSetChanged()
        }
        binding.msgText.text.clear()
    }



    private fun observeAllMessages(){
        lifecycleScope.launchWhenStarted {
            chatViewModel.messages.collect{chatList->
                if (chatList.isNotEmpty()){
                    if (isDataShow) {
                        isDataShow = false
                        adapterMessage.apply {
                            this.data.apply {
                                clear()
                                addAll(chatList)
                            }
                            notifyDataSetChanged()
                        }
                    }
                }
            }
        }
    }
}