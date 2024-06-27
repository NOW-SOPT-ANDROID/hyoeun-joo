package com.sopt.now.presentation.FriendList

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.sopt.now.data.api.ServicePool
import com.sopt.now.data.datasourceimpl.FriendListDataSourceImpl
import com.sopt.now.data.repositoryimpl.FriendListRepositoryImpl

class FriendListViewModelFactory:ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(FriendListViewModel::class.java)) {
            val dataSource = FriendListDataSourceImpl(ServicePool.friendListService)
            val repository = FriendListRepositoryImpl(dataSource)
            return FriendListViewModel(repository) as T
        } else {
            throw IllegalArgumentException("Failed to create ViewModel: ${modelClass.name}")
        }
    }
}