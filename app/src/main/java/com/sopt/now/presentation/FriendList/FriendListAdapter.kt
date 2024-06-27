
package com.sopt.now.presentation.FriendList

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.sopt.now.databinding.ItemFriendlistBinding
import com.sopt.now.domain.model.FriendListEntity

class FriendListAdapter : ListAdapter<FriendListEntity, FriendListViewHolder>(DiffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FriendListViewHolder {
        val binding = ItemFriendlistBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return FriendListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: FriendListViewHolder, position: Int) {
        holder.onBind(getItem(position))
    }

    companion object {
        private val DiffCallback = object : DiffUtil.ItemCallback<FriendListEntity>() {
            override fun areItemsTheSame(oldItem: FriendListEntity, newItem: FriendListEntity): Boolean {
                return oldItem.email == newItem.email
            }

            override fun areContentsTheSame(oldItem: FriendListEntity, newItem: FriendListEntity): Boolean {
                return oldItem == newItem
            }
        }
    }

//>>>>> 이후 추가 리팩할 부분
//    companion object{
//        private val DiffUtil = ItemDiffCallback
//    }
}
