package com.example.intouch.data.model

data class ChatItem(
    val name: String,
    val lastMessage: String,
    val time: String,
    val unreadCount: Int = 0,
    val imageUrl: String? = null,
    val isGroup: Boolean = false,
    val isPinned: Boolean = false
)