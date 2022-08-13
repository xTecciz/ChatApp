package com.example.chatappfirst.data.remote.dto

import com.example.chatappfirst.domain.model.MessageModel
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import java.text.DateFormat
import java.util.*

@Serializable
data class MessageDto(
    @SerialName("id") val id: String,
    @SerialName("text") val text: String,
    @SerialName("username") val username: String,
    @SerialName("timestamp") val timestamp: Long
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