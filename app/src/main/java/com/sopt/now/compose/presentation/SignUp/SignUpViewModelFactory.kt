package com.sopt.now.compose.presentation.SignUp

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.sopt.now.compose.data.AuthRepoImpl
import com.sopt.now.compose.data.api.ServicePool
import com.sopt.now.compose.presentation.Login.LoginViewModel

class SignUpViewModelFactory : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(SignUpViewModel::class.java)) {
            val repository =
                AuthRepoImpl(ServicePool.authService)
            return SignUpViewModel(repository) as T
        } else{
            throw IllegalArgumentException("Failed to create ViewModel: ${modelClass.name}")
        }
    }
}