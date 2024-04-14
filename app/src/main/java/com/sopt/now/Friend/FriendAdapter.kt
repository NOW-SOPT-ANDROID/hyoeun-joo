package com.sopt.now.Friend

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.sopt.now.databinding.ItemFriendBinding

class FriendAdapter() : RecyclerView.Adapter<FriendViewHolder>() {
    private var friendList: List<FriendData> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FriendViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemFriendBinding.inflate(inflater, parent, false)
        return FriendViewHolder(binding)
    }

    override fun onBindViewHolder(holder: FriendViewHolder, position: Int) {
        holder.onBind(friendList[position])
    }

    override fun getItemCount() = friendList.size

    fun setFriendList(friendList: List<FriendData>) {
        this.friendList = friendList.toList()
        notifyDataSetChanged()
    }
}