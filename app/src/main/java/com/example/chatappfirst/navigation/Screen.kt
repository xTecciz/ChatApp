package com.example.chatappfirst.navigation

sealed class Screen(val route: String) {
    object Username : Screen("username_screen")
    object Chat : Screen("chat_screen/{username}") {
        fun passUsername(username: String): String {
            return "chat_screen/$username"
        }
    }
}