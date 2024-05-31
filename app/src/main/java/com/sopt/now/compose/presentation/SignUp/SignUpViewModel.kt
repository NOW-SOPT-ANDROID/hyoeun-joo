package com.sopt.now.compose.presentation.SignUp


import android.content.ContentValues.TAG
import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sopt.now.compose.domain.AuthRepository
import com.sopt.now.compose.domain.model.AuthEntity
import kotlinx.coroutines.launch
import retrofit2.HttpException

class SignUpViewModel(
    private val authRepository: AuthRepository,
) : ViewModel() {
    val signUpResult: MutableLiveData<Boolean> = MutableLiveData()
    val errorMessage: MutableLiveData<String> = MutableLiveData()
    val userId: MutableLiveData<String> = MutableLiveData()

    var signUpId = mutableStateOf("")
    var signUpPw = mutableStateOf("")
    var signUpName = mutableStateOf("")
    var signUpPhone = mutableStateOf("")
    fun signUp(authEntity: AuthEntity) {
        viewModelScope.launch {
            runCatching {
                authRepository.signUp(authEntity)
                    .onSuccess { response ->
                        val userId = response.headers()["Location"]
                        userId?.let {
                            this@SignUpViewModel.userId.postValue(it)
                            signUpResult.postValue(true)
                        }
                        Log.d(TAG, "Sign up successful. User ID: $userId")
                    }
                    .onFailure { t ->
                        if (t is HttpException) {
                            val error = t.message()
                            errorMessage.postValue("$error")
                            Log.e(TAG, "Sign up failed: ${t.message}")
                        } else {
                            errorMessage.postValue("서버 에러 발생")
                            Log.e(TAG, "Server error: ${t.message}")
                        }
                    }
            }
        }
    }
}
