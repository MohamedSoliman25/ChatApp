package com.example.chatapp.presentation.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.chatapp.presentation.utils.EXTRA_FROM_USER_ID
import com.example.chatapp.presentation.utils.EXTRA_To_USER_ID
import com.example.chatapp.presentation.viewmodel.ChatViewModel
import com.exmaple.chatapp.R
import com.exmaple.chatapp.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint
import java.util.Date

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private val TAG = "MainActivity"

    private lateinit var binding:ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        handleChatClick()
    }

    private fun handleChatClick(){
        binding.apply {
            btnChat.setOnClickListener {
                val intent = Intent(this@MainActivity, ChatActivity::class.java).apply {
                    putExtra(EXTRA_FROM_USER_ID, fromUser.text.toString())
                    putExtra(EXTRA_To_USER_ID, toUser.text.toString())
                }
                startActivity(intent)
            }

        }
    }

}