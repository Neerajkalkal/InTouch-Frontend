package com.example.intouch.Uii.Screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.intouch.Uii.homeScreen.CallButton
import com.example.intouch.Uii.homeScreen.VideoButton
import kotlinx.coroutines.launch

data class Message(
    val text: String,
    val isSentByUser: Boolean,
    val time: String
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ChatScreen(
    name: String = "Kathryn Murphy",
    onBack: () -> Unit = {}
) {
    val messages = remember {
        mutableStateListOf(
            Message("Hey everyone! 😊", true, "10:00 PM"),
            Message("Let’s brainstorm some ideas for the upcoming project. 😁", true, "10:01 PM"),
            Message("Hi, morning Sheroz!", false, "10:13 PM"),
            Message("Sounds great! I have a few ideas already. How about we set up a time to discuss them?", false, "10:16 PM")
        )
    }

    var messageText by remember { mutableStateOf("") }
    val coroutineScope = rememberCoroutineScope()

    Scaffold(
        topBar = {
            ChatTopBar(name = name, onBack = onBack)
        },
        containerColor = Color(0xFF0D0D0D),
        bottomBar = {
            ChatInputBar(
                messageText = messageText,
                onMessageChange = { messageText = it },
                onSendClick = {
                    if (messageText.isNotBlank()) {
                        coroutineScope.launch {
                            messages.add(Message(messageText, true, "10:18 PM"))
                            messageText = ""
                        }
                    }
                },
                onEmojiClick = {
                    // TODO: open emoji picker UI
                    println("Emoji button clicked")
                },
                onAttachClick = {
                    // TODO: open file picker
                    println("Attach button clicked")
                },
                onCameraClick = {
                    // TODO: open camera intent
                    println("Camera button clicked")
                }
            )
        }
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(horizontal = 12.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp),
        ) {
            item {
                Text(
                    text = "Today",
                    color = Color.Gray,
                    fontSize = 13.sp,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp),
                    textAlign = TextAlign.Center
                )
            }

            items(messages) { msg ->
                ChatBubble(message = msg)
            }
        }
    }
}

@Composable
fun ChatTopBar(name: String, onBack: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color(0xFF141414))
            .padding(horizontal = 12.dp, vertical = 50.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        IconButton(onClick = onBack) {
            Icon(
                imageVector = Icons.Default.ArrowBack,
                contentDescription = "Back",
                tint = Color(0xFF266EFF)
            )
        }

        Column(modifier = Modifier.weight(1f)) {
            Text(name, color = Color.White, fontWeight = FontWeight.Bold, fontSize = 16.sp)
        }

        Row {
            IconButton(onClick = { /* TODO: start voice call */ }) {
                CallButton()
            }
            IconButton(onClick = { /* TODO: start video call */ }) {
                VideoButton()
            }
        }
    }
}

@Composable
fun ChatBubble(message: Message) {
    val bubbleColor = if (message.isSentByUser) Color(0xFF005FFF) else Color(0xFF1F1F1F)
    val alignment = if (message.isSentByUser) Alignment.End else Alignment.Start
    val shape = if (message.isSentByUser) {
        RoundedCornerShape(16.dp, 16.dp, 0.dp, 16.dp)
    } else {
        RoundedCornerShape(16.dp, 16.dp, 16.dp, 0.dp)
    }

    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = alignment
    ) {
        Box(
            modifier = Modifier
                .widthIn(max = 280.dp)
                .clip(shape)
                .background(bubbleColor)
                .padding(horizontal = 14.dp, vertical = 10.dp)
        ) {
            Text(
                text = message.text,
                color = Color.White,
                fontSize = 15.sp
            )
        }
        Text(
            text = message.time,
            color = Color.Gray,
            fontSize = 11.sp,
            modifier = Modifier.padding(top = 4.dp, end = 8.dp)
        )
    }
}

@Composable
fun ChatInputBar(
    messageText: String,
    onMessageChange: (String) -> Unit,
    onSendClick: () -> Unit,
    onEmojiClick: () -> Unit,
    onAttachClick: () -> Unit,
    onCameraClick: () -> Unit
) {
    val focusRequester = remember { FocusRequester() }
    val keyboardController = LocalSoftwareKeyboardController.current

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color(0xFF121212))
            .padding(8.dp , bottom = 25.dp , ),
        verticalAlignment = Alignment.CenterVertically
    ) {
        // Rounded message box with icons and text field
        Row(
            modifier = Modifier
                .weight(1f)
                .clip(RoundedCornerShape(24.dp))
                .background(Color(0xFF1C1C1C))
                .padding(horizontal = 8.dp, vertical = 4.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // 😊 Emoji button
            IconButton(onClick = {
                keyboardController?.hide()
                onEmojiClick()
            }) {
                Icon(
                    imageVector = Icons.Default.TagFaces,
                    contentDescription = "Emoji",
                    tint = Color(0xFF9A9A9A),
                    modifier = Modifier.size(28.dp)
                )
            }

            // 📝 Text input field
            TextField(
                value = messageText,
                onValueChange = onMessageChange,
                placeholder = { Text("Message", color = Color(0xFF9A9A9A)) },
                textStyle = LocalTextStyle.current.copy(color = Color.White),
                singleLine = true,
                modifier = Modifier
                    .weight(1f)
                    .focusRequester(focusRequester)
                    .onFocusChanged {
                        if (it.isFocused) keyboardController?.show()
                    },
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = Color.Transparent,
                    unfocusedContainerColor = Color.Transparent,
                    disabledContainerColor = Color.Transparent,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    cursorColor = Color.White
                )
            )

            // 🔗 Attachment button
            IconButton(onClick = {
                keyboardController?.hide()
                onAttachClick()
            }) {
                Icon(
                    imageVector = Icons.Default.Link,
                    contentDescription = "Attach",
                    tint = Color(0xFF9A9A9A),
                    modifier = Modifier.size(28.dp)
                )
            }

            // 📷 Camera button
            IconButton(onClick = {
                keyboardController?.hide()
                onCameraClick()
            }) {
                Icon(
                    imageVector = Icons.Default.CameraAlt,
                    contentDescription = "Camera",
                    tint = Color(0xFF9A9A9A),
                    modifier = Modifier.size(28.dp)
                )
            }
        }

        Spacer(modifier = Modifier.width(8.dp))

        // ✈️ Send button
        IconButton(
            onClick = {
                keyboardController?.hide()
                onSendClick()
            },
            modifier = Modifier
                .size(48.dp)
                .clip(RoundedCornerShape(16.dp))
                .background(Color(0xFF005FFF))
        ) {
            Icon(
                imageVector = Icons.Default.Send,
                contentDescription = "Send",
                tint = Color.White,
                modifier = Modifier.size(22.dp)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ChatScreenPreview() {
    ChatScreen()
}
