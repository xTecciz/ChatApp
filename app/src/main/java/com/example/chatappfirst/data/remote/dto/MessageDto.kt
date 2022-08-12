package com.example.chatappfirst.data.remote.dto

import com.example.chatappfirst.domain.model.MessageModel
import kotlinx.serialization.Serializable
import java.text.DateFormat
import java.util.*

@Serializable
data class MessageDto(
    val id: String,
    val text: String,
    val timestamp: Long,
    val username: String
) {
    fun toMessageModel(): MessageModel {
        val date = Date(timestamp)
        val formattedDate = DateFormat
            .getDateInstance(DateFormat.DEFAULT)
            .format(date)
        return MessageModel(
            text = text,
            date = formattedDate,
            username = username
        )
    }
}