package com.sopt.now.presentation.Login


import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sopt.now.domain.AuthRepository
import com.sopt.now.domain.model.AuthData
import kotlinx.coroutines.launch
import retrofit2.HttpException

class LoginViewModel(
    private val authRepository: AuthRepository
) : ViewModel() {
    val loginResult: MutableLiveData<Boolean> = MutableLiveData()
    val errorMessage: MutableLiveData<String> = MutableLiveData()
    val userId: MutableLiveData<String> = MutableLiveData()
    fun logIn(authData: AuthData) {
        viewModelScope.launch{
            authRepository.logIn(authData)
            .onSuccess { response ->
                val userId =  response.headers()["Location"]
                userId?.let {
                    this@LoginViewModel.userId.value =it
                    loginResult.value = true
                }
            }
            .onFailure{ t ->
                if(t is HttpException){
                    val error = t.message()
                    errorMessage.postValue("$error")
                }
                errorMessage.postValue("서버 에러 발생")}

//        ServicePool.authService.logIn(loginRequest).enqueue(object : Callback<ResponseLogInDto> {
//            override fun onResponse(
//                call: Call<ResponseLogInDto>,
//                response: Response<ResponseLogInDto>,
//            ) {
//                if (response.isSuccessful) {
//                    val userId = response.headers()["location"]
//                    userId?.let {
//                        this@LoginViewModel.userId.postValue(it)
//                        loginResult.postValue(true)
//                    }
//                } else {
//                    val error = response.message()
//                    errorMessage.postValue("$error")
//                }
//            }
//
//            override fun onFailure(call: Call<ResponseLogInDto>, t: Throwable) {
//                errorMessage.postValue("서버 에러 발생")
//            }
//        })
    }
}
    }