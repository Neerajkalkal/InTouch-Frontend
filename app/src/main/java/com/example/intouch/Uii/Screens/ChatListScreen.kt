package com.example.intouch.Uii.Screens


import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.filled.Chat
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Archive
import com.example.intouch.R
import com.example.intouch.Uii.homeScreen.NeonIconButton
import com.example.intouch.Uii.homeScreen.NeonIconButtons
import com.example.intouch.data.model.ChatItem


@Composable
fun ChatListScreen(
    modifier: Modifier = Modifier,
    onChatClick: (ChatItem) -> Unit = {}
) {
    val chats = remember {
        listOf(
            ChatItem("Office Group", "Sure!", "5:46 PM", imageUrl = "https://picsum.photos/200"),
            ChatItem("Family Group", "Ok.", "5:55 PM", imageUrl = "https://picsum.photos/210"),
            ChatItem("Kathryn Murphy", "Hi.", "5:55 PM", imageUrl = "https://picsum.photos/220", unreadCount = 4),
            ChatItem("Courtney Henry", "How are you?", "5:55 PM", imageUrl = "https://picsum.photos/230"),
            ChatItem("Devon Lane", "Thanks", "5:55 PM", imageUrl = "https://picsum.photos/240"),
            ChatItem("Ralph Edwards", "The long barrow was built on...", "5:55 PM", imageUrl = "https://picsum.photos/250"),
            ChatItem("Cameron Williamson", "Maxwell's equations—the foundation...", "5:55 PM", imageUrl = "https://picsum.photos/260"),
            ChatItem("Dianne Russell", "Physical space is often conceived...", "5:55 PM", imageUrl = "https://picsum.photos/270"),
            ChatItem("Bessie Cooper", "In the eighteenth century the...", "5:55 PM", imageUrl = "https://picsum.photos/280"),
        )
    }

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(
                onClick = { /* TODO: open new chat */ },
                containerColor = Color(0xFF266EFF),
                contentColor = Color.White,
                modifier = Modifier.size(60.dp)
            ) {
                Icon(
                    imageVector = Icons.Filled.Add,
                    contentDescription = "New Chat",
                    tint = Color.White
                )
            }
        },
        containerColor = Color(0xFF111016)
    ) { padding ->
        Column(
            modifier = modifier
                .fillMaxSize()
                .padding(padding)
                .background(Color(0xFF111016))
        ) {
            // 🔹 Header
            TopBar()

            // 🔹 Tabs
            ChatTabs()

            // 🔹 Chat list
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 9.dp),
                verticalArrangement = Arrangement.Top
            ) {
                item {
                    ArchivedSection(count = 24)
                }

                items(chats) { chat ->
                    ChatListItem(chat = chat, onClick = { onChatClick(chat) })
                }
            }
        }
    }
}

@Composable
fun TopBar() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text("InTouch", color = Color.White, fontSize = 28.sp, fontWeight = FontWeight.Bold)
        Row(verticalAlignment = Alignment.CenterVertically) {
            IconButton(onClick = { /* search */ }) {
                NeonIconButtons()
            }
            IconButton(onClick = { /* menu */ }) {
                NeonIconButton()
            }
        }
    }
}

@Composable
fun ChatTabs() {
    var selected = 0
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 16.dp, bottom = 12.dp)
            .padding(vertical = 8.dp)
            ,verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceAround
    ) {
        listOf("CHATS", "STATUS", "CALLS").forEachIndexed { index, title ->
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.clickable { selected = index }
            ) {
                Text(
                    text = title,
                color = if (index == selected) Color(0xFF266EFF) else Color.Gray,
                fontWeight = if (index == selected) FontWeight.Bold else FontWeight.Normal,
                modifier = Modifier
                    .padding(end = 20.dp)
                    .clickable { selected = index },
                fontSize = 15.sp
                )
                if (selected == index) {
                    Box(
                        modifier = Modifier
                            .padding(top = 2.dp , end = 20.dp)
                            .height(2.dp)
                            .width(40.dp)
                            .background(Color(0xFF266EFF))
                    )
                }
            }
        }
    }
}

@Composable
fun ArchivedSection(count: Int) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Icon(
                imageVector = Icons.Default.Archive,
                contentDescription = "Archived",
                tint = Color.Gray,
                modifier = Modifier.padding(horizontal = 12.dp)
            )
            Text(text = "Archived", color = Color.Gray)
        }
        Text(
            text = count.toString(),
            color = Color(0xFF3A6FFF),
            modifier = Modifier.padding(end = 16.dp)
        )
    }
}

@Composable
fun ChatListItem(chat: ChatItem, onClick: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 10.dp)
            .clickable { onClick() },
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            painter = painterResource(id = R.drawable.outline_contacts_product_24),
            contentDescription = "New Chat",
            tint = Color.White,
            modifier = Modifier.size(66.dp)
        )
//        Image(
//            painter = painterResource(id = R),
//            contentDescription = "Profile Image",
//            modifier = Modifier.size(48.dp)
//        )

        Spacer(modifier = Modifier.width(12.dp))


            Column(modifier = Modifier.weight(1f)) {
                Text(
                    chat.name,
                    color = Color(0xFFFFFFFE),
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 16.sp
                )
                Text(
                    chat.lastMessage,
                    color = Color(0xFF8E8E8E),
                    fontSize = 14.sp,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
            }

            Column(horizontalAlignment = Alignment.End) {
                Text(chat.time, color = Color(0xFF8E8E8E), fontSize = 12.sp)
                if (chat.unreadCount > 0) {
                    Box(
                        modifier = Modifier
                            .padding(top = 4.dp)
                            .background(Color(0xFF1A56F0), CircleShape)
                            .padding(horizontal = 8.dp, vertical = 2.dp)
                    ) {
                        Text("${chat.unreadCount}", color = Color.White, fontSize = 12.sp)
                    }
                }
            }
    }
}

@Preview(showSystemUi = true, showBackground = true)
@Composable
fun PreviewChatListScreen() {
    ChatListScreen(onChatClick = { chat ->
        println("Clicked on chat: ${chat.name}")
    })
}
