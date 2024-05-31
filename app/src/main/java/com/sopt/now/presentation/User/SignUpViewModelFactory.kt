package com.sopt.now.presentation.User

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.sopt.now.data.AuthRepoImpl
import com.sopt.now.data.api.ServicePool
import com.sopt.now.presentation.SignUpViewModel

class SignUpViewModelFactory :ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(SignUpViewModel::class.java)) {
            val repository =
                AuthRepoImpl(ServicePool.authService)//authService를 인자로 받아서 Repository인스턴스 생성
            return SignUpViewModel(repository) as T
        } else {
            throw IllegalArgumentException("Failed to create ViewModel: ${modelClass.name}")
        }
    }
}