package com.example.intouch.loginScreen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.intouch.R

@Composable
fun SignUpScreen(
    onSignInClick: () -> Unit = {},
    onSignUpClick: (String, Boolean) -> Unit = { _, _ -> }
) {
    var phoneNumber by remember { mutableStateOf("") }
    var rememberMe by remember { mutableStateOf(false) }

    // Background
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF111016)), // dark background
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 32.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(80.dp))

            // Logo
            Image(
                painter = painterResource(id = R.drawable.intouchlogo), // Add your logo here
                contentDescription = "InTouch Logo",
                modifier = Modifier.size(250.dp)
            )

            Spacer(modifier = Modifier.height(16.dp))

//            Text(
//                text = "InTouch",
//                color = Color.White,
//                fontSize = 28.sp,
//                fontWeight = FontWeight.Bold
//            )

            Spacer(modifier = Modifier.height(32.dp))

            Text(
                text = "Sign up for free",
                color = Color.White,
                fontSize = 22.sp,
                fontWeight = FontWeight.Medium
            )

            Spacer(modifier = Modifier.height(35.dp))

            Row(modifier = Modifier.fillMaxWidth()){
                Text(
                    text = "Phone Number",
                    color = Color(0xFF6c6976),
                    fontSize = 17.sp
                )
                Text(
                    text = "*",
                    color = Color.Red,
                    fontSize = 17.sp,
                )
            }
            // Phone number field
            OutlinedTextField(
                value = phoneNumber,
                onValueChange = { phoneNumber = it },
                label = {
                    Text("Phone Number", color = Color(0xFF726f7d))
                },
                leadingIcon = {
                    Icon(
                        painter = painterResource(id = R.drawable.baseline_phone_24), // Add phone icon drawable
                        contentDescription = "Phone Icon",
                        tint = Color(0xFF726f7d)
                    )
                },
                singleLine = true,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = Color(0xFF6c6976),
                    unfocusedBorderColor = Color(0xFF6c6976),
                    focusedTextColor = Color.White,
                    unfocusedTextColor = Color.White
                ),

                modifier = Modifier
                    .fillMaxWidth()
                    .height(65.dp)
            )

            Spacer(modifier = Modifier.height(12.dp))

            // Remember Me
            Row(
                modifier = Modifier.fillMaxSize()
            ) {

            Column(
//                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Checkbox(
                        checked = rememberMe,
                        onCheckedChange = { rememberMe = it },
                        colors = CheckboxDefaults.colors(
                            checkedColor = Color(0xFF1E90FF),
                            uncheckedColor = Color(0xFF1E90FF)
                        )
                    )
                    Text(
                        text = "Remember me",
                        color = Color.White,
                        fontSize = 17.sp
                    )
                }

                Spacer(modifier = Modifier.height(5.dp))

                // Sign Up Button
                Button(
                    onClick = { onSignUpClick(phoneNumber, rememberMe) },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(56.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF273569)),
                    shape = MaterialTheme.shapes.large
                ) {
                    Text(text = "Sign Up", fontSize = 16.sp, color = Color.White)
                }

                Spacer(modifier = Modifier.height(20.dp))

                // Sign In text
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(
                        text = "Already have an account? ",
                        color = Color.White,
                        fontSize = 14.sp
                    )
                    TextButton(onClick = onSignInClick) {
                        Text(
                            text = "Sign in",
                            color = Color(0xFF1E90FF),
                            fontSize = 14.sp
                        )
                    }
                }
            }
            }
        }
    }
}
