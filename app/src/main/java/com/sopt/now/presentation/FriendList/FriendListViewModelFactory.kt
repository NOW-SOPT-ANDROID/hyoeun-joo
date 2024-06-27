package com.sopt.now.presentation.FriendList

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.sopt.now.data.api.ServicePool
import com.sopt.now.data.repositoryimpl.FriendListServiceImpl

class FriendListViewModelFactory:ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(FriendListViewModel::class.java)) {
            val repository =
                FriendListServiceImpl(ServicePool.friendListService)
            return FriendListViewModel(repository) as T
        } else {
            throw IllegalArgumentException("Failed to create ViewModel: ${modelClass.name}")
        }
    }
}