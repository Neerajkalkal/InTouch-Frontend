package com.example.intouch.data.model

data class ChatMessage(
    val id: String = "",
    val senderId: String = "",
    val receiverId: String = "",
    val message: String = "",
    val type: String = "text", // "text", "voice", "image"
    val timestamp: Long = System.currentTimeMillis(),
    val attachmentUrl: String? = null
)

