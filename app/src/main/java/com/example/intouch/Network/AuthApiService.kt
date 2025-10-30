package com.example.intouch.Network


import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST

data class AuthRequest(
    val mobileNumber: String,
    val otp: String? = null
)

data class OtpResponse(
    val message: String,
    val success: Boolean
)

data class AuthResponse(
    val token: String? = null,
    val message: String,
    val success: Boolean
)

interface AuthApiService {

    @POST("api/auth/request-otp")
    suspend fun requestOtp(@Body request: AuthRequest): OtpResponse

    @POST("api/auth/verify")
    suspend fun verifyOtp(@Body request: AuthRequest): AuthResponse

    @GET("api/auth/user")
    suspend fun getUserProfile(@Header("Authorization") token: String): Any
}
