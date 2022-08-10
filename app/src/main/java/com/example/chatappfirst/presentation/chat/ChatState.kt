package com.example.chatappfirst.presentation.chat

import com.example.chatappfirst.domain.model.MessageModel

data class ChatState(
    val messages: List<MessageModel> = emptyList(),
    val isLoading: Boolean = false
) {
}