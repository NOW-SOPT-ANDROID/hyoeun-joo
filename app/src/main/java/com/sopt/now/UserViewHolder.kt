package com.sopt.now

import androidx.recyclerview.widget.RecyclerView
import com.sopt.now.databinding.ItemUserBinding

class UserViewHoler(private val binding: ItemUserBinding)
    : RecyclerView.ViewHolder(binding.root) {
    fun onBind(userData: UserDataInput) {
        binding.run {
            tvName.text = userData.userNickName
            tvSelfDescription.text = "34기 YB 주효은입니다!!!"
        }
    }

}