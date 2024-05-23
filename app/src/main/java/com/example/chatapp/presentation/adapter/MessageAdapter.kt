package com.example.chatapp.presentation.adapter

import android.text.TextUtils
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.chatapp.domain.model.ChatMessage
import com.example.chatapp.presentation.utils.ID
import com.example.chatapp.presentation.utils.decodeImage
import com.exmaple.chatapp.R
import com.exmaple.chatapp.databinding.ItemRecipientMessageBinding
import com.exmaple.chatapp.databinding.ItemSenderMessageBinding
import com.exmaple.chatapp.databinding.ItemUserConnectedBinding

class MessageAdapter(val data: ArrayList<ChatMessage>) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val LAYOUT_SENDER = 0
    private val LAYOUT_RECIPIENT = 1

    class SenderViewHolder(private val item: ItemSenderMessageBinding) :
        RecyclerView.ViewHolder(item.root) {
        fun bind(message: ChatMessage) {
            if (message.content.length <= 200) {
                item.messageBody.visibility = View.VISIBLE
                item.messageBody.text = message.content
            } else {
                item.messageBody.visibility = View.GONE
//                item.imageView.setImageBitmap(decodeImage(message.content));
            }
        }
    }

    class RecipientViewHolder(private val item: ItemRecipientMessageBinding) :
        RecyclerView.ViewHolder(item.root) {
        fun bind(message: ChatMessage) {
            item.messageName.text = message.fromUserId
//            item.image.setImageBitmap(decodeImage(message.content))
            if (message.content.length <= 200) {
                item.messageBody.visibility = View.VISIBLE
                item.messageBody.text = message.content
            } else {
                item.messageBody.visibility = View.GONE
//                item.imageView.setImageBitmap(decodeImage(message.content));
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        Log.d("TAG", "onCreateViewHolder: "+viewType)
        if (viewType==LAYOUT_SENDER){
            return SenderViewHolder(
                ItemSenderMessageBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            )
        }else  {
            return RecipientViewHolder(
                ItemRecipientMessageBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            )
        }

    }


    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when {
            getItemViewType(position) == LAYOUT_SENDER -> {
                (holder as SenderViewHolder).bind(data[position])
            }
            getItemViewType(position) == LAYOUT_RECIPIENT -> {
                (holder as RecipientViewHolder).bind(data[position])
            }
        }
    }

    override fun getItemCount() = data.size

    override fun getItemViewType(position: Int): Int {
        data[position].also { message ->
            Log.d("TAG", "getItemViewType: "+message+ ID)
            return when (message.fromUserId) {
                ID -> {
                    LAYOUT_SENDER
                }
                else -> LAYOUT_RECIPIENT
            }
        }

    }


}