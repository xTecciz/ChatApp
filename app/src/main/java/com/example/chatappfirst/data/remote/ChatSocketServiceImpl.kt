package com.example.chatappfirst.data.remote

import com.example.chatappfirst.data.remote.dto.MessageDto
import com.example.chatappfirst.data.remote.util.EndPoints
import com.example.chatappfirst.domain.model.MessageModel
import com.example.chatappfirst.util.Resource
import io.ktor.client.*
import io.ktor.client.plugins.websocket.*
import io.ktor.client.request.*
import io.ktor.websocket.*
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.isActive
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json

class ChatSocketServiceImpl(private val client: HttpClient) : ChatSocketService {
    private var socket: WebSocketSession? = null

    override suspend fun initSession(username: String): Resource<Unit> {
        return try {
            socket = client.webSocketSession {
                url("${EndPoints.ChatSocket.url}?username=$username")
            }
            if (socket?.isActive == true) Resource.Success(Unit)
            else Resource.Error("Connection error")
        } catch (e: Exception) {
            e.printStackTrace()
            Resource.Error(e.localizedMessage ?: "Error")
        }
    }

    override suspend fun sendMessage(message: String) {
        try {
            socket?.send(Frame.Text(message))
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    override fun observeMessages(): Flow<MessageModel> {
        return try {
            socket?.incoming?.receiveAsFlow()?.filter { it is Frame.Text }?.map {
                val json = (it as? Frame.Text)?.readText() ?: ""
                val messageDto = Json.decodeFromString<MessageDto>(json)
                messageDto.toMessageModel()
            } ?: flow { }
        } catch (e: Exception) {
            e.printStackTrace()
            flow {}
        }
    }

    override suspend fun closeSession() {
        socket?.close()
    }
}