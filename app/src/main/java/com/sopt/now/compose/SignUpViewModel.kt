package com.sopt.now.compose

import android.content.Context
import android.content.Intent
import android.util.Log
import android.widget.Toast
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.sopt.now.compose.api.ServicePool
import com.sopt.now.compose.dto.RequestSignUpDto
import com.sopt.now.compose.dto.ResponseSignUpDto
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SignUpViewModel : ViewModel() {

    var signupId = mutableStateOf("")
    var signupPw = mutableStateOf("")
    var signupName = mutableStateOf("")
    var signupPhone = mutableStateOf("")

    private val authService = ServicePool.authService

    fun getSignUpRequestDto(): RequestSignUpDto {
        return RequestSignUpDto(
            authenticationId = signupId.value,
            password = signupPw.value,
            nickname = signupName.value,
            phone = signupPhone.value
        )
    }

    fun signUp(context: Context) {
        val signUpRequest = getSignUpRequestDto()
        authService.signUp(signUpRequest).enqueue(object : Callback<ResponseSignUpDto> {
            override fun onResponse(
                call: Call<ResponseSignUpDto>,
                response: Response<ResponseSignUpDto>,
            ) {
                if (response.isSuccessful) {
                    val data: ResponseSignUpDto? = response.body()
                    val userId = response.headers()["location"]
                    Toast.makeText(
                        context,
                        "회원가입 성공, 유저의 ID는 $userId 입니다.",
                        Toast.LENGTH_SHORT,
                    ).show()
                    val toLogIn = Intent(context, LoginActivity::class.java)
                    context.startActivity(toLogIn)
                } else {
                    val error = response.message()
                    Toast.makeText(
                        context,
                        "회원가입 실패: $error",
                        Toast.LENGTH_SHORT,
                    ).show()
                }
            }

            override fun onFailure(call: Call<ResponseSignUpDto>, t: Throwable) {
                Log.e("SignUp", "서버 요청 실패", t)
                Toast.makeText(context, "서버 에러 발생", Toast.LENGTH_SHORT).show()
            }
        })
    }
}
