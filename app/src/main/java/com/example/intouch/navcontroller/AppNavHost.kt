package com.example.intouch.navcontroller


import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.*
import androidx.navigation.navArgument
import com.example.intouch.homeScreen.HomeScreen
import com.example.intouch.loginScreen.LoginScreen
import com.example.intouch.loginScreen.OtpVerificationScreen

@Composable
fun AppNavHost() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = "login"
    ) {
        // ✅ Correct syntax: Use lambda { } after composable
        composable("login") {
            LoginScreen(navController)
        }

        composable(
            "otp/{phoneNumber}",
            arguments = listOf(navArgument("phoneNumber") { type = NavType.StringType })
        ) { backStackEntry ->
            val phone = backStackEntry.arguments?.getString("phoneNumber") ?: ""
            OtpVerificationScreen(navController, phone)
        }

        composable("home") {
            HomeScreen(navController)
        }
    }
}