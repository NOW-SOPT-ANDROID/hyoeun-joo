package com.sopt.now.presentation.FriendList

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sopt.now.domain.FriendListRepository
import com.sopt.now.domain.model.FriendListEntity
import kotlinx.coroutines.launch

class FriendListViewModel(
    private val friendListRepository: FriendListRepository
) :ViewModel() {

    private val _friendList = MutableLiveData<List<FriendListEntity>>()
    val friendList: LiveData<List<FriendListEntity>> get() = _friendList

    fun updateFriendList(page:Int){
        viewModelScope.launch {
            friendListRepository.getFreindList(page)
                .onSuccess {
                  _friendList.value = it
                }
                .onFailure { Throwable ->

                }

        }
    }
}