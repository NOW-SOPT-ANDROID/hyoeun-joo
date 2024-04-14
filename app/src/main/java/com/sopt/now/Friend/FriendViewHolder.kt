package com.sopt.now.Friend

import androidx.recyclerview.widget.RecyclerView
import com.sopt.now.databinding.ItemFriendBinding


class FriendViewHolder(private val binding: ItemFriendBinding) : RecyclerView.ViewHolder(binding.root) {

    fun onBind(friendData: FriendData) {
        binding.run {
            ivProfile.setImageResource(friendData.profileImage)
            tvName.text = friendData.name
            tvSelfDescription.text = friendData.selfDescription
        }
    }
}
