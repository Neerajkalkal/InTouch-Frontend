package com.example.intouch.homeScreen

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape

import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
//import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.material.icons.Icons
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.compose.material.icons.filled.Add
import androidx.navigation.compose.rememberNavController

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(navController: NavController) {
    var selectedTab by remember { mutableStateOf("Chats") }

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(
                onClick = { /* open new chat screen */ },
                containerColor = Color(0xFF266EFF)
            ) {
                Icon(
                    imageVector = Icons.Filled.Add,
                    contentDescription = "New Chat",
                    tint = Color.White
                )
            }
        },
        containerColor = Color(0xFF111016)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
        ) {
            TopAppBar(
                title = {
                    Text(
                        "InTouch",
                        color = Color.White,
                        fontSize = 28.sp,
                        fontWeight = FontWeight.Bold
                    )
                },
                actions = {
                        IconButton(onClick = { /* search */ }) {
                            NeonIconButtons()
                        }

                        IconButton(onClick = { /* menu */ }) {
                            NeonIconButton()
                        }
                    },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color(0xFF111016)
                )
            )

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 10.dp)
                    .padding(vertical = 8.dp),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                listOf("CHATS", "STATUS", "CALLS").forEach { tab ->
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier.clickable { selectedTab = tab }
                    ) {
                        Text(
                            text = tab,
                            color = if (selectedTab == tab) Color(0xFF266EFF) else Color.Gray,
                            fontWeight = if (selectedTab == tab) FontWeight.Bold else FontWeight.Normal
                        )
                        if (selectedTab == tab) {
                            Box(
                                modifier = Modifier
                                    .fillMaxSize()
                                    .padding(top = 1.dp)
                                    .height(10.dp)
                                    .width(40.dp)
                                    .background(Color(0xFF266EFF))
                            )
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(50.dp))

            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = "You haven’t chat yet",
                    color = Color(0xFF8E8E8E),
                    fontSize = 22.sp
                )
                Spacer(modifier = Modifier.height(16.dp))
                Button(
                    onClick = { /* start new chat */ },
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF266EFF)),
                    shape = RoundedCornerShape(18.dp)
                ) {
                    Text("Start Chatting", color = Color.White)
                }
            }
        }
    }
}


@Preview(showBackground = true, showSystemUi = true)
@Composable
fun HomeScreenPreview() {
    val navController = rememberNavController()
    HomeScreen(navController = navController)
}

