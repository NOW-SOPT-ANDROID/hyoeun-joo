package com.sopt.now.presentation


import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sopt.now.domain.AuthRepository
import com.sopt.now.domain.model.AuthEntity
import kotlinx.coroutines.launch
import org.json.JSONObject


class SignUpViewModel(
    private val authRepository: AuthRepository,
) : ViewModel() {
    val signUpResult: MutableLiveData<Boolean> = MutableLiveData()
    val errorMessage: MutableLiveData<String> = MutableLiveData()
    val userId: MutableLiveData<String> = MutableLiveData()
    fun signUp(authData: AuthEntity) {
        viewModelScope.launch {
            runCatching {
                authRepository.signUp(authData)
                    .onSuccess { response ->
                        if (response.isSuccessful) {
                            val userId = response.headers()["Location"]
                            userId?.let {
                                this@SignUpViewModel.userId.value = it
                                signUpResult.value = true
                            }
                        } else {
                            val error = response.errorBody()?.string()
                            val jsonObject = error?.let { JSONObject(it) }

                            val message = jsonObject?.getString("message")
                            errorMessage.postValue(message.toString())
                        }
                    }
                    .onFailure { t ->
                        errorMessage.postValue(t.message.toString())
                    }
            }
        }
    }
}