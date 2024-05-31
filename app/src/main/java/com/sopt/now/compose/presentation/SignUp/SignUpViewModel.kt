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
    private val authRepository: AuthRepository
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


//    fun signUp(authEntity: AuthEntity) {
//        viewModelScope.launch {
//            runCatching {
//                authRepository.signUp(authEntity)
//                    .onSuccess { response ->
//                        val userId = response.headers()["Location"]
//                        userId?.let {
//                            this@SignUpViewModel.userId.postValue(it)
//                            signUpResult.postValue(true)
//                        }
//                    }
//                    .onFailure { t ->
//                        Log.e("SignUpViewModel", "Error: ${t.message}", t)
//                        if (t is HttpException) {
//                            val error = t.message()
//                            errorMessage.postValue("$error")
//                        } else {
//                            errorMessage.postValue("서버 에러 발생")
//                        }
//                    }
//            }
//        }
//    }

}
//class SignUpViewModel : ViewModel() {
//
//    var signupId = mutableStateOf("")
//    var signupPw = mutableStateOf("")
//    var signupName = mutableStateOf("")
//    var signupPhone = mutableStateOf("")
//
//    private val authService = ServicePool.authService
//
//    fun getSignUpRequestDto(): RequestSignUpDto {
//        return RequestSignUpDto(
//            authenticationId = signupId.value,
//            password = signupPw.value,
//            nickname = signupName.value,
//            phone = signupPhone.value
//        )
//    }
//
//    fun signUp(context: Context) {
//        val signUpRequest = getSignUpRequestDto()
//        authService.signUp(signUpRequest).enqueue(object : Callback<ResponseSignUpDto> {
//            override fun onResponse(
//                call: Call<ResponseSignUpDto>,
//                response: Response<ResponseSignUpDto>,
//            ) {
//                if (response.isSuccessful) {
//                    val data: ResponseSignUpDto? = response.body()
//                    val userId = response.headers()["location"]
//                    Toast.makeText(
//                        context,
//                        "회원가입 성공, 유저의 ID는 $userId 입니다.",
//                        Toast.LENGTH_SHORT,
//                    ).show()
//                    val toLogIn = Intent(context, LoginActivity::class.java)
//                    context.startActivity(toLogIn)
//                } else {
//                    val error = response.message()
//                    Toast.makeText(
//                        context,
//                        "회원가입 실패: $error",
//                        Toast.LENGTH_SHORT,
//                    ).show()
//                }
//            }
//
//            override fun onFailure(call: Call<ResponseSignUpDto>, t: Throwable) {
//                Log.e("SignUp", "서버 요청 실패", t)
//                Toast.makeText(context, "서버 에러 발생", Toast.LENGTH_SHORT).show()
//            }
//        })
//    }
//}
