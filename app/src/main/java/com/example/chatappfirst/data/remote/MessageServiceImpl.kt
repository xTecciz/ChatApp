package com.example.chatappfirst.data.remote

import com.example.chatappfirst.data.remote.dto.MessageDto
import com.example.chatappfirst.data.remote.util.EndPoints
import com.example.chatappfirst.domain.model.MessageModel
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*


class MessageServiceImpl(private val client: HttpClient) : MessageService {

    override suspend fun getAllMessage(): List<MessageModel> {
        return try {
            client.get(EndPoints.GetAllMessages.url).body<List<MessageDto>>().map {
                it.toMessageModel()
            }

//            client.get<List<MessageDto>>(EndPoints.GetAllMessages.url).map {
//                it.toMessageModel()
//            }
        } catch (e: Exception) {
            emptyList()
        }
    }
}


