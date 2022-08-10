package com.example.chatappfirst.data.remote.util

sealed class EndPoints(val url:String){
    object GetAllMessages:EndPoints("$BASE_URL/messages")
    object ChatSocket:EndPoints("$BASE_URL_WS/chat-socket")
}