package com.example.chatappfirst.data.remote

import com.example.chatappfirst.domain.model.MessageModel

interface MessageService {
    suspend fun getAllMessage():List<MessageModel>
}