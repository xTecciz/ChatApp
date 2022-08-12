package com.example.chatappfirst.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.chatappfirst.presentation.chat.ChatScreen
import com.example.chatappfirst.presentation.username.UsernameScreen
import com.example.chatappfirst.util.CHAT_ARGUMENT_KEY

@Composable
fun SetupNavGraph(navHostController: NavHostController) {
    NavHost(
        navController = navHostController,
        startDestination = Screen.Username.route
    ) {
        composable(route = Screen.Username.route) {
            UsernameScreen(
                navHostController = navHostController,
                onNavigate = navHostController::navigate
            )
        }
        composable(
            route = Screen.Chat.route,
            arguments = listOf(navArgument(CHAT_ARGUMENT_KEY) {
                type = NavType.StringType
                nullable = true
            })
        ) {
            val username = it.arguments?.getString(CHAT_ARGUMENT_KEY)
            ChatScreen(navHostController = navHostController)
        }
    }
}