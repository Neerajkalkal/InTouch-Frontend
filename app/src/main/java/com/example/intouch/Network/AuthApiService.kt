package com.example.intouch.Network


import com.example.intouch.data.model.ChatMessage
import com.google.android.gms.common.api.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Query

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

//    @POST("/api/chat/send")
//    suspend fun sendMessage(
//        @Query("chatId") chatId: String,
//        @Body message: ChatMessage
//    ): Response<String>

}
