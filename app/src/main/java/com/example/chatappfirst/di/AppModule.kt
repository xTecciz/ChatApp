package com.example.chatappfirst.di


import com.example.chatappfirst.data.remote.ChatSocketService
import com.example.chatappfirst.data.remote.ChatSocketServiceImpl
import com.example.chatappfirst.data.remote.MessageService
import com.example.chatappfirst.data.remote.MessageServiceImpl
import com.example.chatappfirst.presentation.chat.ChatViewModel
import com.example.chatappfirst.presentation.username.UsernameViewModel
import io.ktor.client.*
import io.ktor.client.engine.cio.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.plugins.logging.*
import io.ktor.client.plugins.websocket.*
import io.ktor.serialization.kotlinx.json.*
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    single {
        HttpClient(CIO) {
            install(Logging)
            install(WebSockets)
            install(ContentNegotiation) {
                json()
            }
        }
    }
    single<MessageService> {
        MessageServiceImpl(get())
    }
    single<ChatSocketService> {
        ChatSocketServiceImpl(get())
    }
    viewModel {
        ChatViewModel(get(),get(),get())
    }
    viewModel{
        UsernameViewModel()
    }
}
