package com.sopt.now.presentation.User

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.sopt.now.databinding.ItemUserBinding

class UserAdapter() : RecyclerView.Adapter<UserAdapter.UserViewHolder>() {
    private var userList: List<UserData> = emptyList()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val binding = ItemUserBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return UserViewHolder(binding)
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        val userData = userList[position]
//        holder.bind(userData)
    }

    override fun getItemCount(): Int {
        return userList.size
    }

    fun setUserList(userList: List<UserData>) {
        this.userList = userList.toList()
        notifyDataSetChanged()
    }

    class UserViewHolder(private val binding: ItemUserBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(userData: UserDataInput) {
            binding.run {
                tvName.text = userData.userNickName
                tvSelfDescription.text = "34기 YB 주효은입니다!!!"
            }
        }
    }
}