package com.example.chatappfirst.presentation.username

import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.chatappfirst.presentation.navigation.Screen
import kotlinx.coroutines.flow.collectLatest

@Composable
fun UsernameScreen(
    navHostController: NavHostController,
    viewModel: UsernameViewModel = hiltViewModel()
) {
    LaunchedEffect(key1 = true) {
        viewModel.onJoinChat.collectLatest { username ->
            navHostController.navigate(Screen.Chat.passUsername(username))
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.End
        ) {
            TextField(
                value = viewModel.usernameText.value,
                onValueChange = viewModel::onUsernameChange,
                placeholder =
                {
                    Text(text = "Enter a username...")
                },
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(8.dp))
            Button(
                onClick = viewModel::onJoinClick
            ) {
                Text(text = "Join")
            }
        }
    }
}