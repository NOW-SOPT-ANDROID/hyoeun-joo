package com.sopt.now

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.sopt.now.databinding.ItemUserBinding

class UserAdapter() : RecyclerView.Adapter<UserAdapter.UserViewHolder>() {
    private val userList: List<UserDataInput> = emptyList()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val binding = ItemUserBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return UserViewHolder(binding)
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        val userData = userList[position]
        holder.bind(userData)
    }

    override fun getItemCount(): Int {
        return userList.size
    }

    class UserViewHolder(private val binding: ItemUserBinding)
        : RecyclerView.ViewHolder(binding.root) {

        fun bind(userData: UserDataInput) {
            binding.run {
                tvName.text = userData.userNickName
                tvSelfDescription.text = "34기 YB 주효은입니다!!!" // You can modify this text as needed
            }
        }
    }
}
