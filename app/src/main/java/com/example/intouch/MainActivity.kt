package com.example.intouch

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.MaterialTheme
import com.example.intouch.Uii.Screens.ChatListScreen
import com.example.intouch.navcontroller.AppNavHost
import com.example.intouch.ui.theme.InTouchTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MaterialTheme {
//                AppNavHost() // ✅ call the composable
                ChatListScreen(onChatClick = { chat ->
                    println("Clicked on chat: ${chat.name}")
                })
            }
        }
    }
}
