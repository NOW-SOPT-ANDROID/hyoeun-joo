package com.sopt.now


import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.sopt.now.api.ServicePool
import com.sopt.now.dto.SignUp.RequestSignUpDto
import com.sopt.now.dto.SignUp.ResponseSignUpDto
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SignUpViewModel : ViewModel() {
    val signUpResult: MutableLiveData<Boolean> = MutableLiveData()
    val errorMessage: MutableLiveData<String> = MutableLiveData()
    val userId: MutableLiveData<String> = MutableLiveData()
    fun signUp(signUpRequest: RequestSignUpDto) {
        ServicePool.authService.signUp(signUpRequest).enqueue(object : Callback<ResponseSignUpDto> {
            override fun onResponse(
                call: Call<ResponseSignUpDto>,
                response: Response<ResponseSignUpDto>,
            ) {
                if (response.isSuccessful) {
                    val userId = response.headers()["location"]
                    userId?.let {
                        this@SignUpViewModel.userId.postValue(it)
                        signUpResult.postValue(true)
                    }
                } else {
                    val errorBody = response.errorBody()?.string() ?: "No error message"
                    val errorMessage = JSONObject(errorBody).getString("message")
                    this@SignUpViewModel.errorMessage.postValue(errorMessage)
                }
            }

            override fun onFailure(call: Call<ResponseSignUpDto>, t: Throwable) {
                errorMessage.postValue("서버 에러 발생")
            }
        })
    }
}