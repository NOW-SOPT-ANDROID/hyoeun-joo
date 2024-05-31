package com.sopt.now.compose.presentation.Login

import android.content.ContentValues.TAG
import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sopt.now.compose.presentation.MainActivity
import com.sopt.now.compose.data.api.ServicePool
import com.sopt.now.compose.data.dto.LoginDto.RequestLogInDto
import com.sopt.now.compose.data.dto.LoginDto.ResponseLogInDto
import com.sopt.now.compose.domain.AuthRepository
import com.sopt.now.compose.domain.model.AuthEntity
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.HttpException
import retrofit2.Response

class LoginViewModel(
    private val authRepository: AuthRepository
) : ViewModel() {

    val loginResult: MutableLiveData<Boolean> = MutableLiveData()

    val errorMessage = MutableLiveData<String>()
    val userId = MutableLiveData<String>()
    var loginId = mutableStateOf("")
    var loginPw = mutableStateOf("")

    fun login(authEntity: AuthEntity) {
        viewModelScope.launch {
            authRepository.logIn(authEntity)
                .onSuccess { response ->
                    val userId = response.headers()["Location"]
                    userId?.let {
                        this@LoginViewModel.userId.postValue(it)
                        loginResult.postValue(true)
                    }
                    Log.d(TAG, "login successful. User ID: $userId")
                }
                .onFailure { t ->
                    if (t is HttpException) {
                        val error = t.message()
                        errorMessage.postValue("$error")
                        Log.e(TAG,"fail ${t.message}")
                    } else {
                        errorMessage.postValue("서버 에러 발생")
                        Log.e(TAG,"server fail ${t.message}")

                    }
                }
        }
    }
}

