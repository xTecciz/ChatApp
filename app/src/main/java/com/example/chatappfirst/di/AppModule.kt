package com.example.chatappfirst.di


import com.example.chatappfirst.data.remote.ChatSocketService
import com.example.chatappfirst.data.remote.ChatSocketServiceImpl
import com.example.chatappfirst.data.remote.MessageService
import com.example.chatappfirst.data.remote.MessageServiceImpl
import com.example.chatappfirst.presentation.chat.ChatViewModel
import com.example.chatappfirst.presentation.username.UsernameViewModel
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.ktor.client.*
import io.ktor.client.engine.cio.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.plugins.logging.*
import io.ktor.client.plugins.websocket.*
import io.ktor.serialization.kotlinx.json.*
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import javax.inject.Singleton

//val appModule = module {
//    single {
//        HttpClient(CIO) {
//            install(Logging)
//            install(WebSockets)
//            install(ContentNegotiation) {
//                json()
//            }
//        }
//    }
//    single<MessageService> {
//        MessageServiceImpl(get<HttpClient>())
//    }
//    single<ChatSocketService> {
//        ChatSocketServiceImpl(get<HttpClient>())
//    }
//    viewModel { params ->
//        ChatViewModel(params.get(), get<ChatSocketService>(), get<MessageService>())
//    }
//    viewModel {
//        UsernameViewModel()
//    }
//}
@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideHttpClient(): HttpClient {
        return HttpClient(CIO) {
            install(Logging)
            install(WebSockets)
            install(ContentNegotiation) {
                json()
            }
        }
    }

    @Provides
    @Singleton
    fun provideMessageService(client: HttpClient): MessageService {
        return MessageServiceImpl(client)
    }

    @Provides
    @Singleton
    fun provideChatSocketService(client: HttpClient): ChatSocketService {
        return ChatSocketServiceImpl(client)
    }
}