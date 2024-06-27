package com.sopt.now.presentation.FriendList

import androidx.recyclerview.widget.RecyclerView
import coil.api.load
import com.sopt.now.databinding.ItemFriendlistBinding
import com.sopt.now.domain.model.FriendListEntity

class FriendListViewHolder(
    private val binding: ItemFriendlistBinding,
) : RecyclerView.ViewHolder(binding.root) {
    fun onBind(data: FriendListEntity) {
        with(binding) {
            tvFriendListName.text = "${data.firstName} ${data.lastName}"
            tvFriendListEmail.text = data.email
            ivFriendListProfile.load(data.avatar)
        }

    }
}