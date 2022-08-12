package com.example.chatappfirst.presentation.chat

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.chatappfirst.data.remote.ChatSocketService
import com.example.chatappfirst.data.remote.MessageService
import com.example.chatappfirst.util.CHAT_ARGUMENT_KEY
import com.example.chatappfirst.util.Resource
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class ChatViewModel(
    private val savedStateHandle: SavedStateHandle,
    private val messageService: MessageService,
    private val chatSocketService: ChatSocketService
) : ViewModel() {

    private val _messageText = mutableStateOf("")
    val messageText: State<String> = _messageText

    private val _state = mutableStateOf(ChatState())
    val state: State<ChatState> = _state

    private val _toastEvent = MutableSharedFlow<String>()
    val toastEvent = _toastEvent.asSharedFlow()

    private val _currentName: MutableStateFlow<String?> = MutableStateFlow("")
    val currentName: StateFlow<String?> = _currentName

    init {
        viewModelScope.launch {
            val currentName = savedStateHandle.get<String>(CHAT_ARGUMENT_KEY)
            _currentName.value = currentName
        }
    }

    fun connectToChat() {
        getAllMessages()
        savedStateHandle.get<String>(CHAT_ARGUMENT_KEY)?.let { username ->
            viewModelScope.launch {
                when (val result = chatSocketService.initSession(username)) {
                    is Resource.Success -> {
                        chatSocketService.observeMessages()
                            .onEach { message ->
                                val newList = state.value.messages.toMutableList().apply {
                                    add(0, message)
                                }
                                _state.value = state.value.copy(
                                    messages = newList
                                )
                            }.launchIn(viewModelScope)
                    }
                    is Resource.Error -> {
                        _toastEvent.emit(result.message ?: "Unknown error")
                    }
                }
            }
        }
    }

    fun onMessageChange(message: String) {
        _messageText.value = message
    }

    fun disconnect() {
        viewModelScope.launch {
            chatSocketService.closeSession()
        }
    }

    private fun getAllMessages() {
        viewModelScope.launch {
            _state.value = state.value.copy(isLoading = true)
            val result = messageService.getAllMessage()
            _state.value = state.value.copy(
                messages = result,
                isLoading = false
            )
        }
    }

    fun sendMessage() {
        viewModelScope.launch {
            if (messageText.value.isNotBlank())
                chatSocketService.sendMessage(messageText.value)
        }
    }

    override fun onCleared() {
        super.onCleared()
        disconnect()
    }
}