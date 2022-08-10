package com.example.chatappfirst.data.remote

import com.example.chatappfirst.domain.model.MessageModel
import com.example.chatappfirst.util.Resource
import kotlinx.coroutines.flow.Flow

interface ChatSocketService {

    suspend fun initSession(username: String): Resource<Unit>

    suspend fun sendMessage(message: String)

    fun observeMessages(): Flow<MessageModel>

    suspend fun closeSession()
}