package com.sopt.now


import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.sopt.now.api.ServicePool
import com.sopt.now.dto.LoginDto.RequestLogInDto
import com.sopt.now.dto.LoginDto.ResponseLogInDto
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginViewModel : ViewModel() {
    val loginResult: MutableLiveData<Boolean> = MutableLiveData()
    val errorMessage: MutableLiveData<String> = MutableLiveData()
    val userId: MutableLiveData<String> = MutableLiveData()
    fun logIn(loginRequest: RequestLogInDto) {
        ServicePool.authService.logIn(loginRequest).enqueue(object : Callback<ResponseLogInDto> {
            override fun onResponse(
                call: Call<ResponseLogInDto>,
                response: Response<ResponseLogInDto>,
            ) {
                if (response.isSuccessful) {
                    val userId = response.headers()["location"]
                    userId?.let {
                        this@LoginViewModel.userId.postValue(it)
                        loginResult.postValue(true)
                    }
                } else {
                    val error = response.message()
                    errorMessage.postValue("$error")
                }
            }

            override fun onFailure(call: Call<ResponseLogInDto>, t: Throwable) {
                errorMessage.postValue("서버 에러 발생")
            }
        })
    }
}