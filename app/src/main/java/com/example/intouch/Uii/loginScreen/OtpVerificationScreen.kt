package com.example.intouch.Uii.loginScreen


import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import kotlinx.coroutines.delay
import androidx.compose.foundation.text.KeyboardOptions
import androidx.lifecycle.viewmodel.compose.viewModel

import com.example.intouch.R
import com.example.intouch.Viewmodes.AuthViewModel

@Composable
fun OtpVerificationScreen(
    navController: NavController,
    phoneNumber: String = "123******15",
    viewModel: AuthViewModel = viewModel()
) {
    var otpValues by remember { mutableStateOf(List(4) { "" }) }
    var timer by remember { mutableStateOf(60) }

    // Countdown timer
    LaunchedEffect(timer) {
        if (timer > 0) {
            delay(1000)
            timer--
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF111016))
            .padding(horizontal = 24.dp),
        contentAlignment = Alignment.Center
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {

            // 🧩 App Logo
            Image(
                painter = painterResource(id = R.drawable.intouchlogo), // Replace with your logo
                contentDescription = "App Logo",
                modifier = Modifier
                    .size(240.dp)
                    .padding(bottom = 16.dp)
            )

            Spacer(modifier = Modifier.height(24.dp))

            // 🧩 Info text
            Text(
                text = "Code has been sent to $phoneNumber",
                fontSize = 14.sp,
                color = Color(0xFF8E8E8E),
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(32.dp))

            // 🧩 OTP Boxes
            Row(
                horizontalArrangement = Arrangement.spacedBy(12.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                otpValues.forEachIndexed { index, value ->
                    OutlinedTextField(
                        value = value,
                        onValueChange = { newValue ->
                            if (newValue.length <= 1 && newValue.all { it.isDigit() }) {
                                otpValues = otpValues.toMutableList().apply {
                                    this[index] = newValue
                                }
                            }
                        },
                        singleLine = true,
                        textStyle = LocalTextStyle.current.copy(
                            textAlign = TextAlign.Center,
                            fontSize = 24.sp,
                            color = Color.White
                        ),
                        modifier = Modifier
                            .width(60.dp)
                            .height(60.dp),
                        shape = RoundedCornerShape(10.dp),
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedBorderColor = Color(0xFF2563EB),
                            unfocusedBorderColor = Color(0xFF1E1E1E),
                            focusedContainerColor = Color(0xFF111111),
                            unfocusedContainerColor = Color(0xFF111111)
                        ),
                    )
                }
            }

            Spacer(modifier = Modifier.height(20.dp))

            // 🧩 Resend text
            Text(
                text = if (timer > 0) "Resend code in ${timer}s" else "Resend Code",
                color = if (timer > 0) Color(0xFF8E8E8E) else Color(0xFF2563EB),
                fontSize = 14.sp,
                modifier = Modifier.padding(top = 8.dp)
            )

            Spacer(modifier = Modifier.height(40.dp))

            // 🧩 Verify Button
            Button(
                onClick = {
                    val otpCode = otpValues.joinToString("")
                    if (otpCode.length == 4) {
                        viewModel.otp.value = otpCode
                        viewModel.phoneNumber.value = phoneNumber
                        viewModel.verifyOtp {
                            navController.navigate("home") {
                                popUpTo("login") { inclusive = true }
                            }
                        }
                    }
                },
                shape = CircleShape,
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF2563EB)),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(54.dp)
            ) {
                Text(
                    text = if (viewModel.loading.value) "Verifying..." else "Verify",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )
            }
            viewModel.errorMessage.value?.let {
                Text(text = it, color = Color.Red, modifier = Modifier.padding(top = 8.dp))
            }
        }
    }
}
//@Preview(showBackground = true, backgroundColor = 0xFF000000)
//@Composable
//fun PreviewOtpVerificationScreen() {
//    OtpVerificationScreen()
//}
