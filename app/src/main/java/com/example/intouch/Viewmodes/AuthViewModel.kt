package com.example.intouch.Viewmodes



import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.intouch.Network.AuthRequest
import com.example.intouch.Network.RetrofitInstance
import kotlinx.coroutines.launch

class AuthViewModel : ViewModel() {

    var phoneNumber = mutableStateOf("")
    var otp = mutableStateOf("")
    var loading = mutableStateOf(false)
    var errorMessage = mutableStateOf<String?>(null)
    var token = mutableStateOf<String?>(null)

    // Request OTP from backend
    fun requestOtp(onSuccess: () -> Unit) {
        viewModelScope.launch {
            loading.value = true
            try {
                val response = RetrofitInstance.api.requestOtp(
                    AuthRequest(mobileNumber = phoneNumber.value)
                )
                if (response.success) {
                    errorMessage.value = null
                    onSuccess()
                } else {
                    errorMessage.value = response.message
                }
            } catch (e: Exception) {
                errorMessage.value = "Network error: ${e.message}"
            }
            loading.value = false
        }
    }

    // Verify OTP from backend
    fun verifyOtp(onSuccess: () -> Unit) {
        viewModelScope.launch {
            loading.value = true
            try {
                val response = RetrofitInstance.api.verifyOtp(
                    AuthRequest(mobileNumber = phoneNumber.value, otp = otp.value)
                )
                if (response.success && response.token != null) {
                    token.value = response.token
                    errorMessage.value = null
                    onSuccess()
                } else {
                    errorMessage.value = response.message
                }
            } catch (e: Exception) {
                errorMessage.value = "Network error: ${e.message}"
            }
            loading.value = false
        }
    }
}
